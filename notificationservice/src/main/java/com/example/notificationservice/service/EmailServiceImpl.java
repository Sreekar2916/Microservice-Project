package com.example.notificationservice.service;

import com.example.notificationservice.entity.Notification;
import com.example.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final NotificationRepository repository;

    @Override
    public Notification sendEmail(String email, String firstName) {

        Notification notification = new Notification();
        notification.setEmail(email);
        notification.setFirstName(firstName);
        notification.setCreatedAt(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("User Registration Successful");
            message.setText("Hello " + firstName +
                    ",\n\n Hope you're doing well have a nice day.");

            mailSender.send(message);
            notification.setStatus("SENT");

        } catch (Exception e) {
            notification.setStatus("FAILED");
        }

        return repository.save(notification);
    }
}