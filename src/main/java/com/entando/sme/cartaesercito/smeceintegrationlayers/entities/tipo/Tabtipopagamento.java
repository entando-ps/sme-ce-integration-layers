package com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tabtipopagamento database table.
 * 
 */
@Entity
@NamedQuery(name="Tabtipopagamento.findAll", query="SELECT t FROM Tabtipopagamento t")
public class Tabtipopagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idTipoPagamento;

	private String denominazionePagamento;

	private byte riscossoDaDitta;

	private int riscossoDaSoggiorno;

	private byte visibile;

	public Tabtipopagamento() {
	}

	public int getIdTipoPagamento() {
		return this.idTipoPagamento;
	}

	public void setIdTipoPagamento(int idTipoPagamento) {
		this.idTipoPagamento = idTipoPagamento;
	}

	public String getDenominazionePagamento() {
		return this.denominazionePagamento;
	}

	public void setDenominazionePagamento(String denominazionePagamento) {
		this.denominazionePagamento = denominazionePagamento;
	}

	public byte getRiscossoDaDitta() {
		return this.riscossoDaDitta;
	}

	public void setRiscossoDaDitta(byte riscossoDaDitta) {
		this.riscossoDaDitta = riscossoDaDitta;
	}

	public int getRiscossoDaSoggiorno() {
		return this.riscossoDaSoggiorno;
	}

	public void setRiscossoDaSoggiorno(int riscossoDaSoggiorno) {
		this.riscossoDaSoggiorno = riscossoDaSoggiorno;
	}

	public byte getVisibile() {
		return this.visibile;
	}

	public void setVisibile(byte visibile) {
		this.visibile = visibile;
	}

}