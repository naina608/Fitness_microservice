package com.fitness.activityservice.app.repository;

import com.fitness.activityservice.app.entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
  List<Activity> findByUserId(UUID userId);
}
