package com.example.gradebook.controller;

import com.example.gradebook.domain.*;
import com.example.gradebook.dto.createOcjenaDTO;
import com.example.gradebook.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/ocjena")
public class OcjenaController {

    @Autowired
    OcjenaService ocjenaService;

    @Autowired
    UcenikService ucenikService;

    @Autowired
    PredmetService predmetService;

    @Autowired
    NastavnikService nastavnikService;

    @Autowired
    NastavnikRazredPredmetService nastavnikRazredPredmetService;


    @GetMapping("/{id}")
    public Ocjena getOcjena(@PathVariable("id") Long id) {
        return ocjenaService.fetch(id);
    }

    @PostMapping("")
    public ResponseEntity<Ocjena> createOcjena(@RequestBody createOcjenaDTO dto) {
        Ucenik ucenik = ucenikService.fetch(dto.getUcenikId());
        Predmet predmet = predmetService.fetch(dto.getPredmetId());
        Nastavnik nastavnik = nastavnikService.fetch(dto.getNastavnikId());
        Razred razred = ucenik.getRazred();

        boolean exists = nastavnikRazredPredmetService.existsNRP(nastavnik, razred, predmet);

        if (!exists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Ocjena saved = ocjenaService.createOcjena(dto.getVrijednost(), dto.getVrsta(), dto.getKomentar(), dto.getDatum(), ucenik, predmet, nastavnik);
        return ResponseEntity.created(URI.create("/ocjena/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ocjena> updateOcjena(@RequestBody createOcjenaDTO dto, @PathVariable("id") Long id) {
        Ucenik ucenik = ucenikService.fetch(dto.getUcenikId());
        Predmet predmet = predmetService.fetch(dto.getPredmetId());
        Nastavnik nastavnik = nastavnikService.fetch(dto.getNastavnikId());
        Razred razred = ucenik.getRazred();

        boolean exists = nastavnikRazredPredmetService.existsNRP(nastavnik, razred, predmet);

        if (!exists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Ocjena saved = ocjenaService.updateOcjena(id, dto.getVrijednost(), dto.getVrsta(), dto.getKomentar(), dto.getDatum(), ucenik, predmet, nastavnik);
        return ResponseEntity.created(URI.create("/ocjena/" + saved.getId())).body(saved);
    }

    @DeleteMapping("/{id}")
    public void deleteOcjena(@PathVariable("id") Long id) {
        ocjenaService.deleteOcjena(id);
    }
}
