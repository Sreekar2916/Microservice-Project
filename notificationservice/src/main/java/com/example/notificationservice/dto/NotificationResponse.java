package com.example.notificationservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponse {
    private String email;
    private String status;

}