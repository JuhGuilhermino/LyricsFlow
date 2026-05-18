package com.example.lyricsflow_backend.dto;

public class FlashcardReviewDTO {

    private Long flashcardId;
    private String word;
    private String definition;        // gerado pelo Gemini
    private String exampleSentence;   // gerado pelo Gemini
    private String translation;       // gerado pelo Gemini

    public FlashcardReviewDTO() {}

    public FlashcardReviewDTO(Long flashcardId, String word,
                               String definition, String exampleSentence,
                               String translation) {
        this.flashcardId = flashcardId;
        this.word = word;
        this.definition = definition;
        this.exampleSentence = exampleSentence;
        this.translation = translation;
    }

    // getters e setters
    public Long getFlashcardId() { return flashcardId; }
    public void setFlashcardId(Long flashcardId) { this.flashcardId = flashcardId; }

    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }

    public String getDefinition() { return definition; }
    public void setDefinition(String definition) { this.definition = definition; }

    public String getExampleSentence() { return exampleSentence; }
    public void setExampleSentence(String exampleSentence) { this.exampleSentence = exampleSentence; }

    public String getTranslation() { return translation; }
    public void setTranslation(String translation) { this.translation = translation; }
}