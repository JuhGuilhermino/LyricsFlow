package com.example.application1.dto;

import java.util.List;

public class TaskGenerateResponseDTO {
    private Long taskId;
    private String maskedLyrics;
    private List<String> targetWords; // gabarito

    // Construtor Padrão
    public TaskGenerateResponseDTO() {}

    // Construtor Completo
    public TaskGenerateResponseDTO(Long taskId, String maskedLyrics, List<String> targetWords) {
        this.taskId = taskId;
        this.maskedLyrics = maskedLyrics;
        this.targetWords = targetWords;
    }

    // Getters e Setters
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }

    public String getMaskedLyrics() { return maskedLyrics; }
    public void setMaskedLyrics(String maskedLyrics) { this.maskedLyrics = maskedLyrics; }

    public List<String> getTargetWords() { return targetWords; }
    public void setTargetWords(List<String> targetWords) { this.targetWords = targetWords; }
}
