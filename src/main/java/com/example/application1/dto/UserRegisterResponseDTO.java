package com.example.application1.dto;

import com.example.application1.enums.LanguageLevel;

public class UserRegisterResponseDTO {
    private Long username; // Mantive como Long para respeitar a declaração original do seu tipo
    private String email;
    private String password;
    private LanguageLevel level;

    // Construtor Padrão
    public UserRegisterResponseDTO() {}

    // Construtor Completo
    public UserRegisterResponseDTO(Long username, String email, String password, LanguageLevel level) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.level = level;
    }

    // Getters e Setters
    public Long getUsername() { return username; }
    public void setUsername(Long username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LanguageLevel getLevel() { return level; }
    public void setLevel(LanguageLevel level) { this.level = level; }
}
