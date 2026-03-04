# Microservices Notification System

A Spring Boot Microservices project demonstrating Event-Driven Architecture using Apache Kafka.

## Architecture

User Service publishes events to Kafka when a user registers.

Notification Service consumes the events and sends notifications like Email, SMS, or WhatsApp.

User Service → Kafka → Notification Service

## Tech Stack

- Java
- Spring Boot
- Apache Kafka
- PostgreSQL
- REST APIs
- Maven
- Git

## Microservices

### User Service
Handles user registration and publishes events to Kafka.

### Notification Service
Consumes Kafka events and sends notifications.

### API Gateway
Single entry point for all services.

## Features

- Event Driven Architecture
- Asynchronous Communication using Kafka
- Scalable Microservices
- Notification System (Email / SMS / WhatsApp)

## Project Structure

## How to Run

1. Start Kafka server
2. Run User Service
3. Run Notification Service
4. Send API request to create user
5. Notification service will consume event from Kafka

## Author

Sreekar Varala  
Java Backend Developer
