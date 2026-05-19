package com.example.lyricsflow_backend.controller;

import com.example.lyricsflow_backend.dto.LoginRequestDTO;
import com.example.lyricsflow_backend.dto.UserRegisterRequestDTO;
import com.example.lyricsflow_backend.dto.UserResponseDTO;
import com.example.lyricsflow_backend.dto.UserRegisterResponseDTO;
import com.example.lyricsflow_backend.business.UserBusiness;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserBusiness userBusiness;

    public AuthController(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @PostMapping("/login") // Requisição de login
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(userBusiness.login(request));
    }

    @PostMapping("/register") // Requsição de cadastro de usuário
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody UserRegisterRequestDTO request) {
        return ResponseEntity.ok(userBusiness.register(request));
    }
}
