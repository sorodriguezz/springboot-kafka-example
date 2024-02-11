package com.kafka.provider.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProviderConfig {
    // valor del application.properties
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // se recomienda siempre recibir un objeto en el mensaje
    public Map<String, Object> producerConfig() {
        Map<String, Object> properties = new HashMap<>();

        // le decimos donde esta nuestro servidor de kafka
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // cual es el objeto que se encarga de serializar la llave del mensaje a una secuencia de bytes
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // indicar quien serializará el objeto del mensaje
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return properties;
    }

    @Bean
    public ProducerFactory<String, String> providerFactory() {
        // usar el patron factory para el cliente kafka
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        // al ser un bean spring se encargará de inyectar el factory, esto se ocupa por la inyeccion de dependencia
        return new KafkaTemplate<>(producerFactory);
    }

}
