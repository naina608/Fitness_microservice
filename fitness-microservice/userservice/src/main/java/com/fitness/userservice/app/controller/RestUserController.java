package com.fitness.userservice.app.controller;

import com.fitness.userservice.api.dto.UserRequest;
import com.fitness.userservice.api.dto.UserResponse;
import com.fitness.userservice.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/users"})
public class RestUserController {

  private final UserService userService;

  @GetMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse getUserProfile(@PathVariable UUID userId) {
    return userService.getUser(userId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void register(@Valid @RequestBody UserRequest userRequest) {
    userService.register(userRequest);
  }

  @GetMapping("/{userId}/validate")
  @ResponseStatus(HttpStatus.OK)
  public Boolean validateUser(@PathVariable UUID userId) {
    log.info("Validating user with id {}", userId);  // Add this log
    return userService.existByUserId(userId);
  }
}
