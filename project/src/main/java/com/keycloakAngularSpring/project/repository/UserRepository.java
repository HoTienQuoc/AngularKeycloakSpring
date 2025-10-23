package com.keycloakAngularSpring.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keycloakAngularSpring.project.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
