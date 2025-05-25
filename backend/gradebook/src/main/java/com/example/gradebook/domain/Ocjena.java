package com.example.gradebook.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Ocjena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer vrijednost;
    private String vrsta;
    private String komentar;
    private LocalDate datum;

    @ManyToOne
    @JoinColumn(name = "ucenik_id")
    @JsonBackReference
    private Ucenik ucenik;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;

    @ManyToOne
    @JoinColumn(name = "nastavnik_id")
    private Nastavnik nastavnik;

    public Ocjena() {
    }

    public Ocjena(Integer vrijednost, String vrsta, String komentar, LocalDate datum, Ucenik ucenik, Predmet predmet, Nastavnik nastavnik) {
        this.vrijednost = vrijednost;
        this.vrsta = vrsta;
        this.komentar = komentar;
        this.datum = datum;
        this.ucenik = ucenik;
        this.predmet = predmet;
        this.nastavnik = nastavnik;
    }

    public Ocjena(Long id, Integer vrijednost, String vrsta, String komentar, LocalDate datum, Ucenik ucenik, Predmet predmet, Nastavnik nastavnik) {
        this.id = id;
        this.vrijednost = vrijednost;
        this.vrsta = vrsta;
        this.komentar = komentar;
        this.datum = datum;
        this.ucenik = ucenik;
        this.predmet = predmet;
        this.nastavnik = nastavnik;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(Integer vrijednost) {
        this.vrijednost = vrijednost;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }
}
