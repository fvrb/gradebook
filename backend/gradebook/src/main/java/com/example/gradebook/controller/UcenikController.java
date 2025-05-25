package com.example.gradebook.controller;

import com.example.gradebook.domain.Ocjena;
import com.example.gradebook.domain.Razred;
import com.example.gradebook.domain.Ucenik;
import com.example.gradebook.dto.createUcenikDTO;
import com.example.gradebook.service.RazredService;
import com.example.gradebook.service.UcenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ucenik")
public class UcenikController {

    @Autowired
    UcenikService ucenikService;

    @Autowired
    RazredService razredService;

    @GetMapping("")
    public List<Ucenik> listUcenici() {
        return ucenikService.listAll();
    }

    @GetMapping("/{id}")
    public Ucenik getUcenik(@PathVariable("id") Long id) {
        return ucenikService.fetch(id);
    }

    @PostMapping("")
    public ResponseEntity<Ucenik> createUcenik(@RequestBody createUcenikDTO dto) {
        Razred razred = razredService.fetch(dto.getRazredId());
        Ucenik saved = ucenikService.createUcenik(dto.getIme(), dto.getPrezime(), dto.getEmail(), dto.getTelefon(), dto.getDob(), razred);
        return ResponseEntity.created(URI.create("/ucenik/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Ucenik updateUcenik(@RequestBody createUcenikDTO dto, @PathVariable("id") Long id) {
        Razred razred = razredService.fetch(dto.getRazredId());
        return ucenikService.updateUcenik(id, dto.getIme(), dto.getPrezime(), dto.getEmail(), dto.getTelefon(), dto.getDob(), razred);
    }

    @DeleteMapping("/{id}")
    public void deleteUcenik(@PathVariable("id") Long id) {
        ucenikService.deleteUcenik(id);
    }

    @GetMapping("/{id}/ocjena")
    public List<Ocjena> getUcenikOcjene(@PathVariable("id") Long id) {
        return ucenikService.listOcjene(id);
    }
}
