package com.example.lyricsflow_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "flashcard",
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "word"}))
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String word;

    @Column(name = "next_review_date", nullable = false)
    private LocalDate nextReviewDate;

    @Column(nullable = false)
    private Integer interval;

    @Column(name = "ease_factor", nullable = false)
    private Double easeFactor;

    @Column(name = "learning_progress", nullable = false)
    private Double learningProgress;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt      = LocalDateTime.now();
        this.nextReviewDate = LocalDate.now();
        this.interval       = 1;
        this.easeFactor     = 2.5;
        this.learningProgress = 0.0;
    }

    // ── Getters e Setters ──────────────────────────────────

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }

    public LocalDate getNextReviewDate() { return nextReviewDate; }
    public void setNextReviewDate(LocalDate nextReviewDate) { this.nextReviewDate = nextReviewDate; }

    public Integer getInterval() { return interval; }
    public void setInterval(Integer interval) { this.interval = interval; }

    public Double getEaseFactor() { return easeFactor; }
    public void setEaseFactor(Double easeFactor) { this.easeFactor = easeFactor; }

    public Double getLearningProgress() { return learningProgress; }
    public void setLearningProgress(Double learningProgress) { this.learningProgress = learningProgress; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
} 
    

