package com.example.application1.dto;

import com.example.application1.enums.LanguageLevel;

public class UserRegisterRequestDTO {
    private String username;
    private String email;
    private String password;
    private LanguageLevel level;

    // Construtor Padrão
    public UserRegisterRequestDTO() {}

    // Construtor Completo
    public UserRegisterRequestDTO(String username, String email, String password, LanguageLevel level) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.level = level;
    }

    // Getters e Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LanguageLevel getLevel() { return level; }
    public void setLevel(LanguageLevel level) { this.level = level; }
}
