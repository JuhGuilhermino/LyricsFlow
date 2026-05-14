package com.example.lyricsflow_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_progress")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private LocalDateTime lastActivityAt;

    private Integer currentStreak; // Dias consecutivos revisando os flashcards
    private Integer longestStreak; // Recorde de dias seguidos de revisão

    private Integer totalReviews;  // Quantidade de revisões
    private Integer totalCorrectAnswers; // Quantidade de respostas corretas classificadas como EASY

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
