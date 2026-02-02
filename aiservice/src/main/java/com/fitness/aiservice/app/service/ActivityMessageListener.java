package com.fitness.aiservice.app.service;

import com.fitness.aiservice.api.dto.ActivityRequest;
import com.fitness.aiservice.app.entity.Recommendation;
import com.fitness.aiservice.app.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityMessageListener {
    private final ActivityAIServiceImpl aiService;
    private static final Logger log =
            LoggerFactory.getLogger(ActivityMessageListener .class);
    private final RecommendationRepository recommendationRepository;

    @RabbitListener(queues = "activity.queue")
    public void processActivity(ActivityRequest activity) {
        log.info("Received activity for processing: {}", activity.id());
        log.info("Generated Recommendation: {}", aiService.generateRecommendation(activity));
        Recommendation recommendation = aiService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }
}
