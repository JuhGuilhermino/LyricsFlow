package com.example.lyricsflow_backend.business;

import com.example.lyricsflow_backend.dto.SongResponseDTO;
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

    private SongResponseDTO toDto(Song song) {
        return new SongResponseDTO(
                song.getId(),
                song.getTitle(),
                song.getArtist(),
                song.getGoal()
        );
    }

    public List<SongResponseDTO> getAllSongs() {
        return songService.getAllSongs()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<SongResponseDTO> searchByTitle(String title) {
        return songService.searchByTitle(title)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<SongResponseDTO> searchByArtist(String artist) {
        return songService.searchByArtist(artist)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<SongResponseDTO> getByGoal(LearningGoal goal) {
        return songService.getSongsByGoal(goal)
                .stream()
                .map(this::toDto)
                .toList();
    }
}
