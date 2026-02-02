package com.fitness.aiservice.app.service;

import com.fitness.aiservice.app.entity.Recommendation;
import com.fitness.aiservice.app.repository.RecommendationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class RecommendationServiceImpl implements RecommendationService {

  private final RecommendationRepository recommendationRepository;

  public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
    this.recommendationRepository = recommendationRepository;
  }

  @Override
  public List<Recommendation> getUserRecommendation(UUID userId) {
    return recommendationRepository.findByUserId(userId);
  }

  @Override
  public Recommendation getActivityRecommendation(String activityId) {
    return recommendationRepository
        .findByActivityId(activityId)
        .orElseThrow(
            () -> new RuntimeException("No recommendation found for this activity: " + activityId));
  }
}
