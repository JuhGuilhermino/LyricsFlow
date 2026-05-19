package com.example.lyricsflow_backend.repository;

import com.example.lyricsflow_backend.enums.FlashcardAnswerQuality;
import com.example.lyricsflow_backend.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findByUserId(Long userId);
    List<Flashcard> findByNextReviewDateBefore(LocalDate date);
    List<Flashcard> findByWordContainingIgnoreCase(String word);

    @Query("SELECT f.lastQuality, COUNT(f) FROM Flashcard f WHERE f.user.id = :userId GROUP BY f.lastQuality")
    List<Object[]> countQualitiesByUserId(@Param("userId") Long userId);
}