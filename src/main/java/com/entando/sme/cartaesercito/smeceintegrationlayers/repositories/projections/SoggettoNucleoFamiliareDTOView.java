package com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.projections;


import javax.persistence.Column;

public class SoggettoNucleoFamiliareDTOView {
    private Integer idSoggetto;
    private String codiceFiscale;
    private String cognome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String nome;
    private String fototessera;
    private String nascitaData;
    private String nascitaLuogo;
    private String nazionalita;
    private boolean isCapofamiglia;
    private boolean isSponsor;

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getFototessera() {
        return fototessera;
    }

    public void setFototessera(String fototessera) {
        this.fototessera = fototessera;
    }

    public String getNascitaData() {
        return nascitaData;
    }

    public void setNascitaData(String nascitaData) {
        this.nascitaData = nascitaData;
    }

    public String getNascitaLuogo() {
        return nascitaLuogo;
    }

    public void setNascitaLuogo(String nascitaLuogo) {
        this.nascitaLuogo = nascitaLuogo;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public boolean isCapofamiglia() {
        return isCapofamiglia;
    }

    public void setCapofamiglia(boolean capofamiglia) {
        isCapofamiglia = capofamiglia;
    }

    public boolean isSponsor() {
        return isSponsor;
    }

    public void setSponsor(boolean sponsor) {
        isSponsor = sponsor;
    }

    public Integer getIdSoggetto() {
        return idSoggetto;
    }

    public void setIdSoggetto(Integer idSoggetto) {
        this.idSoggetto = idSoggetto;
    }

    public SoggettoNucleoFamiliareDTOView(
            Integer idSoggetto,
            String codiceFiscale
/*
            String nome,
            String cognome
            String fototessera,
            String nascitaData,
            String nascitaLuogo,
            String nazionalita
            Integer isCapofamiglia,
            Integer isSponsor
*/
    ) {
        this.idSoggetto = idSoggetto;
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.fototessera = fototessera;
        this.nascitaData = nascitaData;
        this.nascitaLuogo = nascitaLuogo;
        this.nazionalita = nazionalita;
/*
        this.isCapofamiglia = isCapofamiglia==1;
        this.isSponsor = isSponsor==1;
*/
    }
}
