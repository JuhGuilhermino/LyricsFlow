package com.example.lyricsflow_backend.repository;

import com.example.lyricsflow_backend.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {

    Optional<UserProgress> findByUserId(Long userId); // Retorna os dados do progresso do usuário
}
