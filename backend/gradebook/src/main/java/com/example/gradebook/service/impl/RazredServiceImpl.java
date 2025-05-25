package com.example.gradebook.service.impl;

import com.example.gradebook.repository.RazredRepository;
import com.example.gradebook.domain.Razred;
import com.example.gradebook.domain.Ucenik;
import com.example.gradebook.service.EntityMissingException;
import com.example.gradebook.service.RazredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RazredServiceImpl implements RazredService {

    @Autowired
    private RazredRepository razredRepo;

    @Override
    public List<Razred> listAll() {
        return razredRepo.findAll();
    }

    @Override
    public Optional<Razred> findById(Long id) {
        return razredRepo.findById(id);
    }

    @Override
    public Razred fetch(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityMissingException(Razred.class, id)
        );
    }

    @Override
    public List<Ucenik> listUcenici(Long id) {
        return fetch(id).getUcenici();
    }


    @Override
    public Razred createRazred(String oznaka, String naziv) {
        return razredRepo.save(new Razred(oznaka, naziv));
    }

}
