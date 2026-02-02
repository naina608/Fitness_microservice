package com.fitness.activityservice.app.service;

import com.fitness.activityservice.api.dto.ActivityRequest;
import com.fitness.activityservice.api.dto.ActivityResponse;
import com.fitness.activityservice.api.exception.ActivityNotFoundException;
import com.fitness.activityservice.api.exception.UserNotFoundException;
import com.fitness.activityservice.app.entity.Activity;
import com.fitness.activityservice.app.mapper.ActivityMapper;
import com.fitness.activityservice.app.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
  private final ActivityRepository activityRepository;
  private final ActivityMapper activityMapper;
  private final UserValidationService userValidationService;

      private final RabbitTemplate rabbitTemplate;

      @Value("${rabbitmq.exchange.name}")
      private String exchange;

      @Value("${rabbitmq.routing.key}")
      private String routingKey;

  public ActivityResponse trackActivity(UUID userId, ActivityRequest request) {

    boolean isValidUser = userValidationService.validateUser(userId);
    if (!isValidUser) {
      throw new UserNotFoundException(userId);
    }

    Activity activity = activityMapper.toEntity(request, userId);
    log.info("Mapped activity before save: {}", activity);

    Activity savedActivity = activityRepository.save(activity);

    log.info("Saved activity with id: {}", savedActivity.getId());
    // Publish to RabbitMQ for AI Processing
     try {
         rabbitTemplate.convertAndSend(exchange, routingKey, savedActivity);
     } catch(Exception e) {
         log.error("Failed to publish activity to RabbitMQ : ", e);
     }

    return activityMapper.toActivityResponse(savedActivity);
  }

  public List<ActivityResponse> getUserActivities(UUID userId) {
    List<Activity> activities = activityRepository.findByUserId(userId);
    return activities.stream().map(activityMapper::toActivityResponse).collect(Collectors.toList());
  }

  public ActivityResponse getActivityById(String activityId) {
    return activityRepository
        .findById(activityId)
        .map(activityMapper::toActivityResponse)
        .orElseThrow(
            () -> new ActivityNotFoundException("Activity not found with id: " + activityId));
  }
}
