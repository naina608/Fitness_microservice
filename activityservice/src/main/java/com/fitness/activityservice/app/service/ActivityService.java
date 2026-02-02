package com.fitness.activityservice.app.service;

import com.fitness.activityservice.api.dto.ActivityRequest;
import com.fitness.activityservice.api.dto.ActivityResponse;

import java.util.List;
import java.util.UUID;

public interface ActivityService {

  ActivityResponse trackActivity(UUID userId, ActivityRequest request);

  List<ActivityResponse> getUserActivities(UUID userId);

  ActivityResponse getActivityById(String activityId);
}
