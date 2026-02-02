package com.fitness.activityservice.app.controller;

import com.fitness.activityservice.api.dto.ActivityRequest;
import com.fitness.activityservice.api.dto.ActivityResponse;
import com.fitness.activityservice.app.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {

  private ActivityService activityService;

  /**
   * Creates a new activity for the currently authenticated user.
   *
   * <p>The user identity is not taken from the URL or request body. Instead, it is provided via the
   * `X-User-ID` request header by the authentication layer / API gateway to prevent client-side
   * tampering.
   */
  @PostMapping
  public ResponseEntity<ActivityResponse> trackActivity(
      @RequestBody ActivityRequest request, @RequestHeader("X-User-ID") UUID userId) {
    return ResponseEntity.ok(activityService.trackActivity(userId,request));
  }

  @GetMapping
  public ResponseEntity<List<ActivityResponse>> getUserActivities(
      @RequestHeader("X-User-ID") UUID userId) {
    return ResponseEntity.ok(activityService.getUserActivities(userId));
  }

  @GetMapping("/{activityId}")
  public ResponseEntity<ActivityResponse> getActivity(@PathVariable String activityId) {
    return ResponseEntity.ok(activityService.getActivityById(activityId));
  }
}
