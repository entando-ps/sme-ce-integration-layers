package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo;

import java.io.Serializable;
import javax.persistence.*;



public class TabtipopagamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idTipoPagamento;

	private String denominazionePagamento;

	private byte riscossoDaDitta;

	private int riscossoDaSoggiorno;

	private byte visibile;

	public TabtipopagamentoDTO(int idTipoPagamento, String denominazionePagamento, byte riscossoDaDitta,
			int riscossoDaSoggiorno, byte visibile) {
		super();
		this.idTipoPagamento = idTipoPagamento;
		this.denominazionePagamento = denominazionePagamento;
		this.riscossoDaDitta = riscossoDaDitta;
		this.riscossoDaSoggiorno = riscossoDaSoggiorno;
		this.visibile = visibile;
	}

	public TabtipopagamentoDTO() {
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