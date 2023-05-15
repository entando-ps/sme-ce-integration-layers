package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


public class TabtipostatocartaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idtipostatocarta;

	private Timestamp dataAggiornamento;

	private String descrizioneStatoCarta;

	public TabtipostatocartaDTO() {
	}

	public TabtipostatocartaDTO(int idtipostatocarta, Timestamp dataAggiornamento, String descrizioneStatoCarta) {
		super();
		this.idtipostatocarta = idtipostatocarta;
		this.dataAggiornamento = dataAggiornamento;
		this.descrizioneStatoCarta = descrizioneStatoCarta;
	}

	public int getIdtipostatocarta() {
		return this.idtipostatocarta;
	}

	public void setIdtipostatocarta(int idtipostatocarta) {
		this.idtipostatocarta = idtipostatocarta;
	}

	public Timestamp getDataAggiornamento() {
		return this.dataAggiornamento;
	}

	public void setDataAggiornamento(Timestamp dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public String getDescrizioneStatoCarta() {
		return this.descrizioneStatoCarta;
	}

	public void setDescrizioneStatoCarta(String descrizioneStatoCarta) {
		this.descrizioneStatoCarta = descrizioneStatoCarta;
	}

}