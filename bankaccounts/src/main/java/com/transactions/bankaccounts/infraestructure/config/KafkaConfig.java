package com.transactions.bankaccounts.infraestructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic clientesTopic() {
        return TopicBuilder.name("clientes-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic cuentasTopic() {
        return TopicBuilder.name("cuentas-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic movimientosTopic() {
        return TopicBuilder.name("movimientos-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }

}
