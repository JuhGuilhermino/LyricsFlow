package com.example.lyricsflow_backend.service;

import org.springframework.stereotype.Service;

import com.example.lyricsflow_backend.enums.LearningGoal;
import com.example.lyricsflow_backend.model.Song;
import com.example.lyricsflow_backend.repository.SongRepository;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getSongsByGoal(LearningGoal goal) {
        return songRepository.findByGoal(goal);
    }

    public List<Song> searchByArtist(String artist) {
        return songRepository.findByArtistContainingIgnoreCase(artist);
    }

    public List<Song> searchByTitle(String title) {
        return songRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
}
