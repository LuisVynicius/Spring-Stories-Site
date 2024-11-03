package com.mevy.stories.services;

import org.springframework.stereotype.Service;

import com.mevy.stories.entities.Book;
import com.mevy.stories.repositories.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    
    private BookRepository bookRepository;

    public Book create(Book bookDTO) {
        Book book = bookDTO;

        book = bookRepository.save(book);

        return book;
    }

}
