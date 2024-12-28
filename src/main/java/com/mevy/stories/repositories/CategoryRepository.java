package com.mevy.stories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.stories.entities.Category;

public interface CategoryRepository extends JpaRepository<Long, Category> {
    
}
