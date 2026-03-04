package com.example.userservice.service;

import com.example.userservice.dto.*;
import com.example.userservice.entity.User;
import com.example.userservice.enums.NotificationType;
import com.example.userservice.event.UserCreatedEvent;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final WebClient.Builder webClientBuilder;
    private final UserEventProducer eventProducer;

    @Override
    public UserResponse createUser(UserRequest request) {

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMobileNumber(request.getMobileNumber());
        user.setEmail(request.getEmail());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZip(request.getZip());

        User savedUser = repository.save(user);

        // 🔥 Create Kafka Event
        UserCreatedEvent event = new UserCreatedEvent(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getEmail(),
                NotificationType.EMAIL
        );

        // 🔥 Send to Kafka
        eventProducer.sendUserCreatedEvent(event);

        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .email(savedUser.getEmail())
                .message("User created successfully")
                .build();

//        NotificationRequest notificationRequest =
//                new NotificationRequest(
//                        savedUser.getFirstName(),
//                        savedUser.getEmail()
//                );

//        webClientBuilder.build()
//                .post()
//                .uri("http://notification-service/notify")
//                .bodyValue(notificationRequest)
//                .retrieve()
//                .bodyToMono(UserResponse.class)  //  FIXED
//                .subscribe();
//
//        return UserResponse.builder()
//                .id(savedUser.getId())
//                .firstName(savedUser.getFirstName())
//                .email(savedUser.getEmail())
//                .message("User created successfully")
//                .build();
    }
    @Override
    public UserResponse getUserById(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id));

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .message("User fetched successfully")
                .build();
    }
    @Override
    public UserResponse updateUser(Long id, UserRequest request) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMobileNumber(request.getMobileNumber());
        user.setEmail(request.getEmail());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZip(request.getZip());

        User updatedUser = repository.save(user);

        return UserResponse.builder()
                .id(updatedUser.getId())
                .firstName(updatedUser.getFirstName())
                .email(updatedUser.getEmail())
                .message("User updated successfully")
                .build();
    }
    @Override
    public void deleteUser(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id));

        repository.delete(user);
    }
    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}