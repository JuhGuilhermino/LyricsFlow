package com.example.lyricsflow_backend.repository;

import com.example.lyricsflow_backend.model.LanguageLevel;
import com.example.lyricsflow_backend.model.LearningGoal;
import com.example.lyricsflow_backend.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    List<Song> findByLevel(LanguageLevel level); //buscarmúsicas pelo nível do usuário

    List<Song> findByGoal(LearningGoal goal); //buscar músicas pelo objetivo

    List<Song> findByLevelAndGoal(LanguageLevel level, LearningGoal goal); //buscar pelo nível e objetivo combinados

    List<Song> findByTitleContainingIgnoreCase(String title); //buscar pelo título 

    List<Song> findByArtistContainingIgnoreCase(String artist); //buscar pelo artista
}