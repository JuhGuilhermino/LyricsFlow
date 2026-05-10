package com.example.lyricsflow_backend.repository;

import com.example.lyricsflow_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email); //buscar usuário pelo email usado no login

    Optional<User> findByUsername(String username);  //buscar pelo username

    boolean existsByEmail(String email); //verificar se email ja ta cadastrado

    boolean existsByUsername(String username);  //verificar se username ja ta cadastrado
}
