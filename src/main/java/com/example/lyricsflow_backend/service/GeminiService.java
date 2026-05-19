package com.example.lyricsflow_backend.service;

import com.example.lyricsflow_backend.dto.FlashcardReviewDTO;
import com.example.lyricsflow_backend.exception.GeminiIntegrationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GeminiService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public FlashcardReviewDTO generateFlashcardContent(Long flashcardId, String word, String userLevel) {
    String prompt = buildPrompt(word, userLevel);

    Map<String, Object> requestBody = Map.of(
        "contents", List.of(
            Map.of("parts", List.of(
                Map.of("text", prompt)
            ))
        )
    );

    String url = apiUrl + "?key=" + apiKey;

    try {
        ResponseEntity<Map> response = restTemplate.postForEntity(url, requestBody, Map.class);
        return parseResponse(response.getBody(), flashcardId, word);
    } catch (Exception e) {
        throw new GeminiIntegrationException("Falha ao chamar a API do Gemini: " + e.getMessage());
    }
}

    private String buildPrompt(String word, String userLevel) {
        return """
            You are an English language learning assistant.
            Generate a flashcard for the English word "%s" for a %s level student.

            Respond ONLY with a valid JSON object, no markdown, no code blocks, no explanation.
            Use exactly this format:
            {"definition":"simple definition in English","exampleSentence":"one sentence using the word in context","translation":"Portuguese translation of the word"}
            """.formatted(word, userLevel);
    }

    @SuppressWarnings("unchecked")
    private FlashcardReviewDTO parseResponse(Map<?, ?> body, Long flashcardId, String word) {
        try {
            List<Map<?, ?>> candidates = (List<Map<?, ?>>) body.get("candidates");
            Map<?, ?> content = (Map<?, ?>) candidates.get(0).get("content");
            List<Map<?, ?>> parts = (List<Map<?, ?>>) content.get("parts");
            String rawText = (String) parts.get(0).get("text");

            String jsonText = rawText
                .replaceAll("```json", "")
                .replaceAll("```", "")
                .trim();

            Map<String, String> parsed = objectMapper.readValue(jsonText, Map.class);

            return new FlashcardReviewDTO(
                flashcardId,
                word,
                parsed.get("definition"),
                parsed.get("exampleSentence"),
                parsed.get("translation")
            );

        } catch (Exception e) {
            throw new GeminiIntegrationException("Erro ao processar resposta do Gemini: " + e.getMessage());
        }
    }
}