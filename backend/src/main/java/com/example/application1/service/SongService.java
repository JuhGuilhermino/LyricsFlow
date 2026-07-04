package com.example.application1.service;

import com.example.application1.dto.MusicResponseDTO;
import com.example.application1.model.Song;
import com.example.application1.repository.SongRepository;
import com.example.application1.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    private final UserRepository userRepository;
    private final SongRepository songRepository;


    public SongService(UserRepository userRepository, 
                       SongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    
    public List<MusicResponseDTO> listAllSongs() {
        List<Song> songs = this.songRepository.findAll();

        return songs.stream().map(song -> {
            MusicResponseDTO dto = new MusicResponseDTO();
            dto.setId(song.getId());
            dto.setTitle(song.getTitle());
            dto.setArtist(song.getArtist());
            dto.setLyrics(song.getLyrics());
            return dto;
        }).collect(Collectors.toList());
    }

    // Criar um método para buscar músicas
}