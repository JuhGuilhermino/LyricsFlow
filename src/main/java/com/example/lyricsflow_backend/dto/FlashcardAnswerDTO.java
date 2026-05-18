package com.example.lyricsflow_backend.dto;

public class FlashcardAnswerDTO {
    private Long flashcardId;
    private String quality; // BLACKOUT, INCORRECT, HARD, MEDIUM, EASY, PERFECT

    public Long getFlashcardId() { return flashcardId; }
    public void setFlashcardId(Long flashcardId) { this.flashcardId = flashcardId; }

    public String getQuality() { return quality; }
    public void setQuality(String quality) { this.quality = quality; }
}