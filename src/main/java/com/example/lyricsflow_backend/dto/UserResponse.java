package com.example.lyricsflow_backend.dto;

import com.example.lyricsflow_backend.enums.LearningGoal;
import com.example.lyricsflow_backend.enums.LanguageLevel;
import java.time.LocalDateTime;

public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private String avatar;
    private LearningGoal goal;
    private LanguageLevel level;
    private Boolean levelDefinedByTest;
    private LocalDateTime createdAt;
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public LearningGoal getGoal() {
        return goal;
    }
    
    public void setGoal(LearningGoal goal) {
        this.goal = goal;
    }
    
    public LanguageLevel getLevel() {
        return level;
    }
    
    public void setLevel(LanguageLevel level) {
        this.level = level;
    }
    
    public Boolean getLevelDefinedByTest() {
        return levelDefinedByTest;
    }
    
    public void setLevelDefinedByTest(Boolean levelDefinedByTest) {
        this.levelDefinedByTest = levelDefinedByTest;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
