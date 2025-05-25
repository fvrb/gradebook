package com.example.gradebook.controller;

import com.example.gradebook.domain.Predmet;
import com.example.gradebook.dto.createPredmetDTO;
import com.example.gradebook.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/predmet")
public class PredmetController {

    @Autowired
    PredmetService predmetService;

    @GetMapping("")
    public List<Predmet> listPredmeti() {
        return predmetService.listAll();
    }

    @GetMapping("/{id}")
    public Predmet getPredmet(@PathVariable("id") Long id) {
        return predmetService.fetch(id);
    }

    @PostMapping("")
    public ResponseEntity<Predmet> createPredmet(@RequestBody createPredmetDTO dto) {
        Predmet saved = predmetService.createPredmet(dto.getOznaka(), dto.getNaziv());
        return ResponseEntity.created(URI.create("/predmet/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Predmet updatePredmet(@RequestBody createPredmetDTO dto, @PathVariable("id") Long id) {
        return predmetService.updatePredmet(id, dto.getOznaka(), dto.getNaziv());
    }

    @DeleteMapping("/{id}")
    public void deletePredmet(@PathVariable("id") Long id) {
        predmetService.deletePredmet(id);
    }
}
