package com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tabtipoistanza database table.
 * 
 */
@Entity
@NamedQuery(name="Tabtipoistanza.findAll", query="SELECT t FROM Tabtipoistanza t")
public class Tabtipoistanza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idTipoIstanza;

	private Timestamp dataAggiornamento;

	private String denominazioneTipoIstanza;

	public Tabtipoistanza() {
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