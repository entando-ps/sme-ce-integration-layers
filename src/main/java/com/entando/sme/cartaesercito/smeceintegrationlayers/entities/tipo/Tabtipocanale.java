package com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tabtipocanale database table.
 * 
 */
@Entity
@NamedQuery(name="Tabtipocanale.findAll", query="SELECT t FROM Tabtipocanale t")
public class Tabtipocanale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idTipoCanale;

	private Timestamp dataAggiornamento;

	private String denominazioneCanale;

	public Tabtipocanale() {
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