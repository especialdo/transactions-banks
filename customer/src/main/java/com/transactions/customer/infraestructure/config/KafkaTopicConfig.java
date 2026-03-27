package com.transactions.customer.infraestructure.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:29092");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic clientesTopic() {
        return TopicBuilder.name("clientes-topic")
                .partitions(3) // número de particiones
                .replicas(1) // número de réplicas
                .build();
    }

}
