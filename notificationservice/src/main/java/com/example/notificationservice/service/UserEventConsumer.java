package com.example.notificationservice.service;

import com.example.notificationservice.enums.NotificationType;
import com.example.notificationservice.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEventConsumer {

    private final JavaMailSender mailSender;

    @KafkaListener(topics = "user-created-v2", groupId = "notification-group")
    public void consume(UserCreatedEvent event) {

        log.info("Received event for user: {}", event.getEmail());

        if (event.getNotificationType() == NotificationType.EMAIL) {
            sendEmail(event);
        }
    }

    private void sendEmail(UserCreatedEvent event) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getEmail());
        message.setSubject("Welcome to Our Platform");
        message.setText("Hi " + event.getFirstName() + ", your account has been created successfully.");

        mailSender.send(message);

        log.info("Email sent successfully to {}", event.getEmail());
    }
}