package com.entando.sme.cartaesercito.smeceintegrationlayers.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the tabsoggettiistanze database table.
 *
 */
@Embeddable
public class TabsoggettiistanzePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="rif_istanza")
	private int rifIstanza;

	@Column(name="rif_soggetto")
	private int rifSoggetto;

	public TabsoggettiistanzePK() {
	}
	public int getRifIstanza() {
		return this.rifIstanza;
	}
	public void setRifIstanza(int rifIstanza) {
		this.rifIstanza = rifIstanza;
	}
	public int getRifSoggetto() {
		return this.rifSoggetto;
	}
	public void setRifSoggetto(int rifSoggetto) {
		this.rifSoggetto = rifSoggetto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TabsoggettiistanzePK)) {
			return false;
		}
		TabsoggettiistanzePK castOther = (TabsoggettiistanzePK)other;
		return
			(this.rifIstanza == castOther.rifIstanza)
			&& (this.rifSoggetto == castOther.rifSoggetto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rifIstanza;
		hash = hash * prime + this.rifSoggetto;

		return hash;
	}

	public TabsoggettiistanzePK(int rifIstanza, int rifSoggetto) {
		this.rifIstanza = rifIstanza;
		this.rifSoggetto = rifSoggetto;
	}
}
