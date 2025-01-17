package com.mevy.stories.services;

import org.springframework.stereotype.Service;

import com.mevy.stories.dtos.GetCardBookDTO;
import com.mevy.stories.dtos.GetCategoryDTO;
import com.mevy.stories.dtos.PostCategoryCreateDTO;
import com.mevy.stories.entities.Category;
import com.mevy.stories.repositories.CategoryRepository;
import com.mevy.stories.services.exceptions.ResourceSaveException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    
    private CategoryRepository categoryRepository;

    public GetCategoryDTO create(PostCategoryCreateDTO postCategoryCreateDTO) {
        if (!this.categoryRepository.existsByName(postCategoryCreateDTO.name())) {
            Category category = Category.builder()
                                        .name(postCategoryCreateDTO.name())
                                        .build();
            
            this.categoryRepository.save(category);

            GetCategoryDTO getCategoryDTO = toCategoryDTO(category);

            return getCategoryDTO;
        }
        else {
            throw new ResourceSaveException(Category.class, "Nome já utilizado.");
        }
    }

    public GetCategoryDTO toCategoryDTO(Category category) {
        GetCategoryDTO getCategoryDTO = new GetCategoryDTO(
            category.getName()
        );

        return getCategoryDTO;
    }

}
