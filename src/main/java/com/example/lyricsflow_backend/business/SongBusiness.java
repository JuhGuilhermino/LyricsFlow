package com.example.lyricsflow_backend.business;

import com.example.lyricsflow_backend.dto.SongResponse;
import com.example.lyricsflow_backend.enums.LearningGoal;
import com.example.lyricsflow_backend.model.Song;
import com.example.lyricsflow_backend.service.SongService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SongBusiness {

    private final SongService songService;

    public SongBusiness(SongService songService) {
        this.songService = songService;
    }

    private SongResponse toDto(Song song) {
        return new SongResponse(
                song.getId(),
                song.getTitle(),
                song.getArtist(),
                song.getGoal()
        );
    }

    public List<SongResponse> getAllSongs() {
        return songService.getAllSongs()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<SongResponse> searchByTitle(String title) {
        return songService.searchByTitle(title)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<SongResponse> searchByArtist(String artist) {
        return songService.searchByArtist(artist)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<SongResponse> getByGoal(LearningGoal goal) {
        return songService.getSongsByGoal(goal)
                .stream()
                .map(this::toDto)
                .toList();
    }
}
