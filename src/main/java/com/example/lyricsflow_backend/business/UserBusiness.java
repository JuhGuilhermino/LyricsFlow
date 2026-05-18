package com.example.lyricsflow_backend.business;

import com.example.lyricsflow_backend.dto.LoginRequest;
import com.example.lyricsflow_backend.dto.UserRegisterRequest;
import com.example.lyricsflow_backend.dto.UserResponse;
import com.example.lyricsflow_backend.dto.UserRegisterResponse;
import com.example.lyricsflow_backend.model.User;
import com.example.lyricsflow_backend.service.UserService;
import com.example.lyricsflow_backend.service.TaskService;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class UserBusiness {
    private final UserService userService;
    private final TaskService taskService;

    // Construtor
    public UserBusiness(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    // Login
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

    public UserRegisterResponse register(UserRegisterRequest request) {
        // validação básica
        if (userService.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (userService.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username já cadastrado");
        }

        // Criação do novo usuário
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        //user.setAvatarPath(request.getAvatar());
        user.setLevel(request.getLevel());
        user.setGoal(request.getGoal());
        user.setCreatedAt(LocalDateTime.now());

        User saved = userService.save(user);

        // Criação das tarefas
        taskService.createTasksForUser(saved);

        return new UserRegisterResponse(
                saved.getUsername(),
                saved.getEmail(),
                saved.getPassword(),
                //saved.getAvatarPath(),
                saved.getGoal(),
                saved.getLevel()
        );
    }
}
