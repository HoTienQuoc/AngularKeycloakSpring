package com.keycloakAngularSpring.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.keycloakAngularSpring.project.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

    @Query("""
            SELECT feedback
            FROM Feedback feedback
            WHERE feedback.book.id = :bookId
            """)
    Page<Feedback> findAllByBookId(Integer bookId, Pageable pageable);

}
