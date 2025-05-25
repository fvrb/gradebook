package com.example.gradebook.repository;

import com.example.gradebook.domain.Nastavnik;
import com.example.gradebook.domain.NastavnikRazredPredmet;
import com.example.gradebook.domain.Predmet;
import com.example.gradebook.domain.Razred;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NastavnikRazredPredmetRepository extends JpaRepository<NastavnikRazredPredmet, Long> {
    boolean existsByNastavnikAndRazredAndPredmet(Nastavnik nastavnik, Razred razred, Predmet predmet);
}
