package com.fitness.userservice.app.mapper;

import com.fitness.userservice.api.dto.UserRequest;
import com.fitness.userservice.api.dto.UserResponse;
import com.fitness.userservice.app.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

    User toEntity(UserRequest userRequest);
}
