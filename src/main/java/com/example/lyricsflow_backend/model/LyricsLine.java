package com.example.lyricsflow_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lyrics_line",
       uniqueConstraints = @UniqueConstraint(columnNames = {"song_id", "line_num"}))
public class LyricsLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @Column(name = "line_num", nullable = false)
    private Integer lineNum;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String english;

    @Column(columnDefinition = "TEXT")
    private String portuguese;

    @Column(name = "english_mask_beginner", columnDefinition = "TEXT")
    private String englishMaskBeginner;

    @Column(name = "english_mask_intermediate", columnDefinition = "TEXT")
    private String englishMaskIntermediate;

    @Column(name = "english_mask_advanced", columnDefinition = "TEXT")
    private String englishMaskAdvanced;

    // ── Getters e Setters ──────────────────────────────────

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Song getSong() { return song; }
    public void setSong(Song song) { this.song = song; }

    public Integer getLineNum() { return lineNum; }
    public void setLineNum(Integer lineNum) { this.lineNum = lineNum; }

    public String getEnglish() { return english; }
    public void setEnglish(String english) { this.english = english; }

    public String getPortuguese() { return portuguese; }
    public void setPortuguese(String portuguese) { this.portuguese = portuguese; }

    public String getEnglishMaskBeginner() { return englishMaskBeginner; }
    public void setEnglishMaskBeginner(String englishMaskBeginner) { this.englishMaskBeginner = englishMaskBeginner; }

    public String getEnglishMaskIntermediate() { return englishMaskIntermediate; }
    public void setEnglishMaskIntermediate(String englishMaskIntermediate) { this.englishMaskIntermediate = englishMaskIntermediate; }

    public String getEnglishMaskAdvanced() { return englishMaskAdvanced; }
    public void setEnglishMaskAdvanced(String englishMaskAdvanced) { this.englishMaskAdvanced = englishMaskAdvanced; }
}