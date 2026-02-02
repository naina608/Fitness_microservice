package com.fitness.aiservice.app.controller;

import com.fitness.aiservice.app.entity.Recommendation;
import com.fitness.aiservice.app.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {
  private final RecommendationService recommendationService;

  public RecommendationController(RecommendationService recommendationService) {
    this.recommendationService = recommendationService;
  }

  @GetMapping("/user/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public List<Recommendation> getUserRecommendation(@PathVariable UUID userId) {
    return recommendationService.getUserRecommendation(userId);
  }

  @GetMapping("/activity/{activityId}")
  @ResponseStatus(HttpStatus.OK)
  public Recommendation getActivityRecommendation(@PathVariable String activityId) {
    return recommendationService.getActivityRecommendation(activityId);
  }
}
