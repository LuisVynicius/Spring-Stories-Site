package com.mevy.stories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mevy.stories.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Transactional(readOnly = true)
    boolean existsByName(String name);
}
