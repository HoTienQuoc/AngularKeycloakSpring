package com.keycloakAngularSpring.project.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;




import com.keycloakAngularSpring.project.dto.BookRequest;
import com.keycloakAngularSpring.project.dto.BookResponse;
import com.keycloakAngularSpring.project.dto.PageResponse;
import com.keycloakAngularSpring.project.entity.Book;
import static com.keycloakAngularSpring.project.entity.BookSpecification.withOwnerId;
import com.keycloakAngularSpring.project.entity.User;
import com.keycloakAngularSpring.project.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;
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
    public BookResponse findById(Integer bookId) {
        return bookRepository.findById(bookId)
            .map(bookMapper::toBookResponse)
            .orElseThrow(()-> new EntityNotFoundException("No book found with the Id: "+ bookId));
    }
    public PageResponse<BookResponse> findAllBooks(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page,size,Sort.by("createdDate").descending());
        Page<Book> books = bookRepository.findAllDisplayableBooks(pageable,user.getId());
        List<BookResponse> bookResponse = books.stream()
            .map(bookMapper::toBookResponse)
            .toList();

        return new PageResponse<>(
            bookResponse,
            books.getNumber(),
            books.getSize(),
            books.getTotalElements(),
            books.getTotalPages(),
            books.isFirst(),
            books.isLast()
        );
    }
    public PageResponse<BookResponse> findAllBooksByOwner(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page,size,Sort.by("createdDate").descending());
        Page<Book> books = bookRepository.findAll(withOwnerId(user.getId()),pageable);
        return null;
    }
}
