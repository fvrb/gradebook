package com.example.gradebook.dto;

import java.time.LocalDate;

public class createUcenikDTO {
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private LocalDate dob;
    private Long razredId;

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

    public Long getRazredId() {
        return razredId;
    }

    public void setRazredId(Long razredId) {
        this.razredId = razredId;
    }
}
