package com.entando.sme.cartaesercito.smeceintegrationlayers.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;


/**
 * The persistent class for the tabsoggettiistanze database table.
 *
 */
@Entity
@Table(name = "tabsoggettiistanze")
@NamedQuery(name="Tabsoggettiistanze.findAll", query="SELECT t FROM Tabsoggettiistanze t")
public class Tabsoggettiistanze implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TabsoggettiistanzePK id;

	private Timestamp dataAggiornamento;

	public Tabsoggettiistanze() {
	}

	public TabsoggettiistanzePK getId() {
		return this.id;
	}

	public void setId(TabsoggettiistanzePK id) {
		this.id = id;
	}

	public Timestamp getDataAggiornamento() {
		return this.dataAggiornamento;
	}

	public void setDataAggiornamento(Timestamp dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public Tabsoggettiistanze(TabsoggettiistanzePK id) {
		this.id = id;
		this.dataAggiornamento = Timestamp.from(Instant.now());
	}
}
