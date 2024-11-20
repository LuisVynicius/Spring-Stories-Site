package com.mevy.stories.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mevy.stories.dtos.CategoryDTO;
import com.mevy.stories.entities.Category;
import com.mevy.stories.services.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CategoryDTO categoryDTO) {

        Category category = categoryService.fromDTO(categoryDTO);

        URI uri = ServletUriComponentsBuilder
                                            .fromCurrentRequest()
                                            .path("/id")
                                            .buildAndExpand(category.getId())
                                            .toUri();

        return ResponseEntity.created(uri).build();
    }

}
