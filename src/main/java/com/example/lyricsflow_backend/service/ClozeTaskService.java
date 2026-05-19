package com.example.lyricsflow_backend.service;

import com.example.lyricsflow_backend.dto.ClozeLineDTO;
import com.example.lyricsflow_backend.dto.ClozeTaskDTO;
import com.example.lyricsflow_backend.enums.LanguageLevel;
import com.example.lyricsflow_backend.model.Song;
import com.example.lyricsflow_backend.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClozeTaskService {

    private final SongRepository songRepository;

    public ClozeTaskService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public ClozeTaskDTO generateTask(Long songId, LanguageLevel level) {

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        List<ClozeLineDTO> lines = buildLines(song.getLyrics(), song.getMask(), level);

        return new ClozeTaskDTO(
                song.getTitle(),
                song.getArtist(),
                lines
        );
    }

    private List<ClozeLineDTO> buildLines(String lyrics, String mask, LanguageLevel level) {

        if (lyrics == null || mask == null) {
            throw new RuntimeException("Lyrics or mask is null");
        }

        String[] lyricLines = lyrics.split("\\n");
        String[] maskLines = mask.split("\\n");

        int levelValue = mapLevel(level);

        List<ClozeLineDTO> result = new ArrayList<>();

        int size = Math.min(lyricLines.length, maskLines.length);

        for (int i = 0; i < size; i++) {

            String masked = applyMask(lyricLines[i], maskLines[i], levelValue);

            result.add(new ClozeLineDTO(
                    i,
                    lyricLines[i],
                    masked
            ));
        }

        return result;
    }

    private String applyMask(String line, String mask, int levelValue) {

        String[] words = line.split("\\s+");
        String[] maskValues = mask.split("\\s+");

        StringBuilder result = new StringBuilder();

        int size = Math.min(words.length, maskValues.length);

        for (int i = 0; i < size; i++) {

            int value = safeParseMask(maskValues[i]);

            if (value >= levelValue) {
                result.append("___");
            } else {
                result.append(words[i]);
            }

            if (i < size - 1) {
                result.append(" ");
            }
        }

        // se sobrar palavra sem máscara, mantém original
        for (int i = size; i < words.length; i++) {
            result.append(" ").append(words[i]);
        }

        return result.toString().trim();
    }

    /**
     * Evita crash do sistema por máscara inválida.
     */
    private int safeParseMask(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            // fallback seguro: não oculta palavra
            return 0;
        }
    }

    private int mapLevel(LanguageLevel level) {
        return switch (level) {
            case BEGINNER -> 1;
            case INTERMEDIATE -> 2;
            case ADVANCED -> 3;
        };
    }
}