package com.example.notificationservice.service;

import com.example.notificationservice.entity.Notification;

public interface EmailService {
    Notification sendEmail(String email, String firstName);
}