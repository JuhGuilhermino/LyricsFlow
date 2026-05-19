package com.example.lyricsflow_backend.dto;

import com.example.lyricsflow_backend.enums.LearningGoal;

public class SongResponseDTO {

    private Long id;
    private String title;
    private String artist;
    private LearningGoal goal;

    public SongResponseDTO(Long id, String title, String artist, LearningGoal goal) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.goal = goal;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public LearningGoal getGoal() { return goal; }
}