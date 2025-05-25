package com.example.gradebook.service.impl;

import com.example.gradebook.repository.NastavnikRazredPredmetRepository;
import com.example.gradebook.domain.Nastavnik;
import com.example.gradebook.domain.NastavnikRazredPredmet;
import com.example.gradebook.domain.Predmet;
import com.example.gradebook.domain.Razred;
import com.example.gradebook.service.NastavnikRazredPredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NastavnikRazredPredmetServiceImpl implements NastavnikRazredPredmetService {

    @Autowired
    NastavnikRazredPredmetRepository nastavnikRazredPredmetRepo;

    @Override
    public List<NastavnikRazredPredmet> listAll() {
        return nastavnikRazredPredmetRepo.findAll();
    }

    @Override
    public boolean existsNRP(Nastavnik nastavnik, Razred razred, Predmet predmet) {
        return nastavnikRazredPredmetRepo.existsByNastavnikAndRazredAndPredmet(nastavnik, razred, predmet);
    }
}
