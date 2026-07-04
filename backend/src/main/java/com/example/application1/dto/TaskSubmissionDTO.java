package com.example.application1.dto;

import java.util.List;

public class TaskSubmissionDTO {
    private Long taskId;
    private Long userId;
    private List<String> userAnswers;

    // Construtor Padrão
    public TaskSubmissionDTO() {}

    // Construtor Completo
    public TaskSubmissionDTO(Long taskId, Long userId, List<String> userAnswers) {
        this.taskId = taskId;
        this.userId = userId;
        this.userAnswers = userAnswers;
    }

    // Getters e Setters
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<String> getUserAnswers() { return userAnswers; }
    public void setUserAnswers(List<String> userAnswers) { this.userAnswers = userAnswers; }
}
