package com.fitness.aiservice.app.repository;

import com.fitness.aiservice.app.entity.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
    List<Recommendation> findByUserId(UUID userId);
    Optional<Recommendation> findByActivityId(String activityId);
}