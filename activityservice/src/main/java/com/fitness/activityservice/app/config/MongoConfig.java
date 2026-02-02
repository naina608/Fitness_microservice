package com.fitness.activityservice.app.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Enables MongoDB auditing support.
 *
 * <p>This configuration is required for Spring Data MongoDB to automatically populate auditing
 * fields such as {@code @CreatedDate} and {@code @LastModifiedDate} on document persist and update
 * operations.
 *
 * <p>Without this configuration, auditing annotations on Mongo entities will be ignored.
 */
@Configuration
@EnableMongoAuditing
public class MongoConfig {
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
                MongoClientSettings.builder()
                        .uuidRepresentation(UuidRepresentation.STANDARD) // important
                        .build()
        );
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "fitness_activity");
    }
}
