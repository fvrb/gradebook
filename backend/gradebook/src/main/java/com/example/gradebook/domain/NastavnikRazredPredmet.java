package com.example.gradebook.domain;

import jakarta.persistence.*;

@Entity
public class NastavnikRazredPredmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Nastavnik nastavnik;

    @ManyToOne
    private Razred razred;

    @ManyToOne
    private Predmet predmet;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Razred getRazred() {
        return razred;
    }

    public void setRazred(Razred razred) {
        this.razred = razred;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
}
