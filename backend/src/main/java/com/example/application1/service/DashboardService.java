package com.example.application1.service;

import com.example.application1.dto.DashboardDTO;
import com.example.application1.model.UserProgress;
import com.example.application1.repository.UserProgressRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final UserProgressRepository userProgressRepository;

    public DashboardService(UserProgressRepository userProgressRepository) {
        this.userProgressRepository = userProgressRepository;
    }

    public DashboardDTO getDashboardData(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
        }

        UserProgress progress = userProgressRepository.findByUserId(userId)
                .orElseGet(() -> {
                    UserProgress fallback = new UserProgress();
                    fallback.setTotalTasksCompleted(0);
                    fallback.setAverageTaskScore(0.0f);
                    fallback.setTotalTargetWords(0);
                    fallback.setTotalFlashcardsCount(0);
                    return fallback;
                });

        return new DashboardDTO(
                progress.getTotalTasksCompleted() != null ? progress.getTotalTasksCompleted() : 0,
                progress.getAverageTaskScore() != null ? progress.getAverageTaskScore() : 0.0f,
                progress.getTotalTargetWords() != null ? progress.getTotalTargetWords() : 0,
                progress.getTotalFlashcardsCount() != null ? progress.getTotalFlashcardsCount() : 0,
                progress.getUpdatedAt()
        );
    }
}
