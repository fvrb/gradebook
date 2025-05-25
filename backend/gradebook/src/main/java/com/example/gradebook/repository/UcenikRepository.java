package com.example.gradebook.repository;

import com.example.gradebook.domain.Ucenik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UcenikRepository extends JpaRepository<Ucenik, Long> {
}
