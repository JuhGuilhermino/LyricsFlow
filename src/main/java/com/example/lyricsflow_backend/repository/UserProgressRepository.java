package com.example.lyricsflow_backend.repository;

import com.example.lyricsflow_backend.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Integer> {

    Optional<UserProgress> findByUserId(Integer userId);
}
  // Para buscar o progresso de um usuário específico (usado no dashboard)
