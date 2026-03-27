package com.transactions.bankaccounts.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.ConfluentKafkaContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * Clase base para todas las pruebas de integración de bankaccounts.
 *
 * Levanta UN SOLO par de contenedores para toda la suite (static @Container)
 * y los reutiliza entre tests — evita tiempos de arranque por cada clase.
 *
 * Hereda: @SpringBootTest + @Testcontainers + @ActiveProfiles("test")
 *
 * NOTA: Se usa KafkaContainer (org.testcontainers.kafka) en lugar de
 * ConfluentKafkaContainer porque la versión 7.9.0 de cp-kafka tiene un bug
 * con advertised.listeners=0.0.0.0 que hace fallar el arranque del contenedor.
 * KafkaContainer resuelve esto internamente con la misma imagen.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

    // -------------------------------------------------------------------------
    // Contenedor Kafka
    // -------------------------------------------------------------------------
    @Container
    protected static final ConfluentKafkaContainer kafka = new ConfluentKafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:7.6.1"));

    // -------------------------------------------------------------------------
    // Contenedor PostgreSQL
    // -------------------------------------------------------------------------
    @Container
    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:16-alpine"))
            .withDatabaseName("bankaccounts_test")
            .withUsername("test")
            .withPassword("test");

    // -------------------------------------------------------------------------
    // Inyecta las URLs generadas por Testcontainers en el contexto de Spring
    // Reemplaza localhost:9092 y localhost:5432 del application.properties
    // -------------------------------------------------------------------------
    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        // Kafka
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);

        // PostgreSQL
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
}