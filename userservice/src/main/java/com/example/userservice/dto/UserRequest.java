package com.example.userservice.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String city;
    private String state;
    private String zip;
}