package com.example.application1.controller;

import com.example.application1.dto.LoginRequestDTO;
import com.example.application1.dto.UserRegisterRequestDTO;
import com.example.application1.dto.UserResponseDTO;
import com.example.application1.service.UserService;
import com.example.application1.dto.UserRegisterResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {
    private final UserService userService;

    // Chama a classe UserService para validar as regras de negócio
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody UserRegisterRequestDTO request) {
        UserRegisterResponseDTO response = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO request) {
        UserResponseDTO response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}
