package com.mevy.stories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.stories.entities.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    
}
