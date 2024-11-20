package com.mevy.stories.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mevy.stories.entities.Category;
import com.mevy.stories.services.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> create() {
        Category category = categoryService.create(
            Category.builder().name("Category01").build()
        );

        URI uri = ServletUriComponentsBuilder
                                            .fromCurrentRequest()
                                            .path("/id")
                                            .buildAndExpand(category.getId())
                                            .toUri();

        return ResponseEntity.created(uri).build();
    }

}
