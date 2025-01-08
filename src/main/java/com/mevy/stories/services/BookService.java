package com.mevy.stories.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mevy.stories.entities.Book;
import com.mevy.stories.repositories.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    
    private BookRepository bookRepository;

    public List<Book> recentBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

}
