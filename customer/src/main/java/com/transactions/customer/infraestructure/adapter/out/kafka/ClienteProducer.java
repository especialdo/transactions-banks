package com.transactions.customer.infraestructure.adapter.out.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.transactions.customer.domain.dto.ClienteEvent;

import com.transactions.customer.domain.port.out.CustomerEventPublisherPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteProducer implements CustomerEventPublisherPort {
    private final KafkaTemplate<String, ClienteEvent> kafkaTemplate;

    public void sendClienteEvent(ClienteEvent event) {
        kafkaTemplate.send("clientes-topic", event.getClienteId(), event);
    }

    @Override
    public void publicarCliente(ClienteEvent event) {
        kafkaTemplate.send("clientes-topic", event.getClienteId(), event);
    }

}
