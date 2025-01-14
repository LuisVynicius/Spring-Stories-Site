package com.mevy.stories.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mevy.stories.services.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    
    private CategoryService categoryService;

}
