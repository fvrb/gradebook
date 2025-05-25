package com.example.gradebook.integration;

import com.example.gradebook.repository.RazredRepository;
import com.example.gradebook.repository.UcenikRepository;
import com.example.gradebook.domain.Razred;
import com.example.gradebook.domain.Ucenik;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UcenikIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UcenikRepository ucenikRepository;

    @Autowired
    private RazredRepository razredRepository;

    private Long testRazredId;

    @BeforeEach
    public void setup() {
        ucenikRepository.deleteAll();
        razredRepository.deleteAll();

        Razred razred = new Razred();
        razred.setNaziv("3A");
        razred = razredRepository.save(razred);
        testRazredId = razred.getId();
    }

    @Test
    public void testCreateUcenik() throws Exception {
        String jsonUcenik = String.format("""
            {
                "ime": "Ivan",
                "prezime": "Lukić",
                "email": "ilukic@example.com",
                "telefon": "123",
                "dob": "2010-05-15",
                "razredId": %d
            }
        """, testRazredId);

        mockMvc.perform(post("/ucenik")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUcenik))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ime").value("Ivan"))
                .andExpect(jsonPath("$.prezime").value("Lukić"));
    }

    @Test
    public void testGetAllUcenici() throws Exception {
        Ucenik ucenik = new Ucenik();
        ucenik.setIme("Ana");
        ucenik.setPrezime("Anić");
        ucenik.setEmail("ana@example.com");
        ucenik.setTelefon("321");
        ucenik.setDob(LocalDate.of(2011, 3, 20));
        ucenik.setRazred(razredRepository.findById(testRazredId).get());
        ucenikRepository.save(ucenik);

        mockMvc.perform(get("/ucenik"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ime").value("Ana"))
                .andExpect(jsonPath("$[0].prezime").value("Anić"));
    }

    @Test
    public void testGetUcenikById() throws Exception {
        Ucenik ucenik = new Ucenik();
        ucenik.setIme("Petar");
        ucenik.setPrezime("Perić");
        ucenik.setEmail("peric@example.com");
        ucenik.setTelefon("456");
        ucenik.setDob(LocalDate.of(2009, 9, 12));
        ucenik.setRazred(razredRepository.findById(testRazredId).get());
        ucenik = ucenikRepository.save(ucenik);

        mockMvc.perform(get("/ucenik/" + ucenik.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ime").value("Petar"))
                .andExpect(jsonPath("$.prezime").value("Perić"));
    }

    @Test
    public void testUpdateUcenik() throws Exception {
        Ucenik ucenik = new Ucenik();
        ucenik.setIme("Maja");
        ucenik.setPrezime("Majic");
        ucenik.setEmail("maja@example.com");
        ucenik.setTelefon("987");
        ucenik.setDob(LocalDate.of(2010, 7, 7));
        ucenik.setRazred(razredRepository.findById(testRazredId).get());
        ucenik = ucenikRepository.save(ucenik);

        String updatedJson = String.format("""
            {
                "ime": "Maja",
                "prezime": "Kovač",
                "email": "maja.k@example.com",
                "telefon": "999",
                "dob": "2010-07-07",
                "razredId": %d
            }
        """, testRazredId);

        mockMvc.perform(put("/ucenik/" + ucenik.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prezime").value("Kovač"))
                .andExpect(jsonPath("$.email").value("maja.k@example.com"));
    }

    @Test
    public void testDeleteUcenik() throws Exception {
        Ucenik ucenik = new Ucenik();
        ucenik.setIme("Nikola");
        ucenik.setPrezime("Nikolić");
        ucenik.setEmail("nikola@example.com");
        ucenik.setTelefon("111");
        ucenik.setDob(LocalDate.of(2008, 11, 11));
        ucenik.setRazred(razredRepository.findById(testRazredId).get());
        ucenik = ucenikRepository.save(ucenik);

        mockMvc.perform(delete("/ucenik/" + ucenik.getId()))
                .andExpect(status().isOk());
    }
}
