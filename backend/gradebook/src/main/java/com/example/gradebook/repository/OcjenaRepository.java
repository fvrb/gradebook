package com.example.gradebook.repository;

import com.example.gradebook.domain.Ocjena;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcjenaRepository extends JpaRepository<Ocjena, Long> {
}
