package com.example.lyricsflow_backend.dto;

import lombok.Data;

@Data
public class TaskResponse {
    private Long id;
    private Float score;
    private String songTitle;
    private String artist;
    private String completedAt;

    public TaskResponse(Long id, Float score, String songTitle, String artist, String completedAt) {
        this.id = id;
        this.score = score;
        this.songTitle = songTitle;
        this.artist = artist;
        this.completedAt = completedAt;
    }
}
