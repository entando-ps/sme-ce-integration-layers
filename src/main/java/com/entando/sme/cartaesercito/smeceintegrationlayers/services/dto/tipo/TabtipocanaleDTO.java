package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


public class TabtipocanaleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idTipoCanale;

	private Timestamp dataAggiornamento;

	private String denominazioneCanale;

	public TabtipocanaleDTO() {
	}
	

	public TabtipocanaleDTO(int idTipoCanale, Timestamp dataAggiornamento, String denominazioneCanale) {
		super();
		this.idTipoCanale = idTipoCanale;
		this.dataAggiornamento = dataAggiornamento;
		this.denominazioneCanale = denominazioneCanale;
	}


	public int getIdTipoCanale() {
		return this.idTipoCanale;
	}

	public void setIdTipoCanale(int idTipoCanale) {
		this.idTipoCanale = idTipoCanale;
	}

	public Timestamp getDataAggiornamento() {
		return this.dataAggiornamento;
	}

	public void setDataAggiornamento(Timestamp dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public String getDenominazioneCanale() {
		return this.denominazioneCanale;
	}

	public void setDenominazioneCanale(String denominazioneCanale) {
		this.denominazioneCanale = denominazioneCanale;
	}

}