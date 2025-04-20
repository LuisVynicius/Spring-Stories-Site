package com.mevy.metales_backend.services;

import org.springframework.stereotype.Service;

import com.mevy.metales_backend.repositories.TaleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    
    private final TaleRepository bookRepository;

}
