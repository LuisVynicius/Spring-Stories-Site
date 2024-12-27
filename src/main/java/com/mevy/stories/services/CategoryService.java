package com.mevy.stories.services;

import org.springframework.stereotype.Service;

import com.mevy.stories.repositories.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    
    private CategoryRepository categoryRepository;

}
