package com.keycloakAngularSpring.project.services;

import org.springframework.stereotype.Service;

import com.keycloakAngularSpring.project.dto.BookRequest;
import com.keycloakAngularSpring.project.entity.Book;

@Service
public class BookMapper {
    public Book toBook(BookRequest request){
        return Book.builder()
            .id(request.id())
            .title(request.title())
            .authorName(request.authorName())
            .synopis(request.synopsis())
            .archived(false)
            .shareable(request.shareable())
            .build();
    }
}
