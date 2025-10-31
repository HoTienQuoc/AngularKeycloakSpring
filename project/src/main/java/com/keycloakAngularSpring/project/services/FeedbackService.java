package com.keycloakAngularSpring.project.services;

import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.keycloakAngularSpring.project.dto.FeedbackRequest;
import com.keycloakAngularSpring.project.entity.Book;
import com.keycloakAngularSpring.project.entity.Feedback;
import com.keycloakAngularSpring.project.entity.User;
import com.keycloakAngularSpring.project.exception.OperationNotPermittedException;
import com.keycloakAngularSpring.project.repository.BookRepository;
import com.keycloakAngularSpring.project.repository.FeedbackRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;
    public Integer save(FeedbackRequest request, Authentication connectedUser) {
        Book book = bookRepository.findById(request.bookId())
            .orElseThrow(()->new EntityNotFoundException("No book found with the Id:: "+ request.bookId()));
        if(book.isArchived() || !book.isShareable()){
            throw new OperationNotPermittedException("You cannot give a feedback for an archived or not shareable");
        }
        User user = ((User) connectedUser.getPrincipal());
        if(Objects.equals(book.getOwner().getId(), user.getId())){
            throw new OperationNotPermittedException("You cannot give a feedback to your own book");
        }
        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }
}
