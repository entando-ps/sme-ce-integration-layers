package com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tabtiporapporto database table.
 * 
 */
@Entity
@NamedQuery(name="Tabtiporapporto.findAll", query="SELECT t FROM Tabtiporapporto t")
public class Tabtiporapporto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idRapporto;

	private String denominazioneRapporto;

	public Tabtiporapporto() {
	}

	public int getIdRapporto() {
		return this.idRapporto;
	}

	public void setIdRapporto(int idRapporto) {
		this.idRapporto = idRapporto;
	}

	public String getDenominazioneRapporto() {
		return this.denominazioneRapporto;
	}

	public void setDenominazioneRapporto(String denominazioneRapporto) {
		this.denominazioneRapporto = denominazioneRapporto;
	}

}