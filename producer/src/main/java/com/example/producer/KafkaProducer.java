package com.example.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${myapp.input-topic}")
    String inputTopic;

    public void sendMessage(String message) {
        kafkaTemplate.send(inputTopic, message)
                .addCallback(
                        result -> log.info("Message sent to topic: {}", message),
                        ex -> log.error("Failed to send message", ex)
                );
    }
}
