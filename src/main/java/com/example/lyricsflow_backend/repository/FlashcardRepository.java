package com.example.lyricsflow_backend.repository;

import com.example.lyricsflow_backend.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

    List<Flashcard> findByUserId(Long userId); // Retorna todos os flashcards de um usuário

    List<Flashcard> findByNextReviewDateBefore(LocalDate date); // Busca os flashcards com revisão atrasada

    List<Flashcard> findByWordContainingIgnoreCase(String word); // Busca flashcard específico
}
