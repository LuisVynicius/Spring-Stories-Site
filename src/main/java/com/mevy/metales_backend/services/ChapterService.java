package com.mevy.metales_backend.services;

import org.springframework.stereotype.Service;

import com.mevy.metales_backend.repositories.ChapterRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChapterService {
    
    private final ChapterRepository chapterRepository;

}
