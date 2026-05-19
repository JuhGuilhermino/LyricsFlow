package com.example.lyricsflow_backend.business;

import com.example.lyricsflow_backend.dto.FlashcardAnswerDTO;
import com.example.lyricsflow_backend.dto.FlashcardReviewDTO;
import com.example.lyricsflow_backend.exception.FlashcardNotFoundException;
import com.example.lyricsflow_backend.model.Flashcard;
import com.example.lyricsflow_backend.model.UserProgress;
import com.example.lyricsflow_backend.enums.FlashcardAnswerQuality;
import com.example.lyricsflow_backend.repository.FlashcardRepository;
import com.example.lyricsflow_backend.repository.UserProgressRepository;
import com.example.lyricsflow_backend.service.GeminiService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlashcardBusiness {

    private final FlashcardRepository flashcardRepository;
    private final UserProgressRepository userProgressRepository;
    private final GeminiService geminiService;

    public FlashcardBusiness(FlashcardRepository flashcardRepository,
                             UserProgressRepository userProgressRepository,
                             GeminiService geminiService) {
        this.flashcardRepository = flashcardRepository;
        this.userProgressRepository = userProgressRepository;
        this.geminiService = geminiService;
    }

    public FlashcardReviewDTO getNextFlashcardForReview(Long userId) {
        //busca apenas cartões que pertencem ao usuário e que estão vencidos
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

    @Transactional // Garante que se um salvar falhar, nenhuma alteração seja feita no banco
    public void processAnswer(FlashcardAnswerDTO answerDTO) {
        Flashcard card = flashcardRepository.findById(answerDTO.getFlashcardId())
            .orElseThrow(() -> new FlashcardNotFoundException(
                "Flashcard não encontrado: " + answerDTO.getFlashcardId()));

        // Converte a string do DTO para Enum
        FlashcardAnswerQuality qualityEnum;
        try {
            qualityEnum = FlashcardAnswerQuality.valueOf(answerDTO.getQuality().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Qualidade inválida fornecida: " + answerDTO.getQuality());
        }

        // 1. Atualiza as métricas individuais do Flashcard
        applySM2(card, qualityEnum);
        flashcardRepository.save(card);

        // 2. Sincroniza e incrementa as métricas globais no UserProgress do Dashboard
        updateGlobalUserProgress(card.getUser().getId(), qualityEnum, card.getUser());
    }

    private void applySM2(Flashcard card, FlashcardAnswerQuality quality) {
        int q = switch (quality) {
            case PERFECT   -> 5;
            case EASY      -> 4;
            case MEDIUM    -> 3;
            case HARD      -> 2;
            case INCORRECT -> 1;
            case BLACKOUT  -> 0;
        };

        double ef = card.getEaseFactor() != null ? card.getEaseFactor() : 2.5;
        int interval = card.getInterval() != null ? card.getInterval() : 0;

        if (q >= 3) {
            // Acertou (PERFECT, EASY, MEDIUM): expande o intervalo de tempo
            interval = switch (interval) {
                case 0 -> 1;
                case 1 -> 6;
                default -> (int) Math.round(interval * ef);
            };
        } else {
            // Errou (HARD, INCORRECT, BLACKOUT): força a revisão para amanhã
            interval = 1;
        }

        //fator de facilidade
        ef = ef + (0.1 - (5 - q) * (0.08 + (5 - q) * 0.02));
        ef = Math.max(1.3, ef); 

        card.setInterval(interval);
        card.setEaseFactor((float) ef);
        card.setLastQuality(quality);
        card.setNextReviewDate(LocalDate.now().plusDays(interval));
    }

    private void updateGlobalUserProgress(Long userId, FlashcardAnswerQuality quality, com.example.lyricsflow_backend.model.User user) {
        UserProgress progress = userProgressRepository.findByUserId(userId)
            .orElseGet(() -> {
                UserProgress newProgress = new UserProgress();
                newProgress.setUser(user);
                newProgress.setTotalReviews(0);
                newProgress.setTotalCorrectAnswers(0);
                newProgress.setCurrentStreak(0);
                newProgress.setLongestStreak(0);
                return newProgress;
            });

        //Incrementa o contador total de revisões do usuário
        progress.setTotalReviews(progress.getTotalReviews() + 1);

        //considerando respostas como corretas se a qualidade for satisfatória (nota >= 3)
        if (quality == FlashcardAnswerQuality.MEDIUM || 
            quality == FlashcardAnswerQuality.EASY || 
            quality == FlashcardAnswerQuality.PERFECT) {
            progress.setTotalCorrectAnswers(progress.getTotalCorrectAnswers() + 1);
        }

        userProgressRepository.save(progress);
    }
}