package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo;

import java.io.Serializable;
import javax.persistence.*;


public class TabtipostatosoggettoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idStatoSoggetto;

	private String denominazioneStato;

	public TabtipostatosoggettoDTO() {
	}

	public TabtipostatosoggettoDTO(int idStatoSoggetto, String denominazioneStato) {
		super();
		this.idStatoSoggetto = idStatoSoggetto;
		this.denominazioneStato = denominazioneStato;
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