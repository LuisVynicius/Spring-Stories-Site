package com.mevy.metales_backend.services;

import org.springframework.stereotype.Service;

import com.mevy.metales_backend.repositories.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

}
