package com.example.gradebook.service;


import com.example.gradebook.domain.Razred;
import com.example.gradebook.domain.Ucenik;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RazredService {
    List<Razred> listAll();
    Optional<Razred> findById(Long id);
    Razred fetch(Long id);
    List<Ucenik> listUcenici(Long id);
    Razred createRazred(String oznaka, String naziv);
}
