package com.example.lyricsflow_backend.service;

import com.example.lyricsflow_backend.model.Task;
import com.example.lyricsflow_backend.model.Song;
import com.example.lyricsflow_backend.model.User;
import com.example.lyricsflow_backend.repository.TaskRepository;
import com.example.lyricsflow_backend.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SongRepository songRepository;

    // Construtor
    public TaskService(TaskRepository taskRepository,
                       SongRepository songRepository) {
        this.taskRepository = taskRepository;
        this.songRepository = songRepository;
    }


    // Criação da lista de tarefas personalizada do usuário
    public List<Task> createTasksForUser(User user) {

        List<Song> songs = songRepository.findByGoal(user.getGoal()); // Monta um lista com as músicas daquela meta

        List<Task> tasks = new ArrayList<>(); // Craiação da lista de tarefas

        // Cria e alona ao usuario uma tarefa para cada música 
        for (Song song : songs) {
            Task task = new Task();
            task.setUser(user); 
            task.setSong(song); 
            task.setScore(null);
            task.setCompletedAt(null);

            tasks.add(task);
        }

        return taskRepository.saveAll(tasks); 
    }

    // Retorna todas as tarefas de um usuário
    public List<Task> findByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }
}
