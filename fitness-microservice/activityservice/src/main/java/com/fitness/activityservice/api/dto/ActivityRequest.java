package com.fitness.activityservice.api.dto;

import com.fitness.activityservice.api.enums.ActivityType;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record ActivityRequest(
    UUID userId,
    ActivityType type,
    Integer duration,
    Integer caloriesBurned,
    Instant startTime,
    Map<String, Object> additionalMetrics) {}
