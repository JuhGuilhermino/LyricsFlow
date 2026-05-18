package com.example.lyricsflow_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

import com.example.lyricsflow_backend.enums.LanguageLevel;
import com.example.lyricsflow_backend.enums.LearningGoal;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String avatarPath;

    @Enumerated(EnumType.STRING)
    private LearningGoal goal;

    @Enumerated(EnumType.STRING)
    private LanguageLevel level;

    //private Boolean levelDefinedByTest;

    private LocalDateTime createdAt;

    // Relacionamentos
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProgress progress;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    @OneToMany(mappedBy = "user")
    private List<Flashcard> flashcards;
}
