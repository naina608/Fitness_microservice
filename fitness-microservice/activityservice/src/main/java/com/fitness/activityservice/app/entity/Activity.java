package com.fitness.activityservice.app.entity;

import com.fitness.activityservice.api.enums.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Document(collection = "activities")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
  @Id private String id;
  private UUID userId;
  private ActivityType type;
  private Integer duration;
  private Integer caloriesBurned;
  private Instant startTime;

  /**
   * Mapped to MongoDB field "metrics". This annotation is intentionally used to keep the DB field
   * name short and to allow renaming the Java field without changing the existing MongoDB schema.
   */
  @Field("metrics")
  private Map<String, Object> additionalMetrics;

  @CreatedDate private Instant createdAt;

  @LastModifiedDate private Instant updatedAt;
}
