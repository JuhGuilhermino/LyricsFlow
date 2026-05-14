package com.example.lyricsflow_backend.model;

import jakarta.persistence.*;
import lombok.*;

import com.example.lyricsflow_backend.enums.LearningGoal;

@Entity
@Table(name = "songs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String artist;

    @Enumerated(EnumType.STRING)
    private LearningGoal goal;

    @Column(columnDefinition = "TEXT")
    private String lyrics;

    @Column(columnDefinition = "TEXT")
    private String mask;
}
