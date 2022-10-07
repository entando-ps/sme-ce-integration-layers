package com.entando.sme.cartaesercito.smeceintegrationlayers.entities;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the tabresidenze database table.
 */
@Entity
@Table(name = "tabresidenze")
@NamedQuery(name = "Tabresidenze.findAll", query = "SELECT t FROM Tabresidenze t")
public class Tabresidenze implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResidenza;

    private String cap;

    private String citta;

    private String civico;

    private String presso;

    private String provincia;

    @Column(name = "rif_soggetto")
    private int rifSoggetto;

    private String via;

    public Tabresidenze() {
    }

    public Integer getIdResidenza() {
        return this.idResidenza;
    }

    public void setIdResidenza(Integer idResidenza) {
        this.idResidenza = idResidenza;
    }

    public String getCap() {
        return this.cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCitta() {
        return this.citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCivico() {
        return this.civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getPresso() {
        return this.presso;
    }

    public void setPresso(String presso) {
        this.presso = presso;
    }

    public String getProvincia() {
        return this.provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getRifSoggetto() {
        return this.rifSoggetto;
    }

    public void setRifSoggetto(int rifSoggetto) {
        this.rifSoggetto = rifSoggetto;
    }

    public String getVia() {
        return this.via;
    }

    public void setVia(String via) {
        this.via = via;
    }


	/*
	INSERT    INTO tabresidenze (
rif_soggetto, --- FK con tabsoggetto?
via,
civico,
citta,
cap,
provincia)
VALUES (?,?,?,?,?,?)

	 */

    public Tabresidenze(String cap, String citta, String civico, String presso, String provincia, String via) {
        this.cap = cap;
        this.citta = citta;
        this.civico = civico;
        this.presso = presso;
        this.provincia = provincia;
        this.via = via;
    }

    public Tabresidenze(String[] attributes) {
        this(
                attributes[0],
                attributes[1],
                attributes[2],
                attributes[3],
                attributes[4],
                attributes[5]
        );
    }

    public Tabresidenze(ModuloDTO.Residenza residenza) {
        this(
                residenza.getCap(), residenza.getCitta(), residenza.getCivico(), residenza.getPresso(), residenza.getProvincia(), residenza.getVia()
        );
    }
}
