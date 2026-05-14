package com.example.lyricsflow_backend.repository;

import com.example.lyricsflow_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // Busca o usuário pelo e-mail utilizado no login

    boolean existsByEmail(String email); // Indica se o email informado eé válido

    boolean existsByUsername(String username); // Indica se o nome do usuário é válido
}
