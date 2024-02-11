package com.kafka.consumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerListener {
    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    // etiqueta para escuchar mensajes que se envian, el groupId es para formar grupos de consumidores
    @KafkaListener(topics = {"messages-topic"}, groupId = "mi-id-grupo")
    public void listener(String message) {
        LOGGER.info("Mensaje recibido, el mensaje es: " + message);
    }
}
