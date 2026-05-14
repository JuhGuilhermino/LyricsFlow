package com.example.lyricsflow_backend.repository;

import com.example.lyricsflow_backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId); // Retorna as tarefas concluídas por um usuário

}
