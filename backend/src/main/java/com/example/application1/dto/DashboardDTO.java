package com.example.application1.dto;

import java.time.LocalDateTime;

public class DashboardDTO {
    private Integer totalTasksCompleted;
    private Float averageTaskScore;
    private Integer totalTargetWords;
    private Integer totalFlashcardsCount;
    private LocalDateTime updatedAt;

    public DashboardDTO() {}

    public DashboardDTO(Integer totalTasksCompleted, Float averageTaskScore, Integer totalTargetWords, 
                        Integer totalFlashcardsCount, LocalDateTime updatedAt) {
        this.totalTasksCompleted = totalTasksCompleted;
        this.averageTaskScore = averageTaskScore;
        this.totalTargetWords = totalTargetWords;
        this.totalFlashcardsCount = totalFlashcardsCount;
        this.updatedAt = updatedAt;
    }

    public Integer getTotalTasksCompleted() { return totalTasksCompleted; }
    public void setTotalTasksCompleted(Integer totalTasksCompleted) { this.totalTasksCompleted = totalTasksCompleted; }

    public Float getAverageTaskScore() { return averageTaskScore; }
    public void setAverageTaskScore(Float averageTaskScore) { this.averageTaskScore = averageTaskScore; }

    public Integer getTotalTargetWords() { return totalTargetWords; }
    public void setTotalTargetWords(Integer totalTargetWords) { this.totalTargetWords = totalTargetWords; }

    public Integer getTotalFlashcardsCount() { return totalFlashcardsCount; }
    public void setTotalFlashcardsCount(Integer totalFlashcardsCount) { this.totalFlashcardsCount = totalFlashcardsCount; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
