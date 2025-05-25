package com.example.gradebook.service.impl;

import com.example.gradebook.repository.UcenikRepository;
import com.example.gradebook.domain.Ocjena;
import com.example.gradebook.domain.Razred;
import com.example.gradebook.domain.Ucenik;
import com.example.gradebook.service.EntityMissingException;
import com.example.gradebook.service.UcenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UcenikServiceImpl implements UcenikService {
    @Autowired
    UcenikRepository ucenikRepo;

    @Override
    public List<Ucenik> listAll() {
        return ucenikRepo.findAll();
    }

    @Override
    public Optional<Ucenik> findById(Long id) {
        return ucenikRepo.findById(id);
    }

    @Override
    public Ucenik fetch(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityMissingException(Ucenik.class, id)
        );
    }

    @Override
    public List<Ocjena> listOcjene(Long id) {
        return fetch(id).getOcjene();
    }

    @Override
    public Ucenik createUcenik(String ime, String prezime, String email, String telefon, LocalDate dob, Razred razred) {
        return ucenikRepo.save(new Ucenik(ime, prezime, email, telefon, dob, razred));
    }

    @Override
    public Ucenik updateUcenik(Long id, String ime, String prezime, String email, String telefon, LocalDate dob, Razred razred) {
        return ucenikRepo.save(new Ucenik(id, ime, prezime, email, telefon, dob, razred));
    }

    @Override
    public void deleteUcenik(Long id) {
        ucenikRepo.deleteById(id);
    }
}
