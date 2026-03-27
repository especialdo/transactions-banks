package com.transactions.bankaccounts.infraestructure.adapter.in.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.transactions.bankaccounts.application.dto.ClientSincronize;
import com.transactions.bankaccounts.application.use_case.ClienteSrincronizeUseCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClienteEventConsumer {
    private final ClienteSrincronizeUseCase clientUseCase;

    @KafkaListener(topics = "clientes-topic", groupId = "bankaccounts-group")
    public void consumeClienteEvent(ClienteTopic event) {
        System.out.println("🔥 RAW EVENTO: " + event);
        if ("CREAR".equals(event.getAccion())) {
            ClientSincronize c = ClientSincronize.builder()
                    .clienteId(event.getClienteId())
                    .estado(event.getEstado())
                    .nombre(event.getNombre())
                    .build();
            clientUseCase.save(c);
        } else if ("ELIMINAR".equals(event.getAccion())) {
            clientUseCase.delete(event.getClienteId());
        }
    }

}
