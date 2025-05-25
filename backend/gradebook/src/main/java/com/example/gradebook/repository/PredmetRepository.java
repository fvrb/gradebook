package com.example.gradebook.repository;

import com.example.gradebook.domain.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredmetRepository extends JpaRepository<Predmet, Long> {
}
