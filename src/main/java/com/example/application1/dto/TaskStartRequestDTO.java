package com.example.application1.dto;

public class TaskStartRequestDTO {
    private Long userId;
    private Long songId;

    public TaskStartRequestDTO() {}

    public TaskStartRequestDTO(Long userId, Long songId) {
        this.userId = userId;
        this.songId = songId;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getSongId() { return songId; }
    public void setSongId(Long songId) {  this.songId = songId; }
}
