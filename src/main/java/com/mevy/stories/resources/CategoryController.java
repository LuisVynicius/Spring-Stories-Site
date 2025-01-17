package com.mevy.stories.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mevy.stories.dtos.PostCategoryCreateDTO;
import com.mevy.stories.services.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> create(
        @RequestBody @Valid PostCategoryCreateDTO postCategoryCreateDTO
    ) {
        categoryService.create(postCategoryCreateDTO);
        return ResponseEntity.status(201).build();
    }

}
