package com.keycloakAngularSpring.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name="Book")
public class BookController {
    private final BookService service;

    @PostMapping("path")
    public ResponseEntity<Integer> saveBook(
        @Valid @RequestBody BookRequest request,
        Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.save(request, connectedUser));
    }
    
}
