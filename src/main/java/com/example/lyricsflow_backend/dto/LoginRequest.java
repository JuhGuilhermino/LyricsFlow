package com.example.lyricsflow_backend.dto;

import lombok.Data;

@Data
public class LoginRequest { // Dados inseridos no login
    private String email;
    private String password;
}
