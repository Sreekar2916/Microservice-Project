package com.example.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFoundException ex) {

        return new ResponseEntity<>(
                new ErrorResponse(
                        LocalDateTime.now(),
                        ex.getMessage(),
                        "USER NOT FOUND"
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception ex) {

        return new ResponseEntity<>(
                new ErrorResponse(
                        LocalDateTime.now(),
                        ex.getMessage(),
                        "INTERNAL SERVER ERROR"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

@Data
@AllArgsConstructor
class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}