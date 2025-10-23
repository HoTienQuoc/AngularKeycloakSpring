package com.keycloakAngularSpring.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keycloakAngularSpring.project.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByName(String role);
}
