package com.example.gradebook.service;

import com.example.gradebook.domain.Nastavnik;
import com.example.gradebook.domain.NastavnikRazredPredmet;
import com.example.gradebook.domain.Predmet;
import com.example.gradebook.domain.Razred;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NastavnikRazredPredmetService {
    List<NastavnikRazredPredmet> listAll();
    boolean existsNRP(Nastavnik nastavnik, Razred razred, Predmet predmet);
}
