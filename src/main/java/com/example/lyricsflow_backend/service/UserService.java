package com.example.lyricsflow_backend.service;

import com.example.lyricsflow_backend.model.User;
import com.example.lyricsflow_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService { // Interage com a inteface do Repository para realizar a persistência dos dados
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Busca referencia de um usuário pelo email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Salva um novo usuário no banco
    public User save(User user) {
        return userRepository.save(user);
    }

    // Cunsulta se já existe um usuário com esse email
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Consulta se já existe um usuário com esse username
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
