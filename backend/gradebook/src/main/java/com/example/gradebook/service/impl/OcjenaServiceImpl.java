package com.example.gradebook.service.impl;

import com.example.gradebook.repository.OcjenaRepository;
import com.example.gradebook.domain.Nastavnik;
import com.example.gradebook.domain.Ocjena;
import com.example.gradebook.domain.Predmet;
import com.example.gradebook.domain.Ucenik;
import com.example.gradebook.service.EntityMissingException;
import com.example.gradebook.service.OcjenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OcjenaServiceImpl implements OcjenaService {

    @Autowired
    OcjenaRepository ocjenaRepo;

    @Override
    public Optional<Ocjena> findById(Long id) {
        return ocjenaRepo.findById(id);
    }

    @Override
    public Ocjena fetch(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityMissingException(Predmet.class, id)
        );
    }

    @Override
    public Ocjena createOcjena(Integer vrijednost, String vrsta, String komentar, LocalDate datum, Ucenik ucenik, Predmet predmet, Nastavnik nastavnik) {
        return ocjenaRepo.save(new Ocjena(vrijednost, vrsta, komentar, datum, ucenik, predmet, nastavnik));
    }

    @Override
    public Ocjena updateOcjena(Long id, Integer vrijednost, String vrsta, String komentar, LocalDate datum, Ucenik ucenik, Predmet predmet, Nastavnik nastavnik) {
        return ocjenaRepo.save(new Ocjena(id, vrijednost, vrsta, komentar, datum, ucenik, predmet, nastavnik));
    }


    @Override
    public void deleteOcjena(Long id) {
        ocjenaRepo.deleteById(id);
    }
}
