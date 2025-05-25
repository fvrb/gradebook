package com.example.gradebook.dto;

import java.time.LocalDate;

public class createOcjenaDTO {
    private Integer vrijednost;
    private String vrsta;
    private String komentar;
    private LocalDate datum;
    private Long ucenikId;
    private Long predmetId;
    private Long nastavnikId;

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

    public Long getUcenikId() {
        return ucenikId;
    }

    public void setUcenikId(Long ucenikId) {
        this.ucenikId = ucenikId;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public Long getNastavnikId() {
        return nastavnikId;
    }

    public void setNastavnikId(Long nastavnikId) {
        this.nastavnikId = nastavnikId;
    }
}
