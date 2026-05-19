package com.example.lyricsflow_backend.dto;

import java.util.List;

public class DashboardDTO {

    private Integer currentStreak;
    private Integer longestStreak;
    private Integer totalReviews;
    private Integer totalCorrectAnswers;
    private Double accuracyRate;

    private List<TaskResponse> recentTasks;

    private Long totalBlackout;
    private Long totalIncorrect;
    private Long totalHard;
    private Long totalMedium;
    private Long totalEasy;
    private Long totalPerfect;

    public Integer getCurrentStreak() { return currentStreak; }
    public void setCurrentStreak(Integer currentStreak) { this.currentStreak = currentStreak; }

    public Integer getLongestStreak() { return longestStreak; }
    public void setLongestStreak(Integer longestStreak) { this.longestStreak = longestStreak; }

    public Integer getTotalReviews() { return totalReviews; }
    public void setTotalReviews(Integer totalReviews) { this.totalReviews = totalReviews; }

    public Integer getTotalCorrectAnswers() { return totalCorrectAnswers; }
    public void setTotalCorrectAnswers(Integer totalCorrectAnswers) { this.totalCorrectAnswers = totalCorrectAnswers; }

    public Double getAccuracyRate() { return accuracyRate; }
    public void setAccuracyRate(Double accuracyRate) { this.accuracyRate = accuracyRate; }

    public List<TaskResponse> getRecentTasks() { return recentTasks; }
    public void setRecentTasks(List<TaskResponse> recentTasks) { this.recentTasks = recentTasks; }

    public Long getTotalBlackout() { return totalBlackout; }
    public void setTotalBlackout(Long totalBlackout) { this.totalBlackout = totalBlackout; }

    public Long getTotalIncorrect() { return totalIncorrect; }
    public void setTotalIncorrect(Long totalIncorrect) { this.totalIncorrect = totalIncorrect; }

    public Long getTotalHard() { return totalHard; }
    public void setTotalHard(Long totalHard) { this.totalHard = totalHard; }

    public Long getTotalMedium() { return totalMedium; }
    public void setTotalMedium(Long totalMedium) { this.totalMedium = totalMedium; }

    public Long getTotalEasy() { return totalEasy; }
    public void setTotalEasy(Long totalEasy) { this.totalEasy = totalEasy; }

    public Long getTotalPerfect() { return totalPerfect; }
    public void setTotalPerfect(Long totalPerfect) { this.totalPerfect = totalPerfect; }
}