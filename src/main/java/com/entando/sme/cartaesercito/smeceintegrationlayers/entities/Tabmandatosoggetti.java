package com.entando.sme.cartaesercito.smeceintegrationlayers.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import static com.entando.sme.cartaesercito.smeceintegrationlayers.Utils.parseString;


/**
 * The persistent class for the tabmandato database table.
 */
@Entity
@Table(name = "tabmandatosoggetti")
@NamedQuery(name = "Tabmandatosoggetti.findAll", query = "SELECT t FROM Tabmandatosoggetti t")
public class Tabmandatosoggetti implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="rif_mandato")
    private int rifMandato;
    @Column(name="rif_soggetto")
    private int rifSoggetto;
    private Timestamp dataAggiornamento;
	public Tabmandatosoggetti(int rifMandato, int rifSoggetto, Timestamp dataAggiornamento) {
		super();
		this.rifMandato = rifMandato;
		this.rifSoggetto = rifSoggetto;
		this.dataAggiornamento = dataAggiornamento;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getRifMandato() {
		return rifMandato;
	}
	public void setRifMandato(int rifMandato) {
		this.rifMandato = rifMandato;
	}
	public int getRifSoggetto() {
		return rifSoggetto;
	}
	public void setRifSoggetto(int rifSoggetto) {
		this.rifSoggetto = rifSoggetto;
	}
	public Timestamp getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(Timestamp dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

    

}
