package com.example.application1.controller;

import com.example.application1.dto.MusicRequestDTO;
import com.example.application1.dto.MusicResponseDTO;
import com.example.application1.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<?> getAllSongs() {
        try {
            List<MusicResponseDTO> songs = this.songService.listAllSongs();
            return ResponseEntity.ok(songs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao listar as músicas: " + e.getMessage());
        }
    }

    /** ENDPOINT PARA PESQUISAR MÚSICAS    
    @PostMapping("/search")
    public ResponseEntity<?> findOrSaveMusic(@RequestBody MusicRequestDTO request) {
        try {
            MusicResponseDTO response = this.taskService.searchSong(request.getTitle(), request.getArtist());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao processar a requisição da música: " + e.getMessage());
        }
    }
    */
}
