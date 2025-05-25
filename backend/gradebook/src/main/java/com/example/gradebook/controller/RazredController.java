package com.example.gradebook.controller;

import com.example.gradebook.domain.Razred;
import com.example.gradebook.domain.Ucenik;
import com.example.gradebook.dto.createRazredDTO;
import com.example.gradebook.service.RazredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/razred")
public class RazredController {

    @Autowired
    RazredService razredService;

    @GetMapping("")
    public List<Razred> listRazredi() {
        return razredService.listAll();
    }

    @GetMapping("/{id}")
    public Razred getRazred(@PathVariable("id") Long id) {
        return razredService.fetch(id);
    }

    @PostMapping("")
    public ResponseEntity<Razred> createRazred(@RequestBody createRazredDTO dto) {
        Razred saved = razredService.createRazred(dto.getOznaka(), dto.getNaziv());
        return ResponseEntity.created(URI.create("/razred/" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}/ucenik")
    public List<Ucenik> getRazredUcenici(@PathVariable("id") Long id) {
        return razredService.listUcenici(id);
    }
}
