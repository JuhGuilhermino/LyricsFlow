package com.example.lyricsflow_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.lyricsflow_backend.dto.UserRegistrationRequest;
import com.example.lyricsflow_backend.dto.UserResponse;
import com.example.lyricsflow_backend.dto.LoginRequest;
import com.example.lyricsflow_backend.dto.AuthResponse;
import com.example.lyricsflow_backend.service.UserService;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    
    private final UserService userService;
    
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegistrationRequest request) {
        UserResponse response = userService.register(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        var user = userService.findByUsername(request.getUsername());
        
        if (user.isPresent() && userService.verifyPassword(request.getPassword(), user.get().getPasswordHash())) {
            // Simple token generation for academic project
            String token = UUID.randomUUID().toString();
            AuthResponse response = new AuthResponse(user.get().getId(), user.get().getUsername(), token);
            return ResponseEntity.ok(response);
        }
        
        return ResponseEntity.status(401).build();
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer userId) {
        UserResponse response = userService.getUserById(userId);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}
