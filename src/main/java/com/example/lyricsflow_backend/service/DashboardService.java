package com.example.lyricsflow_backend.service;

import com.example.lyricsflow_backend.dto.DashboardDTO;
import com.example.lyricsflow_backend.dto.TaskResponse;
import com.example.lyricsflow_backend.enums.FlashcardAnswerQuality;
import com.example.lyricsflow_backend.model.Task;
import com.example.lyricsflow_backend.model.UserProgress;
import com.example.lyricsflow_backend.repository.FlashcardRepository;
import com.example.lyricsflow_backend.repository.TaskRepository;
import com.example.lyricsflow_backend.repository.UserProgressRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final UserProgressRepository userProgressRepository;
    private final TaskRepository taskRepository;
    private final FlashcardRepository flashcardRepository;

    public DashboardService(UserProgressRepository userProgressRepository,
                            TaskRepository taskRepository,
                            FlashcardRepository flashcardRepository) {
        this.userProgressRepository = userProgressRepository;
        this.taskRepository = taskRepository;
        this.flashcardRepository = flashcardRepository;
    }

    public DashboardDTO getDashboardData(Long userId) {
        DashboardDTO dashboard = new DashboardDTO();

        // 1. Progresso Geral (Acurácia)
        UserProgress progress = userProgressRepository.findByUserId(userId).orElse(null);

        if (progress != null) {
            dashboard.setCurrentStreak(progress.getCurrentStreak() != null ? progress.getCurrentStreak() : 0);
            dashboard.setLongestStreak(progress.getLongestStreak() != null ? progress.getLongestStreak() : 0);
            dashboard.setTotalReviews(progress.getTotalReviews() != null ? progress.getTotalReviews() : 0);
            dashboard.setTotalCorrectAnswers(progress.getTotalCorrectAnswers() != null ? progress.getTotalCorrectAnswers() : 0);

            if (progress.getTotalReviews() != null && progress.getTotalReviews() > 0) {
                double accuracy = (progress.getTotalCorrectAnswers() * 100.0) / progress.getTotalReviews();
                dashboard.setAccuracyRate(Math.round(accuracy * 10.0) / 10.0);
            } else {
                dashboard.setAccuracyRate(0.0);
            }
        } else {
            dashboard.setCurrentStreak(0);
            dashboard.setLongestStreak(0);
            dashboard.setTotalReviews(0);
            dashboard.setTotalCorrectAnswers(0);
            dashboard.setAccuracyRate(0.0);
        }

        // 2. Tarefas Recentes (10 recentes direto do banco de dados)
        List<Task> tasks = taskRepository.findTop10ByUserIdOrderByCompletedAtDesc(userId);
        List<TaskResponse> taskResponses = tasks.stream()
            .map(t -> new TaskResponse(
                t.getId(),
                t.getScore(),
                t.getSong().getTitle(),
                t.getSong().getArtist(),
                t.getCompletedAt() != null ? t.getCompletedAt().toString() : null
            ))
            .collect(Collectors.toList());
        dashboard.setRecentTasks(taskResponses);

        // 3. Contagem de Flashcards por Qualidade 
        List<Object[]> qualityResults = flashcardRepository.countQualitiesByUserId(userId);
        Map<FlashcardAnswerQuality, Long> qualityMap = new HashMap<>();
        
        for (Object[] result : qualityResults) {
            if (result[0] != null) {
                qualityMap.put((FlashcardAnswerQuality) result[0], (Long) result[1]);
            }
        }

        dashboard.setTotalBlackout(qualityMap.getOrDefault(FlashcardAnswerQuality.BLACKOUT, 0L));
        dashboard.setTotalIncorrect(qualityMap.getOrDefault(FlashcardAnswerQuality.INCORRECT, 0L));
        dashboard.setTotalHard(qualityMap.getOrDefault(FlashcardAnswerQuality.HARD, 0L));
        dashboard.setTotalMedium(qualityMap.getOrDefault(FlashcardAnswerQuality.MEDIUM, 0L));
        dashboard.setTotalEasy(qualityMap.getOrDefault(FlashcardAnswerQuality.EASY, 0L));
        dashboard.setTotalPerfect(qualityMap.getOrDefault(FlashcardAnswerQuality.PERFECT, 0L));

        return dashboard;
    }
}
