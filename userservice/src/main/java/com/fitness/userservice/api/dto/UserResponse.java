package com.fitness.userservice.api.dto;

import java.time.Instant;
import java.util.UUID;

public record UserResponse(
    UUID id,
    String email,
    String firstName,
    String lastName,
    String keycloakId,
    Instant createdAt,
    Instant updatedAt) {}
