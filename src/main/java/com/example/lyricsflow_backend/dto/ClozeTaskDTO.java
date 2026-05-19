package com.example.lyricsflow_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClozeTaskDTO {
    private String title;
    private String artist;
    private List<ClozeLineDTO> lines;

    //public ClozeTaskDTO(String title, String artist, String maskedLyrics) {
    //    this.title = title;
    //    this.artist =  artist;
    //    private List<ClozeLineDTO> lines;
    //}

}
