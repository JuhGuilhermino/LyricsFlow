package com.example.lyricsflow_backend.repository;

import com.example.lyricsflow_backend.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Integer> {

    List<Flashcard> findByUserId(Integer userId); //buscar todos os flashcards do usuário

    //busvar flashcards para revisão de hoje (repetição espaçada)
    //retornar todos onde a data de revisão é hoje ou já passou
    List<Flashcard> findByUserIdAndNextReviewDateLessThanEqual(
        Integer userId, LocalDate date
    );

    Optional<Flashcard> findByUserIdAndWord(Integer userId, String word); //verificar se uma palavra já existe no vocabulário do usuário
    boolean existsByUserIdAndWord(Integer userId, String word);

    int countByUserId(Integer userId); //contar total de palavras no vocabulário do usuário
}