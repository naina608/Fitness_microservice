package com.fitness.aiservice.app.service;

import com.fitness.aiservice.api.dto.ActivityRequest;
import com.fitness.aiservice.app.entity.Recommendation;

public interface ActivityAIService {
  Recommendation generateRecommendation(ActivityRequest activity);
}
