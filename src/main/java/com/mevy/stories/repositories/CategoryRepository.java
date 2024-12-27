package com.mevy.stories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mevy.stories.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Long, Category> {
    
}
