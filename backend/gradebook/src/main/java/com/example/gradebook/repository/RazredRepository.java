package com.example.gradebook.repository;

import com.example.gradebook.domain.Razred;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RazredRepository extends JpaRepository<Razred, Long> {
}
