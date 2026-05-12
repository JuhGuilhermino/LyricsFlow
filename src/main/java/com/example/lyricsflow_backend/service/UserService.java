package com.example.lyricsflow_backend.service;

import com.example.lyricsflow_backend.dto.UserRegistrationRequest;
import com.example.lyricsflow_backend.dto.UserResponse;
import com.example.lyricsflow_backend.model.User;
import com.example.lyricsflow_backend.model.UserProgress;
import com.example.lyricsflow_backend.repository.UserRepository;
import com.example.lyricsflow_backend.repository.UserProgressRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final UserProgressRepository userProgressRepository;
    
    public UserService(UserRepository userRepository, UserProgressRepository userProgressRepository) {
        this.userRepository = userRepository;
        this.userProgressRepository = userProgressRepository;
    }
    
    public UserResponse register(UserRegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(hashPassword(request.getPassword()));
        user.setGoal(request.getGoal());
        user.setLevel(request.getLevel());
        user.setLevelDefinedByTest(request.getDefineLevelByTest());
        
        User savedUser = userRepository.save(user);
        
        // Create user progress
        UserProgress progress = new UserProgress();
        progress.setUser(savedUser);
        userProgressRepository.save(progress);
        
        return convertToResponse(savedUser);
    }
    
    public UserResponse getUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(this::convertToResponse).orElse(null);
    }
    
    public UserResponse updateUserProfile(Integer userId, UserRegistrationRequest request) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (request.getLevel() != null) {
                user.setLevel(request.getLevel());
            }
            if (request.getDefineLevelByTest() != null) {
                user.setLevelDefinedByTest(request.getDefineLevelByTest());
            }
            if (request.getGoal() != null) {
                user.setGoal(request.getGoal());
            }
            User updatedUser = userRepository.save(user);
            return convertToResponse(updatedUser);
        }
        return null;
    }
    
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setAvatar(user.getAvatar());
        response.setGoal(user.getGoal());
        response.setLevel(user.getLevel());
        response.setLevelDefinedByTest(user.getLevelDefinedByTest());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
    
    private String hashPassword(String password) {
        // Simple hash for academic project - DO NOT use in production
        return Integer.toHexString(password.hashCode());
    }
    
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return hashPassword(rawPassword).equals(hashedPassword);
    }
}
