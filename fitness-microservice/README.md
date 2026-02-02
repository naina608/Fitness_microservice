# Fitness Service

## 📘 Overview

The **Fitness Service** is a microservices-based fitness tracking application that allows users to log physical
activities, receive AI-based workout recommendations, and manage user data securely using **Keycloak authentication**.

The system is built using **Spring Boot + Spring Cloud**, follows best practices for **API Gateway**, **service
discovery**, and **event-driven communication**, and integrates an AI service for personalized recommendations.

---

## 🛠️ Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring Cloud Gateway
- Spring WebFlux
- Spring Security
- Spring Data JPA
- Spring Data MongoDB

### Frontend

- React
- Redux Toolkit
- Axios
- Material UI (MUI)

### Infrastructure & DevOps

- Keycloak (OAuth2 + PKCE)
- Netflix Eureka (Service Discovery)
- Spring Cloud Config Server
- RabbitMQ
- MongoDB
- PostgreSQL
- Docker

### AI Integration

- Gemini API

---

## 📁 Project Structure

```text
fitness-microservice/
│
├── gatewayservice/        # API Gateway + Security + User Sync
├── userservice/           # User Management (PostgreSQL)
├── activityservice/       # Activity Tracking (MongoDB)
├── aiservice/             # AI Recommendations (MongoDB + Gemini API)
├── configserver/          # Centralized Configuration
├── eureka/          # Service Discovery
└── fitness-app-frontend/              # React Application
```

## 🗄️ Database Schema

### User Service (PostgreSQL)

- Users
- Keycloak User Mapping
- User Metadata

### Activity Service (MongoDB)

```text
{
"id": "string",
"userId": UUID,
"type": "RUNNING | WALKING | CYCLING",
"duration": number,
"caloriesBurned": number,
"startTime":timestamp,
"additionalMetrics": {},
"createdAt": "timestamp",
"updatedAt": "timestamp"
}
```

### AI Service (MongoDB)

```text
{
"id": "string",
"activityId":"string",
"userId": UUID,
"activityType": "RUNNING | WALKING | CYCLING",
"recommendation":"string",
"improvements":{},
"suggestions":{},
"safety":{},
"createdAt": "timestamp"
}
```
---
## 📦 Features

- 🔐 Secure authentication with Keycloak (OAuth2 + PKCE)
- 🚪 API Gateway with centralized routing and security
- 👤 Automatic user synchronization from Keycloak
- 🏃 Activity tracking (Running, Walking, Cycling)
- 🤖 AI-based activity recommendations
- 📡 Event-driven architecture using RabbitMQ
- 🧭 Service discovery with Eureka
- ⚙️ Centralized configuration using Config Server

---
## 🌐 REST API Endpoints

### Gateway (Entry Point)

```text
http://localhost:8080
```

### User Service

| Method | Endpoint                   | Description             |
|--------|----------------------------|-------------------------|
| GET    | `/api/users/{userId}`      | Get user details        |
| GET    | `/api/users/{id}/validate` | Validate user existence |
| POST   | `/api/users`               | Register new user       |

### Activity Service

| Method | Endpoint                       | Description                                      |
|--------|--------------------------------|--------------------------------------------------|
| GET    | `/api/activities`              | Fetch all activities for the authenticated user  |
| GET    | `/api/activities/{activityId}` | Fetch a specific activity by its ID              |
| POST   | `/api/activities`              | Create a new activity for the authenticated user |

### AI Recommendation Service

| Method | Endpoint                                     | Description                                   |
|--------|----------------------------------------------|-----------------------------------------------|
| GET    | `/api/recommendations/user/{userId}`         | Get AI recommendations for a specific user    |
| GET    | `/api/recommendations/activity/{activityId}` | Get AI recommendation for a specific activity |

---
## Service Method Summary

### Gateway Service
- JWT extraction and validation
- User auto-registration using KeycloakUserSyncFilter
- Request enrichment with X-User-ID header

### User Service
- User persistence
- Validation APIs
- Keycloak user mapping

### Activity Service
- Activity operations
- Publishes activity events to RabbitMQ

### AI Service
- Listens to activity events
- Calls Gemini API
- Stores AI-generated recommendations

---
## 🧼 Code Formatting

This project uses:

- `google-java-format`

---
## ▶️ Running the Application

### Start Infrastructure
- PostgreSQL
- MongoDB
- RabbitMQ
- Keycloak
- Eureka Server
- Config Server

### Start Services (Recommended Order)
- Config Server
- Eureka Server
- User Service
- Activity Service
- AI Service
- Gateway Service

### Start Frontend
- npm install
- npm run dev