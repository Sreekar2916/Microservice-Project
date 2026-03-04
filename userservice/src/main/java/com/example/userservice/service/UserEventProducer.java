package com.example.userservice.service;

import com.example.userservice.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventProducer {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public void sendUserCreatedEvent(UserCreatedEvent event) {
        kafkaTemplate.send("user-created-v2", event);
    }
}