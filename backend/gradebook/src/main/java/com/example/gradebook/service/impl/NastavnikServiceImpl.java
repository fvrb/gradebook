package com.example.gradebook.service.impl;

import com.example.gradebook.repository.NastavnikRepository;
import com.example.gradebook.domain.Nastavnik;
import com.example.gradebook.service.EntityMissingException;
import com.example.gradebook.service.NastavnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NastavnikServiceImpl implements NastavnikService {
    @Autowired
    NastavnikRepository nastavnikRepo;

    @Override
    public List<Nastavnik> listAll() {
        return nastavnikRepo.findAll();
    }

    @Override
    public Optional<Nastavnik> findById(Long id) {
        return nastavnikRepo.findById(id);
    }

    @Override
    public Nastavnik fetch(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityMissingException(Nastavnik.class, id)
        );
    }
}
