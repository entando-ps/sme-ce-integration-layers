package com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tabtipostatomandato database table.
 * 
 */
@Entity
@NamedQuery(name="Tabtipostatomandato.findAll", query="SELECT t FROM Tabtipostatomandato t")
public class Tabtipostatomandato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idStatoMandato;

	private String denominazioneMandato;

	public Tabtipostatomandato() {
	}

	public int getIdStatoMandato() {
		return this.idStatoMandato;
	}

	public void setIdStatoMandato(int idStatoMandato) {
		this.idStatoMandato = idStatoMandato;
	}

	public String getDenominazioneMandato() {
		return this.denominazioneMandato;
	}

	public void setDenominazioneMandato(String denominazioneMandato) {
		this.denominazioneMandato = denominazioneMandato;
	}

}