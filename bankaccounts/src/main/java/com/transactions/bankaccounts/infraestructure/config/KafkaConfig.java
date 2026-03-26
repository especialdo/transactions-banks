package com.transactions.bankaccounts.infraestructure.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.transactions.bankaccounts.infraestructure.adapter.in.kafka.ClienteTopic;

@Configuration
public class KafkaConfig {
    @Bean
    public ConsumerFactory<String, ClienteTopic> clienteConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "bankaccount-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        // 🔥 CONFIG DEL DESERIALIZER
        JsonDeserializer<ClienteTopic> deserializer = new JsonDeserializer<>(ClienteTopic.class);

        Map<String, Object> config = new HashMap<>();
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false); // 👈 LA CLAVE
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, ClienteTopic.class);

        deserializer.configure(config, false);
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ClienteTopic> clienteKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ClienteTopic> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(clienteConsumerFactory());
        return factory;
    }

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
