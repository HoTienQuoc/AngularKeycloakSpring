package com.keycloakAngularSpring.project.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.keycloakAngularSpring.project.dto.BookRequest;
import com.keycloakAngularSpring.project.entity.Book;
import com.keycloakAngularSpring.project.entity.User;
import com.keycloakAngularSpring.project.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    public Integer save(BookRequest request, Authentication connectedUser){
        User user = ((User) connectedUser.getPrincipal());
        Book book = bookMapper.toBook(request);
        book.setOwner(user);
        return bookRepository.save(book).getId();
    }
}
