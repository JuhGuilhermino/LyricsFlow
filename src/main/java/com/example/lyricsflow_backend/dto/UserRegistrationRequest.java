package com.example.lyricsflow_backend.dto;

import com.example.lyricsflow_backend.enums.LearningGoal;
import com.example.lyricsflow_backend.enums.LanguageLevel;

public class UserRegistrationRequest {
    private String username;
    private String email;
    private String password;
    private LearningGoal goal;
    private LanguageLevel level;
    private Boolean defineLevelByTest;
    
    // Getters and Setters
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
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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
    
    public Boolean getDefineLevelByTest() {
        return defineLevelByTest;
    }
    
    public void setDefineLevelByTest(Boolean defineLevelByTest) {
        this.defineLevelByTest = defineLevelByTest;
    }
}
