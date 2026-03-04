package com.example.notificationservice.controller;

import com.example.notificationservice.dto.*;
import com.example.notificationservice.entity.Notification;
import com.example.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final EmailService emailService;

    @PostMapping("/notify")
    public ResponseEntity<NotificationResponse> notifyUser(
            @RequestBody NotificationRequest request) {

        Notification notification =
                emailService.sendEmail(
                        request.getEmail(),
                        request.getFirstName()
                );

        return ResponseEntity.ok(
                NotificationResponse.builder()
                        .email(notification.getEmail())
                        .status(notification.getStatus())
                        .build()
        );
    }
}