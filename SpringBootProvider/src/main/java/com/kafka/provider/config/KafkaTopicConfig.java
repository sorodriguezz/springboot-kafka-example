package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic generateTopic() {
        // map para las configuraciones
        Map<String, String> configurations = new HashMap<>();

        // borra el mensaje (el compact mantiene el ultimo mensaje)
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);

        // cuanto será el tiempo que se van a retener los mensajes dentro del tipico (en ms, por defecto es -1 donde acumula todo)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");

        // tamaño maximo de los segmentos dentro del topico (en bytes, por defecto es 1G)
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");

        // tamaño maximo de cada mensaje que voy a soportar (por defecto va en 1MB)
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");

        // nombre de nuestro topico, particiones de nuestro topico y las replicas que debe tener el topico en caso de caer
        return TopicBuilder.name("messages-topic").partitions(2).replicas(2).configs(configurations).build();
    }

}
