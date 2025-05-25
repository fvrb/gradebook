package com.example.gradebook.service;

import com.example.gradebook.domain.Nastavnik;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface NastavnikService {
    List<Nastavnik> listAll();
    Optional<Nastavnik> findById(Long id);
    Nastavnik fetch(Long id);
}
