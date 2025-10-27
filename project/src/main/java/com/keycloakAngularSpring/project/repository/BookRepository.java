package com.keycloakAngularSpring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keycloakAngularSpring.project.entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer> {

}
