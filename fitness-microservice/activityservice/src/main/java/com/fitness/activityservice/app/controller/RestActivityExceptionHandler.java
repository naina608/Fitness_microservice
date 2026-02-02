package com.fitness.activityservice.app.controller;

import com.fitness.activityservice.api.exception.ActivityNotFoundException;
import com.fitness.activityservice.api.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestActivityExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(ActivityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleActivityNotFound(ActivityNotFoundException ex) {
    return ex.getMessage();
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleUserNotFound(UserNotFoundException ex) {
    return ex.getMessage();
  }
}
