package com.example.application1.dto;

import com.example.application1.enums.LanguageLevel;

public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private LanguageLevel level;

    // Construtor Padrão
    public UserResponseDTO() {}

    // Construtor Completo
    public UserResponseDTO(Long id, String username, String email, LanguageLevel level) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.level = level;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LanguageLevel getLevel() { return level; }
    public void setLevel(LanguageLevel level) { this.level = level; }
}
