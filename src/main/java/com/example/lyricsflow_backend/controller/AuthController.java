package com.example.lyricsflow_backend.controller;

import com.example.lyricsflow_backend.dto.LoginRequest;
import com.example.lyricsflow_backend.dto.UserRegisterRequest;
import com.example.lyricsflow_backend.dto.UserResponse;
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
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userBusiness.login(request));
    }

    @PostMapping("/register") // Requsição de cadastro de usuário
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(userBusiness.register(request));
    }
}
