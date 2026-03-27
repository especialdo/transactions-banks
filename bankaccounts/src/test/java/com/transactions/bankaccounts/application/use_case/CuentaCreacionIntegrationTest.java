package com.transactions.bankaccounts.application.use_case;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.client.RestClient;

import com.transactions.bankaccounts.domain.enums.TipoCuenta;
import com.transactions.bankaccounts.infraestructure.adapter.in.kafka.ClienteTopic;
import com.transactions.bankaccounts.infraestructure.adapter.in.rest.CuentaRequest;
import com.transactions.bankaccounts.infraestructure.adapter.in.rest.SuccessResponse;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.repository.ClienteSincronizeRepository;
import com.transactions.bankaccounts.integration.IntegrationTestBase;

@DisplayName("E2E: Flujo creación de cuenta vía evento Kafka")
class CuentaCreacionIntegrationTest extends IntegrationTestBase {

        // -------------------------------------------------------------------------
        // Dependencias inyectadas
        // -------------------------------------------------------------------------

        @Autowired
        private ClienteSincronizeRepository clienteSincronizeRepository;

        @LocalServerPort
        private int port;

        private RestClient restClient;

        private KafkaProducer<String, ClienteTopic> kafkaProducer;

        // -------------------------------------------------------------------------
        // Setup
        // -------------------------------------------------------------------------

        @BeforeEach
        void setUp() {
                // RestClient apuntando a la URL base del servidor de test
                restClient = RestClient.builder()
                                .baseUrl("http://localhost:" + port)
                                .build();

                Map<String, Object> props = new HashMap<>();
                props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
                props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
                props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
                props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

                kafkaProducer = new KafkaProducer<>(props);
        }

        @AfterEach
        void tearDown() {
                kafkaProducer.close();
                clienteSincronizeRepository.deleteAll();
        }

        // -------------------------------------------------------------------------
        // Helper: POST /api/cuenta sin lanzar excepción en 4xx/5xx
        // -------------------------------------------------------------------------

        private HttpStatusCode postCuenta(CuentaRequest request) {
                return restClient.post()
                                .uri("/api/cuenta")
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(request)
                                .retrieve()
                                .onStatus(status -> true, (req, res) -> {
                                })
                                .toBodilessEntity()
                                .getStatusCode();
        }

        // -------------------------------------------------------------------------
        // Caso 1 — Happy path: cliente publicado → cuenta creada exitosamente
        // -------------------------------------------------------------------------

        @Test
        @DisplayName("Debe crear cuenta cuando el clienteId fue sincronizado desde Kafka")
        void debeCrearCuentaCuandoClienteExiste() {
                // --- ARRANGE ---
                String clienteId = UUID.randomUUID().toString();

                ClienteTopic evento = new ClienteTopic(clienteId, "Juan Perez", true, "CREAR");

                // --- ACT 1: publicar evento simulando el microservicio customer ---
                kafkaProducer.send(new ProducerRecord<>("clientes-topic", clienteId, evento));
                kafkaProducer.flush();

                // --- WAIT: hasta que el consumer persista el clienteId (máx 15s) ---
                await()
                                .atMost(Duration.ofSeconds(15))
                                .pollInterval(Duration.ofMillis(500))
                                .untilAsserted(() -> assertThat(clienteSincronizeRepository.findByClienteId(clienteId))
                                                .isPresent());

                // --- ACT 2: crear la cuenta ---
                CuentaRequest request = new CuentaRequest(
                                "001-" + clienteId.substring(0, 8),
                                TipoCuenta.AHORROS,
                                new BigDecimal("500.00"),
                                true,
                                clienteId // campo "cliente" del record
                );

                SuccessResponse body = restClient.post()
                                .uri("/api/cuenta")
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(request)
                                .retrieve()
                                .body(SuccessResponse.class);

                // --- ASSERT ---
                assertThat(body).isNotNull();
                assertThat(body.message()).contains("cuenta creada exitosamente");
        }

        // -------------------------------------------------------------------------
        // Caso 2 — Negativo: clienteId nunca sincronizado → 500
        // -------------------------------------------------------------------------

        @Test
        @DisplayName("Debe rechazar la creación si el clienteId no existe en sincronizacion_clientes")
        void debeRechazarCuentaCuandoClienteNoExiste() {
                String clienteIdInexistente = UUID.randomUUID().toString();

                HttpStatusCode status = postCuenta(new CuentaRequest(
                                "002-" + clienteIdInexistente.substring(0, 8),
                                TipoCuenta.CORRIENTE,
                                new BigDecimal("1000.00"),
                                true,
                                clienteIdInexistente));

                assertThat(status).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // -------------------------------------------------------------------------
        // Caso 3 — Evento ELIMINAR: cliente eliminado no puede abrir cuenta
        // -------------------------------------------------------------------------

        @Test
        @DisplayName("Debe eliminar cliente sincronizado cuando llega evento ELIMINAR")
        void debeEliminarClienteCuandoLlegaEventoEliminar() {
                String clienteId = UUID.randomUUID().toString();

                // Paso 1: publicar CREAR y esperar sincronización
                kafkaProducer.send(new ProducerRecord<>("clientes-topic", clienteId,
                                new ClienteTopic(clienteId, "Ana Lopez", true, "CREAR")));
                kafkaProducer.flush();

                await()
                                .atMost(Duration.ofSeconds(15))
                                .pollInterval(Duration.ofMillis(500))
                                .untilAsserted(() -> assertThat(clienteSincronizeRepository.findByClienteId(clienteId))
                                                .isPresent());

                // Paso 2: publicar ELIMINAR y esperar que desaparezca
                kafkaProducer.send(new ProducerRecord<>("clientes-topic", clienteId,
                                new ClienteTopic(clienteId, "Ana Lopez", true, "ELIMINAR")));
                kafkaProducer.flush();

                await()
                                .atMost(Duration.ofSeconds(15))
                                .pollInterval(Duration.ofMillis(500))
                                .untilAsserted(() -> assertThat(clienteSincronizeRepository.findByClienteId(clienteId))
                                                .isEmpty());

                // Paso 3: intentar crear cuenta → debe fallar
                HttpStatusCode status = postCuenta(new CuentaRequest(
                                "003-" + clienteId.substring(0, 8),
                                TipoCuenta.AHORROS,
                                new BigDecimal("200.00"),
                                true,
                                clienteId));

                assertThat(status).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        }
}