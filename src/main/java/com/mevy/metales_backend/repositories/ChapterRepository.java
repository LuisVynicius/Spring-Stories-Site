package com.mevy.metales_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mevy.metales_backend.entities.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long>{
    
}
