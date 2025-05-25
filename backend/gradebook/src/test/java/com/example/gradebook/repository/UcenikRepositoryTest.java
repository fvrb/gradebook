package com.example.gradebook.repository;

import com.example.gradebook.domain.Ucenik;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UcenikRepositoryTest {

    @Autowired
    private UcenikRepository ucenikRepository;

    @Test
    void testSaveAndFindById() {
        Ucenik u = new Ucenik(null, "Ivan", "Ivić", "ivan@example.com", "0912345678", LocalDate.of(2005, 5, 20), null);
        Ucenik saved = ucenikRepository.save(u);

        Optional<Ucenik> found = ucenikRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getIme()).isEqualTo("Ivan");
    }

    @Test
    void testFindAll() {
        ucenikRepository.save(new Ucenik(null, "Ana", "Anić", null, null, null, null));
        ucenikRepository.save(new Ucenik(null, "Marko", "Marić", null, null, null, null));

        List<Ucenik> all = ucenikRepository.findAll();
        assertThat(all).hasSize(2);
    }

    @Test
    void testDeleteById() {
        Ucenik u = ucenikRepository.save(new Ucenik(null, "Iva", "Ivić", null, null, null, null));
        Long id = u.getId();

        ucenikRepository.deleteById(id);

        Optional<Ucenik> found = ucenikRepository.findById(id);
        assertThat(found).isNotPresent();
    }
}
