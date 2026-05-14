package com.example.lyricsflow_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.lyricsflow_backend.enums.FlashcardAnswerQuality;

@Entity
@Table(name = "flashcards")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String word;

    private Integer interval;

    private LocalDate nextReviewDate;

    private Float easeFactor;

    @Enumerated(EnumType.STRING)
    private FlashcardAnswerQuality lastQuality;

    private LocalDateTime createdAt;
}
