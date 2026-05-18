package com.example.lyricsflow_backend.business;

import com.example.lyricsflow_backend.dto.TaskResponse;
import com.example.lyricsflow_backend.model.Task;
import com.example.lyricsflow_backend.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskBusiness {

    private final TaskService taskService;

    // Construtor
    public TaskBusiness(TaskService taskService) {
        this.taskService = taskService;
    }

    // Retorna a lista de músicas
    public List<TaskResponse> getTasksByUser(Long userId) {
        return taskService.findByUserId(userId)
                .stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getScore(),
                        task.getSong().getTitle(),
                        task.getSong().getArtist(),
                        task.getCompletedAt() != null ? task.getCompletedAt().toString() : null
                ))
                .toList();
    }
}
