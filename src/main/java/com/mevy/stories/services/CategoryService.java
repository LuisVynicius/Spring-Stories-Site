package com.mevy.stories.services;

import org.springframework.stereotype.Service;

import com.mevy.stories.dtos.CategoryDTO;
import com.mevy.stories.entities.Category;
import com.mevy.stories.repositories.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    
    private CategoryRepository categoryRepository;

    public Category create(Category createDTO) {
        Category category = createDTO;

        category = categoryRepository.save(category);

        return category;
    }

    public Category fromDTO(CategoryDTO categoryDTO) {
        return Category
                        .builder()
                        .name(categoryDTO.name())
                        .build();

    }

}
