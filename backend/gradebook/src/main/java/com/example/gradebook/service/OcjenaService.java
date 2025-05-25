package com.example.gradebook.service;


import com.example.gradebook.domain.Nastavnik;
import com.example.gradebook.domain.Ocjena;
import com.example.gradebook.domain.Predmet;
import com.example.gradebook.domain.Ucenik;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public interface OcjenaService {
    Optional<Ocjena> findById(Long id);
    Ocjena fetch(Long id);
    Ocjena createOcjena(Integer vrijednost, String vrsta, String komentar, LocalDate datum, Ucenik ucenik, Predmet predmet, Nastavnik nastavnik);
    Ocjena updateOcjena(Long id, Integer vrijednost, String vrsta, String komentar, LocalDate datum, Ucenik ucenik, Predmet predmet, Nastavnik nastavnik);
    void deleteOcjena(Long id);
}
