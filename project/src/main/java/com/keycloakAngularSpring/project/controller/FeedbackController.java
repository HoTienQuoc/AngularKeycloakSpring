package com.keycloakAngularSpring.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keycloakAngularSpring.project.dto.FeedbackRequest;
import com.keycloakAngularSpring.project.services.FeedbackService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import jakarta.validation.Valid;


@RestController
@RequestMapping("feedbacks")
@RequiredArgsConstructor
@Tag(name="Feedback")
public class FeedbackController {
    private final FeedbackService service;

    @PostMapping()
    public ResponseEntity<Integer> saveFeedback(
        @Valid @RequestBody FeedbackRequest request,
        Authentication connectedUser
    ) {        
        return ResponseEntity.ok(service.save(request,connectedUser));
    }
    
}
