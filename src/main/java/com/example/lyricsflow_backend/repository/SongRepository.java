package com.example.lyricsflow_backend.repository;

import com.example.lyricsflow_backend.model.Song;
import com.example.lyricsflow_backend.enums.LearningGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByGoal(LearningGoal goal); // Busca música

    List<Song> findByArtistContainingIgnoreCase(String artist); // Busca música pelo artista

    List<Song> findByTitleContainingIgnoreCase(String title); // Busca a música pelo título
}
