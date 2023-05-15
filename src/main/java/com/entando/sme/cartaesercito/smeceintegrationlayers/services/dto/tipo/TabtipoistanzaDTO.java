package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


public class TabtipoistanzaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idTipoIstanza;

	private Timestamp dataAggiornamento;

	private String denominazioneTipoIstanza;

	public TabtipoistanzaDTO() {
	}

	public TabtipoistanzaDTO(int idTipoIstanza, Timestamp dataAggiornamento, String denominazioneTipoIstanza) {
		super();
		this.idTipoIstanza = idTipoIstanza;
		this.dataAggiornamento = dataAggiornamento;
		this.denominazioneTipoIstanza = denominazioneTipoIstanza;
	}

	public int getIdTipoIstanza() {
		return this.idTipoIstanza;
	}

	public void setIdTipoIstanza(int idTipoIstanza) {
		this.idTipoIstanza = idTipoIstanza;
	}

	public Timestamp getDataAggiornamento() {
		return this.dataAggiornamento;
	}

	public void setDataAggiornamento(Timestamp dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public String getDenominazioneTipoIstanza() {
		return this.denominazioneTipoIstanza;
	}

	public void setDenominazioneTipoIstanza(String denominazioneTipoIstanza) {
		this.denominazioneTipoIstanza = denominazioneTipoIstanza;
	}

}