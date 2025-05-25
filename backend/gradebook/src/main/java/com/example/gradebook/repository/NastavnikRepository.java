package com.example.gradebook.repository;

import com.example.gradebook.domain.Nastavnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NastavnikRepository extends JpaRepository<Nastavnik, Long> {
}
