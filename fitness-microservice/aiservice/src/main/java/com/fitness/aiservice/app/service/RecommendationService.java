package com.fitness.aiservice.app.service;

import com.fitness.aiservice.app.entity.Recommendation;

import java.util.List;
import java.util.UUID;

public interface RecommendationService {
  List<Recommendation> getUserRecommendation(UUID userId);

  Recommendation getActivityRecommendation(String activityId);
}
