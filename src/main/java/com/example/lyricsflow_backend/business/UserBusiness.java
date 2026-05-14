package com.example.lyricsflow_backend.business;

import com.example.lyricsflow_backend.dto.LoginRequest;
import com.example.lyricsflow_backend.dto.UserResponse;
import com.example.lyricsflow_backend.model.User;
import com.example.lyricsflow_backend.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserBusiness {
    private final UserService userService;

    public UserBusiness(UserService userService) {
        this.userService = userService;
    }

    public UserResponse login(LoginRequest request) { // Recebe a requsição

        User user = userService.findByEmail(request.getEmail()) // Solicita e vefica a busca no banco
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
