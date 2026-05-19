package com.example.lyricsflow_backend.controller;

import com.example.lyricsflow_backend.business.SongBusiness;
import com.example.lyricsflow_backend.dto.SongResponseDTO;
import com.example.lyricsflow_backend.enums.LearningGoal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongBusiness songBusiness;

    public SongController(SongBusiness songBusiness) {
        this.songBusiness = songBusiness;
    }

    @GetMapping
    public List<SongResponseDTO> getAll() {
        return songBusiness.getAllSongs();
    }

    @GetMapping("/search/title")
    public List<SongResponseDTO> searchByTitle(@RequestParam String title) {
        return songBusiness.searchByTitle(title);
    }

    @GetMapping("/search/artist")
    public List<SongResponseDTO> searchByArtist(@RequestParam String artist) {
        return songBusiness.searchByArtist(artist);
    }

    @GetMapping("/search/goal")
    public List<SongResponseDTO> searchByGoal(@RequestParam LearningGoal goal) {
        return songBusiness.getByGoal(goal);
    }
}
