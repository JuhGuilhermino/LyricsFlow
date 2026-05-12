package com.example.lyricsflow_backend.dto;

public class AuthResponse {
    private Integer userId;
    private String username;
    private String token;
    
    public AuthResponse(Integer userId, String username, String token) {
        this.userId = userId;
        this.username = username;
        this.token = token;
    }
    
    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
}
