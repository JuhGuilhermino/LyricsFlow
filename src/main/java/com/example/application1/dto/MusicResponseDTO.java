package com.example.application1.dto;

public class MusicResponseDTO {
    private Long id;
    private String title;
    private String artist;
    private boolean startTask;
    private String lyrics;

    // Construtor Padrão
    public MusicResponseDTO() {}

    // Construtor Completo
    public MusicResponseDTO(Long id, String title, String artist, boolean startTask) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.startTask = startTask;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public boolean isStartTask() { return startTask; }
    public void setStartTask(boolean startTask) { this.startTask = startTask; }

    public String getLyrics() { return lyrics; }
    public void setLyrics(String lyrics) { this.lyrics = lyrics; }
}
