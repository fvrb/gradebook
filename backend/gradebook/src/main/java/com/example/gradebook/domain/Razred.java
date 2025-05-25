package com.example.gradebook.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Razred {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oznaka;
    private String naziv;

    @OneToMany(mappedBy = "razred", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ucenik> ucenici;


    public Razred() {
    }

    public Razred(String oznaka, String naziv) {
        this.oznaka = oznaka;
        this.naziv = naziv;
        this.ucenici = new ArrayList<>();
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

    public List<Ucenik> getUcenici() {
        return ucenici;
    }

    public void setUcenici(List<Ucenik> ucenici) {
        this.ucenici = ucenici;
    }
}
