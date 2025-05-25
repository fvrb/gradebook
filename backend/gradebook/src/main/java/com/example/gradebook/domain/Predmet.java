package com.example.gradebook.domain;

import jakarta.persistence.*;

@Entity
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oznaka;
    private String naziv;

    public Predmet() {
    }

    public Predmet(String oznaka, String naziv) {
        this.oznaka = oznaka;
        this.naziv = naziv;
    }

    public Predmet(Long id, String oznaka, String naziv) {
        this.id = id;
        this.oznaka = oznaka;
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
