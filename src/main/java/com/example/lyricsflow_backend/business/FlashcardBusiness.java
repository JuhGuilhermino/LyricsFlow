package com.example.lyricsflow_backend.business;

import com.example.lyricsflow_backend.dto.FlashcardAnswerDTO;
import com.example.lyricsflow_backend.dto.FlashcardReviewDTO;
import com.example.lyricsflow_backend.exception.FlashcardNotFoundException;
import com.example.lyricsflow_backend.model.Flashcard;
import com.example.lyricsflow_backend.enums.FlashcardAnswerQuality;
import com.example.lyricsflow_backend.repository.FlashcardRepository;
import com.example.lyricsflow_backend.service.GeminiService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlashcardBusiness {

    private final FlashcardRepository flashcardRepository;
    private final GeminiService geminiService;

    public FlashcardBusiness(FlashcardRepository flashcardRepository,
                              GeminiService geminiService) {
        this.flashcardRepository = flashcardRepository;
        this.geminiService = geminiService;
    }

    public FlashcardReviewDTO getNextFlashcardForReview(Long userId) {
        List<Flashcard> dueCards = flashcardRepository
            .findByNextReviewDateBefore(LocalDate.now().plusDays(1));

        Flashcard next = dueCards.stream()
            .filter(card -> card.getUser().getId().equals(userId))
            .findFirst()
            .orElseThrow(() -> new FlashcardNotFoundException(
                "Nenhum flashcard pendente para o usuário " + userId));

        String userLevel = next.getUser().getLevel().name();

        return geminiService.generateFlashcardContent(
            next.getId(),
            next.getWord(),
            userLevel
        );
    }

    public void processAnswer(FlashcardAnswerDTO answerDTO) {
        Flashcard card = flashcardRepository.findById(answerDTO.getFlashcardId())
            .orElseThrow(() -> new FlashcardNotFoundException(
                "Flashcard não encontrado: " + answerDTO.getFlashcardId()));

        applySM2(card, answerDTO.getQuality());
        flashcardRepository.save(card);
    }

    private void applySM2(Flashcard card, String quality) {
        int q = switch (quality) {
            case "PERFECT"   -> 5;
            case "EASY"      -> 4;
            case "MEDIUM"    -> 3;
            case "HARD"      -> 2;
            case "INCORRECT" -> 1;
            case "BLACKOUT"  -> 0;
            default -> throw new IllegalArgumentException("Qualidade inválida: " + quality);
        };

        double ef = card.getEaseFactor() != null ? card.getEaseFactor() : 2.5;
        int interval = card.getInterval() != null ? card.getInterval() : 0;

        if (q >= 3) {
            // Acertou: avança o intervalo
            interval = switch (interval) {
                case 0 -> 1;
                case 1 -> 6;
                default -> (int) Math.round(interval * ef);
            };
        } else {
            // Errou: reseta para o começo
            interval = 1;
        }

        // Atualiza ease factor (mínimo 1.3)
        ef = ef + (0.1 - (5 - q) * (0.08 + (5 - q) * 0.02));
        ef = Math.max(1.3, ef);

        card.setInterval(interval);
        card.setEaseFactor((float) ef);
        card.setLastQuality(FlashcardAnswerQuality.valueOf(quality));
        card.setNextReviewDate(LocalDate.now().plusDays(interval));
    }
}