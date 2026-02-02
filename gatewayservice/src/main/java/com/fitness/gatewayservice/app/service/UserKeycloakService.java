package com.fitness.gatewayservice.app.service;

import com.fitness.gatewayservice.api.dto.UserRequest;
import com.fitness.gatewayservice.api.dto.UserResponse;
import reactor.core.publisher.Mono;

public interface UserKeycloakService {
    Mono<Boolean> validateUser(String userId);
    Mono<UserResponse> registerUser(UserRequest request);
}
