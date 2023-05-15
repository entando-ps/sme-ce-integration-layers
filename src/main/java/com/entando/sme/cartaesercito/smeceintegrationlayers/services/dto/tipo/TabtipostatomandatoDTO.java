package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo;

import java.io.Serializable;
import javax.persistence.*;


public class TabtipostatomandatoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idStatoMandato;

	private String denominazioneMandato;

	public TabtipostatomandatoDTO() {
	}
	

	public TabtipostatomandatoDTO(int idStatoMandato, String denominazioneMandato) {
		super();
		this.idStatoMandato = idStatoMandato;
		this.denominazioneMandato = denominazioneMandato;
	}


	public int getIdStatoMandato() {
		return this.idStatoMandato;
	}

	public void setIdStatoMandato(int idStatoMandato) {
		this.idStatoMandato = idStatoMandato;
	}

	public String getDenominazioneMandato() {
		return this.denominazioneMandato;
	}

	public void setDenominazioneMandato(String denominazioneMandato) {
		this.denominazioneMandato = denominazioneMandato;
	}

}