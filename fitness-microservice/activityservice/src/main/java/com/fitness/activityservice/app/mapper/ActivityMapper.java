package com.fitness.activityservice.app.mapper;

import com.fitness.activityservice.api.dto.ActivityRequest;
import com.fitness.activityservice.api.dto.ActivityResponse;
import com.fitness.activityservice.app.entity.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
  //  @Mapping(target = "userId", source = "userId")

  @Mapping(target = "userId", source = "user")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  Activity toEntity(ActivityRequest request, UUID user);

  ActivityResponse toActivityResponse(Activity activity);
}
