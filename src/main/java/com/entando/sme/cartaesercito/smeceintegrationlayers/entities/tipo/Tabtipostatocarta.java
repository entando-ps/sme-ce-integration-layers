package com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tabtipostatocarta database table.
 * 
 */
@Entity
@NamedQuery(name="Tabtipostatocarta.findAll", query="SELECT t FROM Tabtipostatocarta t")
public class Tabtipostatocarta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idtipostatocarta;

	private Timestamp dataAggiornamento;

	private String descrizioneStatoCarta;

	public Tabtipostatocarta() {
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