package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo;

import java.io.Serializable;
import javax.persistence.*;



public class TabtiporapportoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idRapporto;

	private String denominazioneRapporto;

	public TabtiporapportoDTO() {
	}
	

	public TabtiporapportoDTO(int idRapporto, String denominazioneRapporto) {
		super();
		this.idRapporto = idRapporto;
		this.denominazioneRapporto = denominazioneRapporto;
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