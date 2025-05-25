package com.example.gradebook.service;


import com.example.gradebook.domain.Ocjena;
import com.example.gradebook.domain.Razred;
import com.example.gradebook.domain.Ucenik;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface UcenikService {
    List<Ucenik> listAll();
    Optional<Ucenik> findById(Long id);
    Ucenik fetch(Long id);
    List<Ocjena> listOcjene(Long id);
    Ucenik createUcenik(String ime, String prezime, String email, String telefon, LocalDate dob, Razred razred);
    Ucenik updateUcenik(Long id, String ime, String prezime, String email, String telefon, LocalDate dob, Razred razred);
    void deleteUcenik(Long id);
}
