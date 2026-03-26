package com.transactions.customer.infraestructure.adapter.out.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.transactions.customer.application.dtos.ClienteEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteProducer {
    private final KafkaTemplate<String, ClienteEvent> kafkaTemplate;

    public void sendClienteEvent(ClienteEvent event) {
        kafkaTemplate.send("clientes-topic", event.getClienteId(), event);
    }

}
