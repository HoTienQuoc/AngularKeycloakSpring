package com.keycloakAngularSpring.project.services;

import org.springframework.stereotype.Service;

import com.keycloakAngularSpring.project.dto.BookRequest;
import com.keycloakAngularSpring.project.dto.BookResponse;
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

    public BookResponse toBookResponse(Book book){
        return BookResponse.builder()
            .id(book.getId())
            .title(book.getTitle())
            .authorName(book.getAuthorName())
            .isbn(book.getIsbn())
            .synopsis(book.getSynopis())
            .rate(book.getRate())
            .archived(book.isArchived())
            .shareable(book.isShareable())
            .owner(book.getOwner().fullName())
            .build();
    }
}
