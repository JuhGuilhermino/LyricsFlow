package com.example.application1.controller;

import com.example.application1.dto.FlashcardAnswerDTO;
import com.example.application1.dto.FlashcardDetailsResponseDTO;
import com.example.application1.service.FlashcardService;
import com.example.application1.model.Flashcard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/flashcards")
public class FlashcardController {

    private final FlashcardService flashcardService;

    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @GetMapping
    public ResponseEntity<?> getAllFlashcards(@RequestParam Long userId) {
        try {
            List<Flashcard> flashcards = this.flashcardService.getAllFlashcardsByUserId(userId);
            return ResponseEntity.ok(flashcards);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao listar os flashcards: " + e.getMessage());
        }
    }

    @GetMapping("/next")
    public ResponseEntity<FlashcardDetailsResponseDTO> getNextFlashcard(@RequestParam Long userId) {
        try {
            FlashcardDetailsResponseDTO response = flashcardService.getNextFlashcardForReview(userId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping("/answer")
    public ResponseEntity<?> processAnswer(@RequestBody FlashcardAnswerDTO answerDTO) {
        try {
            this.flashcardService.processAnswer(answerDTO);
            return ResponseEntity.ok("Resposta processada com sucesso. O flashcard foi reagendado.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro na validação dos dados: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao processar a resposta: " + e.getMessage());
        }
    }


    @PostMapping("/review-search")
    public ResponseEntity<?> reviewSpecificWord(
            @RequestParam Long userId,
            @RequestParam String word,
            @RequestParam String quality) {
        try {
            this.flashcardService.reviewSpecificWord(userId, word, quality);
            return ResponseEntity.ok("Flashcard '" + word + "' revisado isoladamente com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro na validação da pesquisa: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao processar a revisão isolada: " + e.getMessage());
        }
    }
}


