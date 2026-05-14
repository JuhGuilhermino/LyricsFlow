package com.example.lyricsflow_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse { // Se o login é validado, esses são os dados retornados
    private Long id;
    private String username;
    private String email;
}
