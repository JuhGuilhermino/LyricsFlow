package com.example.application1.dto;

public class FlashcardAnswerDTO {
    private Long flashcardId;
    private String quality;

    public FlashcardAnswerDTO() {}

    public Long getFlashcardId() { return flashcardId; }
    public void setFlashcardId(Long flashcardId) { this.flashcardId = flashcardId; }

    public String getQuality() { return quality; }
    public void setQuality(String quality) { this.quality = quality; }
}