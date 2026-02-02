package com.fitness.aiservice.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiServiceImpl implements GeminiService {
  private final WebClient webClient;

  @Value("${gemini.api.url}")
  private String geminiApiUrl;

  @Value("${gemini.api.key}")
  private String geminiApiKey;

  @Override
  public String getAnswer(String question) {

    try {
      return webClient
          .post()
          .uri(geminiApiUrl)
          .header("x-goog-api-key", geminiApiKey)
          .header("Content-Type", "application/json")
          .bodyValue(buildRequest(question))
          .retrieve()
          .bodyToMono(String.class)
          .block();

    } catch (Exception e) {
      // SIMPLE FIX: swallow Gemini failure
      return "{}";
    }
  }

  private Map<String, Object> buildRequest(String question) {
    return Map.of(
        "contents", new Object[] {Map.of("parts", new Object[] {Map.of("text", question)})});
  }
}
