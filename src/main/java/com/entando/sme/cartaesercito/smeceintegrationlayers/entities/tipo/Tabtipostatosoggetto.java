package com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tabtipostatosoggetto database table.
 * 
 */
@Entity
@NamedQuery(name="Tabtipostatosoggetto.findAll", query="SELECT t FROM Tabtipostatosoggetto t")
public class Tabtipostatosoggetto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idStatoSoggetto;

	private String denominazioneStato;

	public Tabtipostatosoggetto() {
	}

	public int getIdStatoSoggetto() {
		return this.idStatoSoggetto;
	}

	public void setIdStatoSoggetto(int idStatoSoggetto) {
		this.idStatoSoggetto = idStatoSoggetto;
	}

	public String getDenominazioneStato() {
		return this.denominazioneStato;
	}

	public void setDenominazioneStato(String denominazioneStato) {
		this.denominazioneStato = denominazioneStato;
	}

}