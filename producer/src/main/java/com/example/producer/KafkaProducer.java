package com.example.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        String inputTopic = "my-input-topic";
        kafkaTemplate.send(inputTopic, message)
                .addCallback(
                        result -> log.info("Message sent to topic: {}", message),
                        ex -> log.error("Failed to send message", ex)
                );
    }
}
