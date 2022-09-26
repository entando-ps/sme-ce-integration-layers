package com.entando.sme.cartaesercito.smeceintegrationlayers.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the tabistanza database table.
 *
 */
@Entity
@Table(name = "tabistanza")
@NamedQuery(name="Tabistanza.findAll", query="SELECT t FROM Tabistanza t")
public class Tabistanza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idIstanza;

	private Timestamp dataAggiornamento;

	@Temporal(TemporalType.DATE)
	private Date dataIstanza;

	@Column(name="id_sponsor")
	private int idSponsor;

	@Column(name="rif_canale")
	private int rifCanale;

	@Column(name="rif_operatore")
	private int rifOperatore;

	@Column(name="rif_ops")
	private int rifOps;

	@Column(name="rif_stabilimento")
	private int rifStabilimento;

	private int rif_statoIstanza;

	private int rif_tipoIstanza;

	public Tabistanza() {
	}

	public Integer getIdIstanza() {
		return this.idIstanza;
	}

	public void setIdIstanza(Integer idIstanza) {
		this.idIstanza = idIstanza;
	}

	public Timestamp getDataAggiornamento() {
		return this.dataAggiornamento;
	}

	public void setDataAggiornamento(Timestamp dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public Date getDataIstanza() {
		return this.dataIstanza;
	}

	public void setDataIstanza(Date dataIstanza) {
		this.dataIstanza = dataIstanza;
	}

	public int getIdSponsor() {
		return this.idSponsor;
	}

	public void setIdSponsor(int idSponsor) {
		this.idSponsor = idSponsor;
	}

	public int getRifCanale() {
		return this.rifCanale;
	}

	public void setRifCanale(int rifCanale) {
		this.rifCanale = rifCanale;
	}

	public int getRifOperatore() {
		return this.rifOperatore;
	}

	public void setRifOperatore(int rifOperatore) {
		this.rifOperatore = rifOperatore;
	}

	public int getRifOps() {
		return this.rifOps;
	}

	public void setRifOps(int rifOps) {
		this.rifOps = rifOps;
	}

	public int getRifStabilimento() {
		return this.rifStabilimento;
	}

	public void setRifStabilimento(int rifStabilimento) {
		this.rifStabilimento = rifStabilimento;
	}

	public int getRif_statoIstanza() {
		return this.rif_statoIstanza;
	}

	public void setRif_statoIstanza(int rif_statoIstanza) {
		this.rif_statoIstanza = rif_statoIstanza;
	}

	public int getRif_tipoIstanza() {
		return this.rif_tipoIstanza;
	}

	public void setRif_tipoIstanza(int rif_tipoIstanza) {
		this.rif_tipoIstanza = rif_tipoIstanza;
	}


	/*
	INSERT INTO tabistanza (
id_sponsor,
rif_tipoIstanza, -- vedi vocabolario
rif_canale, -- vedi vocabolario fisso a Aggate 1
rif_statoIstanza, -- tabella tipo stato istanza inserire nuovo tipo
7 ( pagata da registrare)
dataIstanza,
rif_ops, --- sempre nullo
rif_operatore) -- stesso operatore fittizio di tab sggetto
VALUES (?, ?, ?, ?, ?, ?, ?)
	 */

	public Tabistanza(Date dataIstanza, int idSponsor, int rifCanale, int rifOperatore, int rif_statoIstanza, int rif_tipoIstanza) {
		this.dataIstanza = dataIstanza;
		this.idSponsor = idSponsor;
		this.rifCanale = rifCanale;
		this.rifOperatore = rifOperatore;
		this.rif_statoIstanza = rif_statoIstanza;
		this.rif_tipoIstanza = rif_tipoIstanza;
	}
}
