package com.fitness.userservice.app.service;

import com.fitness.userservice.api.dto.UserRequest;
import com.fitness.userservice.api.dto.UserResponse;

import java.util.UUID;

public interface UserService {

  UserResponse getUser(UUID userId);

  UserResponse register(UserRequest userRequest);

  Boolean existByUserId(UUID userId);
}
