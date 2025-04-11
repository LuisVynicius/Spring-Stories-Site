package com.mevy.metales_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.metales_backend.entities.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long>{
    
}
