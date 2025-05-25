package com.example.gradebook.service.impl;

import com.example.gradebook.repository.PredmetRepository;
import com.example.gradebook.domain.Predmet;
import com.example.gradebook.service.EntityMissingException;
import com.example.gradebook.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PredmetServiceImpl implements PredmetService {
    @Autowired
    PredmetRepository predmetRepo;

    @Override
    public List<Predmet> listAll() {
        return predmetRepo.findAll();
    }

    @Override
    public Optional<Predmet> findById(Long id) {
        return predmetRepo.findById(id);
    }

    @Override
    public Predmet fetch(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityMissingException(Predmet.class, id)
        );
    }

    @Override
    public Predmet createPredmet(String oznaka, String naziv) {
        return predmetRepo.save(new Predmet(oznaka, naziv));
    }

    @Override
    public Predmet updatePredmet(Long id, String oznaka, String naziv) {
        return predmetRepo.save(new Predmet(id, oznaka, naziv));
    }

    @Override
    public void deletePredmet(Long id) {
        predmetRepo.deleteById(id);
    }
}
