package com.example.gradebook.service;

import com.example.gradebook.repository.UcenikRepository;
import com.example.gradebook.domain.Razred;
import com.example.gradebook.domain.Ucenik;
import com.example.gradebook.service.impl.UcenikServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UcenikServiceTest {

    @Mock
    private UcenikRepository ucenikRepository;

    @InjectMocks
    private UcenikServiceImpl ucenikService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        Ucenik u = new Ucenik(1L, "Ivan", "Ivić", "ivan@example.com", "0912345678", LocalDate.of(2005, 5, 20), null);
        when(ucenikRepository.findAll()).thenReturn(List.of(u));

        List<Ucenik> result = ucenikService.listAll();
        assertEquals(1, result.size());
        assertEquals("Ivan", result.get(0).getIme());
    }

    @Test
    void testFetchExists() {
        Ucenik u = new Ucenik(1L, "Ana", "Anić", null, null, null, null);
        when(ucenikRepository.findById(1L)).thenReturn(Optional.of(u));

        Ucenik result = ucenikService.fetch(1L);
        assertNotNull(result);
        assertEquals("Ana", result.getIme());
    }

    @Test
    void testFetchNotExists() {
        when(ucenikRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityMissingException.class, () -> ucenikService.fetch(1L));
    }

    @Test
    void testCreateUcenik() {
        Razred razred = new Razred("4A", null);
        Ucenik u = new Ucenik("Marko", "Marić", "marko@gmail.com", "0987654321", LocalDate.of(2006, 3, 15), razred);
        when(ucenikRepository.save(any(Ucenik.class))).thenReturn(u);

        Ucenik result = ucenikService.createUcenik("Marko", "Marić", "marko@gmail.com", "0987654321", LocalDate.of(2006, 3, 15), razred);
        assertEquals("Marko", result.getIme());
        verify(ucenikRepository).save(any(Ucenik.class));
    }

    @Test
    void testDeleteUcenik() {
        doNothing().when(ucenikRepository).deleteById(1L);

        ucenikService.deleteUcenik(1L);
        verify(ucenikRepository).deleteById(1L);
    }
}
