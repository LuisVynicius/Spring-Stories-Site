package com.mevy.stories.services;

import org.springframework.stereotype.Service;

import com.mevy.stories.repositories.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    
    private BookRepository bookRepository;

}
