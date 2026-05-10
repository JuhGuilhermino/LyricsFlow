package com.example.lyricsflow_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_progress")
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "tasks_completed", nullable = false)
    private Integer tasksCompleted = 0;

    @Column(name = "words_learned", nullable = false)
    private Integer wordsLearned = 0;

    @Column(name = "flash_cards_progress", nullable = false)
    private Double flashCardsProgress = 0.0;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ── Getters e Setters ──────────────────────────────────

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Integer getTasksCompleted() { return tasksCompleted; }
    public void setTasksCompleted(Integer tasksCompleted) { this.tasksCompleted = tasksCompleted; }

    public Integer getWordsLearned() { return wordsLearned; }
    public void setWordsLearned(Integer wordsLearned) { this.wordsLearned = wordsLearned; }

    public Double getFlashCardsProgress() { return flashCardsProgress; }
    public void setFlashCardsProgress(Double flashCardsProgress) { this.flashCardsProgress = flashCardsProgress; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
} 

