package com.mevy.stories.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mevy.stories.services.ChapterService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/chapter")
@AllArgsConstructor
public class ChapterController {
    
    private ChapterService chapterService;

}
