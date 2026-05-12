package com.example.lyricsflow_backend.model;

import com.example.lyricsflow_backend.enums.LearningGoal;
import com.example.lyricsflow_backend.enums.LanguageLevel;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String artist;

    @Column(columnDefinition = "TEXT")
    private String album;

    private Integer year;

    @Column(name = "youtube_id", length = 50)
    private String youtubeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LearningGoal goal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LanguageLevel level;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("lineNum ASC")
    private List<LyricsLine> lines;

    // ── Getters e Setters ──────────────────────────────────

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public String getYoutubeId() { return youtubeId; }
    public void setYoutubeId(String youtubeId) { this.youtubeId = youtubeId; }

    public LearningGoal getGoal() { return goal; }
    public void setGoal(LearningGoal goal) { this.goal = goal; }

    public LanguageLevel getLevel() { return level; }
    public void setLevel(LanguageLevel level) { this.level = level; }

    public List<LyricsLine> getLines() { return lines; }
    public void setLines(List<LyricsLine> lines) { this.lines = lines; }
} 
