package com.example.gradebook.service;


import com.example.gradebook.domain.Predmet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PredmetService {
    List<Predmet> listAll();
    Optional<Predmet> findById(Long id);
    Predmet fetch(Long id);
    Predmet createPredmet(String oznaka, String naziv);
    Predmet updatePredmet(Long id, String oznaka, String naziv);
    void deletePredmet(Long id);
}
