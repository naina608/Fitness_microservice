package com.fitness.activityservice.api.exception;

public class ActivityNotFoundException extends RuntimeException {
  public ActivityNotFoundException(String message) {
    super(message);
  }
}
