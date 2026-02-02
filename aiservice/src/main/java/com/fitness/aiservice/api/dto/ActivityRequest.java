package com.fitness.aiservice.api.dto;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record ActivityRequest(
    String id,
    UUID userId,
    String type,
    Integer duration,
    Integer caloriesBurned,
    Instant startTime,
    Map<String, Object> additionalMetrics,
    Instant createdAt,
    Instant updatedAt) {}
