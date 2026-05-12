package com.example.lyricsflow_backend.model;

import com.example.lyricsflow_backend.enums.LearningGoal;
import com.example.lyricsflow_backend.enums.LanguageLevel;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LearningGoal goal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LanguageLevel level;

    @Column(name = "level_defined_by_test", nullable = false)
    private Boolean levelDefinedByTest = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ── Getters e Setters ──────────────────────────────────

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public LearningGoal getGoal() { return goal; }
    public void setGoal(LearningGoal goal) { this.goal = goal; }

    public LanguageLevel getLevel() { return level; }
    public void setLevel(LanguageLevel level) { this.level = level; }

    public Boolean getLevelDefinedByTest() { return levelDefinedByTest; }
    public void setLevelDefinedByTest(Boolean levelDefinedByTest) { this.levelDefinedByTest = levelDefinedByTest; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
} 
