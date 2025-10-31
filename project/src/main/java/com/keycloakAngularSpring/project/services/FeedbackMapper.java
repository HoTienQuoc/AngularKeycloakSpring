package com.keycloakAngularSpring.project.services;

import org.springframework.stereotype.Service;

import com.keycloakAngularSpring.project.dto.FeedbackRequest;
import com.keycloakAngularSpring.project.entity.Book;
import com.keycloakAngularSpring.project.entity.Feedback;

@Service
public class FeedbackMapper {

    public Feedback toFeedback(FeedbackRequest request) {
        return Feedback.builder()
            .note(request.note())
            .comment(request.comment())
            .book(
                Book.builder()
                    .id(request.bookId())
                    .archived(false)
                    .shareable(false)
                    .build())
            .build();
    }

}
