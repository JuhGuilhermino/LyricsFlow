package com.example.lyricsflow_backend.controller;

import com.example.lyricsflow_backend.dto.ClozeTaskDTO;
import com.example.lyricsflow_backend.enums.LanguageLevel;
import com.example.lyricsflow_backend.service.ClozeTaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cloze")
public class ClozeTaskController {

    private final ClozeTaskService service;

    public ClozeTaskController(ClozeTaskService service) {
        this.service = service;
    }

    @GetMapping("/{songId}")
    public ClozeTaskDTO generate(
            @PathVariable Long songId,
            @RequestParam LanguageLevel level
    ) {
        return service.generateTask(songId, level);
    }
}
