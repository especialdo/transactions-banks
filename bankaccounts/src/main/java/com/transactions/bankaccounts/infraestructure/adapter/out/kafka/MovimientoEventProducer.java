package com.transactions.bankaccounts.infraestructure.adapter.out.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.transactions.bankaccounts.domain.port.out.MovimientoEvent;
import com.transactions.bankaccounts.domain.port.out.MovimientoEventPort;
import com.transactions.bankaccounts.infraestructure.adapter.exception.KafkaPublishException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MovimientoEventProducer implements MovimientoEventPort {
    private final KafkaTemplate<String, MovimientoEvent> kafkaTemplate;
    private static final String TOPIC = "movimientos.eventos";

    @Override
    public void publicarMovimiento(MovimientoEvent event) {
        kafkaTemplate.send(TOPIC, event.getCuentaId(), event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        throw new KafkaPublishException("Error publicando evento", ex);
                    }
                });
    }

}
