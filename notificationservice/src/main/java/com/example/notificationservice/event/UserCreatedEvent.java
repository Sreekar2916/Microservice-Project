package com.example.notificationservice.event;

import com.example.notificationservice.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreatedEvent {

    private Long userId;
    private String firstName;
    private String email;
    private NotificationType notificationType;
}