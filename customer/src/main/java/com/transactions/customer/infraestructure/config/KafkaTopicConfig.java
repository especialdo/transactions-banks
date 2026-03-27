package com.transactions.customer.infraestructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic clientesTopic() {
        return TopicBuilder.name("clientes-topic")
                .partitions(3) // número de particiones
                .replicas(1) // número de réplicas
                .build();
    }

}
