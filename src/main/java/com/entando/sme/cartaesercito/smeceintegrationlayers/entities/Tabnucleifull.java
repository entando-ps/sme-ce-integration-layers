package com.entando.sme.cartaesercito.smeceintegrationlayers.entities;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the tabnucleifull database table.
 *
 */
@Entity
@Table(name = "tabnucleifull")
@NamedQuery(name="Tabnucleifull.findAll", query="SELECT t FROM Tabnucleifull t")
public class Tabnucleifull implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="is_capofamiglia")
	private boolean isCapofamiglia;

	@Column(name="is_sponsor")
	private boolean isSponsor;

	@Column(name="rif_istanza")
	private int rifIstanza;

	@Column(name="rif_nucleo")
	private int rifNucleo;

	@Column(name="rif_soggetto")
	private int rifSoggetto;

	public Tabnucleifull() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getIsCapofamiglia() {
		return this.isCapofamiglia;
	}

	public void setIsCapofamiglia(boolean isCapofamiglia) {
		this.isCapofamiglia = isCapofamiglia;
	}

	public boolean getIsSponsor() {
		return this.isSponsor;
	}

	public void setIsSponsor(boolean isSponsor) {
		this.isSponsor = isSponsor;
	}

	public int getRifIstanza() {
		return this.rifIstanza;
	}

	public void setRifIstanza(int rifIstanza) {
		this.rifIstanza = rifIstanza;
	}

	public int getRifNucleo() {
		return this.rifNucleo;
	}

	public void setRifNucleo(int rifNucleo) {
		this.rifNucleo = rifNucleo;
	}

	public int getRifSoggetto() {
		return this.rifSoggetto;
	}

	public void setRifSoggetto(int rifSoggetto) {
		this.rifSoggetto = rifSoggetto;
	}

	/*
	INSERT INTO tabnucleifull (
rif_nucleo,  --- rif ALL id della stessa tabella che indica il
nucleo principale , se nucleo principale rif_nucleo e id coincidono
rif_istanza,
rif_soggetto,
is_sponsor,  --- quando viene valorizzato questo flag
is_capofamiglia)
VALUES (?, ?, ?, ?, ?)

	 */


	public Tabnucleifull(boolean isCapofamiglia, boolean isSponsor, int rifIstanza, int rifNucleo, int rifSoggetto) {
		this.isCapofamiglia = isCapofamiglia;
		this.isSponsor = isSponsor;
		this.rifIstanza = rifIstanza;
		this.rifNucleo = rifNucleo;
		this.rifSoggetto = rifSoggetto;
	}
}
