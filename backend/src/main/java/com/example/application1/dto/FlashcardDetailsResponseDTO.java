package com.example.application1.dto;

public class FlashcardDetailsResponseDTO {
    private Long flashcardId;
    private String contextPhrase;
    private String meaning;
    private String partOfSpeech;
    private String exampleUsage;

    public Long getFlashcardId() { return flashcardId; }
    public void setFlashcardId(Long flashcardId) { this.flashcardId = flashcardId; }

    public String getContextPhrase() { return contextPhrase; }
    public void setContextPhrase(String contextPhrase) { this.contextPhrase = contextPhrase; }

    public String getMeaning() { return meaning; }
    public void setMeaning(String meaning) { this.meaning = meaning; }

    public String getPartOfSpeech() { return partOfSpeech; }
    public void setPartOfSpeech(String partOfSpeech) { this.partOfSpeech = partOfSpeech; }

    public String getExampleUsage() { return exampleUsage; }
    public void setExampleUsage(String exampleUsage) { this.exampleUsage = exampleUsage; }
}
