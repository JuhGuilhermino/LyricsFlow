package com.example.lyricsflow_backend.dto;

import com.example.lyricsflow_backend.enums.LanguageLevel;
import com.example.lyricsflow_backend.enums.LearningGoal;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String username;
    private String email;
    private String password;
    private LearningGoal goal;
    private LanguageLevel level;
}
