package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka

public class UserserviceApplicatioin {

    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplicatioin.class, args);
    }

}




