package com.fitness.userservice.app.service;

import com.fitness.userservice.api.dto.UserRequest;
import com.fitness.userservice.api.dto.UserResponse;
import com.fitness.userservice.api.exception.UserAlreadyExistsException;
import com.fitness.userservice.api.exception.UserNotFoundException;
import com.fitness.userservice.app.entity.User;
import com.fitness.userservice.app.mapper.UserMapper;
import com.fitness.userservice.app.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper mapper;

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
  @Override
  public UserResponse getUser(UUID userId) {
    User user =
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

    return mapper.toUserResponse(user);
  }

  @Override
  @Transactional
  public UserResponse register(UserRequest userRequest) {
    String email = userRequest.email();
    if (userRepository.existsByEmail(email)) {
      User user=userRepository.findByEmail(email);
      return toUserResponse(user);
    }
    User user = mapper.toEntity(userRequest);
    userRepository.save(user);
    return toUserResponse(user);
  }

  @Override
  public Boolean existByUserId(UUID userId) {
    logger.info("Calling User Validation API for userId: {}", userId);
    return userRepository.existsByKeycloakId(userId.toString());
  }
  private UserResponse toUserResponse(User user) {
    return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getKeycloakId(),
            user.getCreatedAt(),
            user.getUpdatedAt()
    );
  }
}
