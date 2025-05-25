package com.example.gradebook.controller;

import com.example.gradebook.domain.Ocjena;
import com.example.gradebook.domain.Razred;
import com.example.gradebook.domain.Ucenik;
import com.example.gradebook.dto.createUcenikDTO;
import com.example.gradebook.service.RazredService;
import com.example.gradebook.service.UcenikService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UcenikController.class)
class UcenikControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UcenikService ucenikService;

    @MockBean
    private RazredService razredService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnListOfUcenici() throws Exception {
        Ucenik u = new Ucenik(1L, "Ana", "Anić", "ana@gmail.com", "0911111111", null, null);
        when(ucenikService.listAll()).thenReturn(List.of(u));

        mockMvc.perform(get("/ucenik"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].ime").value("Ana"));
    }

    @Test
    void shouldReturnUcenikById() throws Exception {
        Ucenik u = new Ucenik(1L, "Marko", "Marić", "marko@gmail.com", "0982222222", null, null);
        when(ucenikService.fetch(1L)).thenReturn(u);

        mockMvc.perform(get("/ucenik/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ime").value("Marko"));
    }

    @Test
    void shouldCreateUcenik() throws Exception {
        createUcenikDTO dto = new createUcenikDTO();
        dto.setIme("Iva");
        dto.setRazredId(2L);
        Razred razred = new Razred("4B", null);
        Ucenik saved = new Ucenik("Iva", null, null, null, null, razred);

        when(razredService.fetch(2L)).thenReturn(razred);
        when(ucenikService.createUcenik(
                dto.getIme(), dto.getPrezime(), dto.getEmail(), dto.getTelefon(), dto.getDob(), razred))
                .thenReturn(saved);

        mockMvc.perform(post("/ucenik")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ime").value("Iva"));
    }

    @Test
    void shouldUpdateUcenik() throws Exception {
        createUcenikDTO dto = new createUcenikDTO();
        dto.setIme("Ivan");
        dto.setRazredId(2L);
        Razred razred = new Razred("4B", null);
        Ucenik updated = new Ucenik(1L,"Ivan", null, null, null, null, razred);

        when(razredService.fetch(2L)).thenReturn(razred);
        when(ucenikService.updateUcenik(
                1L, dto.getIme(), dto.getPrezime(), dto.getEmail(), dto.getTelefon(), dto.getDob(), razred))
                .thenReturn(updated);

        mockMvc.perform(put("/ucenik/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ime").value("Ivan"));
    }

    @Test
    void shouldDeleteUcenik() throws Exception {
        mockMvc.perform(delete("/ucenik/1"))
                .andExpect(status().isOk());

        Mockito.verify(ucenikService).deleteUcenik(1L);
    }

    @Test
    void shouldReturnOcjeneUcenika() throws Exception {
        Ocjena o = new Ocjena(5, null, null, null, null, null, null);
        when(ucenikService.listOcjene(1L)).thenReturn(List.of(o));

        mockMvc.perform(get("/ucenik/1/ocjena"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].vrijednost").value(5));
    }
}
