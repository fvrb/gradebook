package com.example.gradebook.controller;

import com.example.gradebook.domain.Nastavnik;
import com.example.gradebook.service.NastavnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/nastavnik")
public class NastavnikController {

    @Autowired
    NastavnikService nastavnikService;

    @GetMapping("")
    public List<Nastavnik> listNastavnici() {
        return nastavnikService.listAll();
    }
}
