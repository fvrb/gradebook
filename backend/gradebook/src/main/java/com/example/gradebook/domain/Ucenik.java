package com.example.gradebook.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ucenik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "razred_id")
    @JsonBackReference
    private Razred razred;

    @OneToMany(mappedBy = "ucenik", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ocjena> ocjene;

    public Ucenik() {
    }

    public Ucenik(String ime, String prezime, String email, String telefon, LocalDate dob, Razred razred) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.dob = dob;
        this.razred = razred;
        this.ocjene = new ArrayList<>();
    }

    public Ucenik(Long id, String ime, String prezime, String email, String telefon, LocalDate dob, Razred razred) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.dob = dob;
        this.razred = razred;
        this.ocjene = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Razred getRazred() {
        return razred;
    }

    public void setRazred(Razred razred) {
        this.razred = razred;
    }

    public List<Ocjena> getOcjene() {
        return ocjene;
    }

    public void setOcjene(List<Ocjena> ocjene) {
        this.ocjene = ocjene;
    }
}
