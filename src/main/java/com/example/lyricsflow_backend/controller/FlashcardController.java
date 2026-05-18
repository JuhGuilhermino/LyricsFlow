package com.example.lyricsflow_backend.controller;

import com.example.lyricsflow_backend.business.FlashcardBusiness;
import com.example.lyricsflow_backend.dto.FlashcardReviewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flashcards")
public class FlashcardController {

    private final FlashcardBusiness flashcardBusiness;

    public FlashcardController(FlashcardBusiness flashcardBusiness) {
        this.flashcardBusiness = flashcardBusiness;
    }

    @GetMapping("/review/{userId}/next")
    public ResponseEntity<FlashcardReviewDTO> getNextReviewCard(@PathVariable Long userId) {
        FlashcardReviewDTO reviewCard = flashcardBusiness.getNextFlashcardForReview(userId);
        return ResponseEntity.ok(reviewCard);
    }
}
