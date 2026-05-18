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
    // 1. Simula exatamente a String JSON estruturada que o Gemini retornaria
    String jsonSimulado = """
        {
          "definition": "A wild and uncultivated region, as of forest or desert, left in its natural state.",
          "exampleSentence": "The hikers spent three days exploring the vast wilderness.",
          "translation": "Região selvagem / deserto"
        }
        """;

    try {
        // 2. Executa o mesmo mapeamento via Jackson que você já implementou
        Map<String, String> parsed = objectMapper.readValue(jsonSimulado, Map.class);

        // 3. Monta e retorna o DTO esperado pelo seu Controller
        return new FlashcardReviewDTO(
            flashcardId,
            word,
            parsed.get("definition"),
            parsed.get("exampleSentence"),
            parsed.get("translation")
        );

    } catch (Exception e) {
        throw new GeminiIntegrationException("Erro ao processar o Mock de teste: " + e.getMessage());
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

            // Remove possíveis blocos de markdown que o Gemini pode retornar
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