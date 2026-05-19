package com.example.lyricsflow_backend.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class ClozeLineDTO {
    private Integer index;
    private String original;
    private String masked;
}
