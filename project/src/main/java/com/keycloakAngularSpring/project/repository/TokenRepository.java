package com.keycloakAngularSpring.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keycloakAngularSpring.project.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);
}
