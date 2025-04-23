package com.mevy.metales_backend.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mevy.metales_backend.entities.dtos.TaleDTO;
import com.mevy.metales_backend.entities.dtos.TaleReadDTO;
import com.mevy.metales_backend.entities.dtos.TaleViewDTO;
import com.mevy.metales_backend.services.TaleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/tales")
@AllArgsConstructor
public class TaleResource {
    
    private final TaleService taleService;

    @GetMapping("/all")
    public ResponseEntity<List<TaleDTO>> findTales() {
        List<TaleDTO> tales = taleService.findTales();

        return ResponseEntity.ok().body(tales);
    }

    @GetMapping("/{name}")
    public ResponseEntity<TaleViewDTO> findTale(@PathVariable String name) {
        TaleViewDTO taleViewDTO = taleService.findTale(name);

        return ResponseEntity.ok().body(taleViewDTO);
    }

    @GetMapping("/{name}/{number}")
    public ResponseEntity<TaleReadDTO> findChapter(@PathVariable String name, @PathVariable Long number) {
        TaleReadDTO taleReadDTO = taleService.findChapter(name, number);



        return ResponseEntity.ok().body(taleReadDTO);
    }

    @GetMapping("/my")
    public ResponseEntity<List<TaleDTO>> findMyTales(@RequestHeader("Authorization") String token) {
        List<TaleDTO> tales = taleService.findMyTales(token);

        return ResponseEntity.ok().body(tales);
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<TaleDTO>> findMyFavorites(@RequestHeader("Authorization") String token) {
        List<TaleDTO> tales = taleService.findMyFavorites(token);

        return ResponseEntity.ok().body(tales);
    }

}
