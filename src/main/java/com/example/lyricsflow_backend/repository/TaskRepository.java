package com.example.lyricsflow_backend.repository;

import com.example.lyricsflow_backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUserIdOrderByOrderIndexAsc(Integer userId); //buscar todas as tarefas do usuário

    Optional<Task> findByUserIdAndIsCurrentTrue(Integer userId); //buscar a tarefa atual do usuário 

    Optional<Task> findFirstByUserIdAndIsCompletedFalseOrderByOrderIndexAsc(
        Integer userId
    ); //buscar a próxima tarefa pendente (para promover após concluir a atual)

    List<Task> findByUserIdAndIsCompletedTrue(Integer userId); //buscar tarefas concluídas do usuário

    boolean existsByUserIdAndSongId(Integer userId, Integer songId); //verificar se o usuário já tem tal música como tarefa

    int countByUserIdAndIsCompletedTrue(Integer userId); //contar tarefas concluídas do usuário
}