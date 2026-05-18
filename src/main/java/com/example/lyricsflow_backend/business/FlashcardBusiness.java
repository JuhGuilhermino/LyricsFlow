package com.example.lyricsflow_backend.business;

import com.example.lyricsflow_backend.dto.FlashcardReviewDTO;
import com.example.lyricsflow_backend.exception.FlashcardNotFoundException;
import com.example.lyricsflow_backend.model.Flashcard;
import com.example.lyricsflow_backend.model.User;
import com.example.lyricsflow_backend.repository.FlashcardRepository;
import com.example.lyricsflow_backend.repository.UserRepository;
import com.example.lyricsflow_backend.service.GeminiService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlashcardBusiness {

    private final FlashcardRepository flashcardRepository;
    private final GeminiService geminiService;
    private final UserRepository userRepository;

    public FlashcardBusiness(FlashcardRepository flashcardRepository,
                              GeminiService geminiService,
                              UserRepository userRepository) {
        this.flashcardRepository = flashcardRepository;
        this.geminiService = geminiService;
        this.userRepository = userRepository;
    }

    // Método chamado pelo controller
    public FlashcardReviewDTO getNextFlashcardForReview(Long userId) {
    List<Flashcard> dueCards = flashcardRepository
        .findByNextReviewDateBefore(LocalDate.now().plusDays(1));

    Flashcard next = dueCards.stream()
        .filter(card -> card.getUser().getId().equals(userId))
        .findFirst()
        .orElseThrow(() -> new FlashcardNotFoundException(
            "Nenhum flashcard pendente para o usuário " + userId));

    // .name() converte o enum LanguageLevel para String (ex: "BEGINNER")
    String userLevel = next.getUser().getLevel().name();

    return geminiService.generateFlashcardContent(
        next.getId(),
        next.getWord(),
        userLevel
    );
}
}