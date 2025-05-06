package com.mevy.metales_backend.resources;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mevy.metales_backend.entities.dtos.ChapterCreate;
import com.mevy.metales_backend.entities.dtos.ChapterViewDTO;
import com.mevy.metales_backend.services.ChapterService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/chapter")
@AllArgsConstructor
public class ChapterResource {
    
    private final ChapterService chapterService;

    @PostMapping
    public ResponseEntity<ChapterViewDTO> create(
        @RequestBody ChapterCreate chapterCreate
    ) {
        ChapterViewDTO chapterViewDTO = this.chapterService.create(chapterCreate);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                            .path("/{name}")
                                            .buildAndExpand(chapterViewDTO.name())
                                            .toUri();

        return ResponseEntity.created(uri).body(chapterViewDTO);

    }

}
