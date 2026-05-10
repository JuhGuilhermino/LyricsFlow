package com.example.lyricsflow_backend.repository;

import com.example.lyricsflow_backend.model.LyricsLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LyricsLineRepository extends JpaRepository<LyricsLine, Integer> {

    // Busca todas as linhas de uma música ordenadas por linha
    // (usado para montar a atividade de preenchimento de lacunas)
    List<LyricsLine> findBySongIdOrderByLineNumAsc(Integer songId);

    int countBySongId(Integer songId);  //contar quantas linhas uma musica tem
}
