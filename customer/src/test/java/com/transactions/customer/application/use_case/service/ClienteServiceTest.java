package com.transactions.customer.application.use_case.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transactions.customer.application.mapper.ClienteAppMapper;
import com.transactions.customer.application.use_case.service.impl.ClienteService;
import com.transactions.customer.domain.exception.ResourceNotFoundException;
import com.transactions.customer.domain.model.Cliente;
import com.transactions.customer.domain.port.out.ClienteRepositoryPort;
import com.transactions.customer.infraestructure.adapter.out.kafka.ClienteProducer;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    @Mock
    private ClienteRepositoryPort repository;

    @Mock
    private ClienteAppMapper mapper;

    @Mock
    private ClienteProducer producer;

    @InjectMocks
    private ClienteService service;

    @Test
    void deberiaEncontrarClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setClienteId("1");

        when(repository.findById("1")).thenReturn(Optional.of(cliente));

        Cliente resultado = service.findById("1");

        assertEquals("1", resultado.getClienteId());
    }

    @Test
    void deberiaLanzarExcepcionSiNoExiste() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById("1");
        });
    }
}
