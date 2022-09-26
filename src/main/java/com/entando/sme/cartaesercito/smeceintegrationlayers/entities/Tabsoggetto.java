package com.entando.sme.cartaesercito.smeceintegrationlayers.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the tabsoggetto database table.
 *
 */
@Entity
@Table(name = "tabsoggetto")
@NamedQuery(name="Tabsoggetto.findAll", query="SELECT t FROM Tabsoggetto t")
public class Tabsoggetto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSoggetto;

	private int anagraficaVerificataDa;

	@Column(columnDefinition = "TEXT")
	private String avviso;

	private String codiceFiscale;

	private String cognome;

	private double credito;

	private Timestamp dataAggiornamento;

	private String email;

	private String enteAppartenenza;

	private String fototessera;

	private String nascitaData;

	private String nascitaLuogo;

	private String nazionalita;

	private int nCarteRicevute;

	private int nContatti;

	private String nome;

	@Column(columnDefinition = "TEXT")
	private String nota;

	@Column(columnDefinition = "TEXT")
	private String notaPerditaRequisiti;

	@Column(columnDefinition = "TEXT")
	private String notaRespinta;

	@Column(columnDefinition = "TEXT")
	private String notaRespintaFoto;

	private int nuovaDirettiva;

	private String pin;

	@Column(name="rif_amministrazione")
	private int rifAmministrazione;

	private int rif_gradoQualifica;

	private int rif_operatoreAggiornamentoStato;

	@Column(name="rif_posizione")
	private int rifPosizione;

	@Column(name="rif_rapporto")
	private int rifRapporto;

	@Column(name="rif_stato")
	private String rifStato;

	private String sesso;

	private String telCellulare;

	private String telUfficio;

	public Tabsoggetto() {
	}

	public Integer getIdSoggetto() {
		return this.idSoggetto;
	}

	public void setIdSoggetto(Integer idSoggetto) {
		this.idSoggetto = idSoggetto;
	}

	public int getAnagraficaVerificataDa() {
		return this.anagraficaVerificataDa;
	}

	public void setAnagraficaVerificataDa(int anagraficaVerificataDa) {
		this.anagraficaVerificataDa = anagraficaVerificataDa;
	}

	public String getAvviso() {
		return this.avviso;
	}

	public void setAvviso(String avviso) {
		this.avviso = avviso;
	}

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public double getCredito() {
		return this.credito;
	}

	public void setCredito(double credito) {
		this.credito = credito;
	}

	public Timestamp getDataAggiornamento() {
		return this.dataAggiornamento;
	}

	public void setDataAggiornamento(Timestamp dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnteAppartenenza() {
		return this.enteAppartenenza;
	}

	public void setEnteAppartenenza(String enteAppartenenza) {
		this.enteAppartenenza = enteAppartenenza;
	}

	public String getFototessera() {
		return this.fototessera;
	}

	public void setFototessera(String fototessera) {
		this.fototessera = fototessera;
	}

	public String getNascitaData() {
		return this.nascitaData;
	}

	public void setNascitaData(String nascitaData) {
		this.nascitaData = nascitaData;
	}

	public String getNascitaLuogo() {
		return this.nascitaLuogo;
	}

	public void setNascitaLuogo(String nascitaLuogo) {
		this.nascitaLuogo = nascitaLuogo;
	}

	public String getNazionalita() {
		return this.nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public int getNCarteRicevute() {
		return this.nCarteRicevute;
	}

	public void setNCarteRicevute(int nCarteRicevute) {
		this.nCarteRicevute = nCarteRicevute;
	}

	public int getNContatti() {
		return this.nContatti;
	}

	public void setNContatti(int nContatti) {
		this.nContatti = nContatti;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNota() {
		return this.nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getNotaPerditaRequisiti() {
		return this.notaPerditaRequisiti;
	}

	public void setNotaPerditaRequisiti(String notaPerditaRequisiti) {
		this.notaPerditaRequisiti = notaPerditaRequisiti;
	}

	public String getNotaRespinta() {
		return this.notaRespinta;
	}

	public void setNotaRespinta(String notaRespinta) {
		this.notaRespinta = notaRespinta;
	}

	public String getNotaRespintaFoto() {
		return this.notaRespintaFoto;
	}

	public void setNotaRespintaFoto(String notaRespintaFoto) {
		this.notaRespintaFoto = notaRespintaFoto;
	}

	public int getNuovaDirettiva() {
		return this.nuovaDirettiva;
	}

	public void setNuovaDirettiva(int nuovaDirettiva) {
		this.nuovaDirettiva = nuovaDirettiva;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public int getRifAmministrazione() {
		return this.rifAmministrazione;
	}

	public void setRifAmministrazione(int rifAmministrazione) {
		this.rifAmministrazione = rifAmministrazione;
	}

	public int getRif_gradoQualifica() {
		return this.rif_gradoQualifica;
	}

	public void setRif_gradoQualifica(int rif_gradoQualifica) {
		this.rif_gradoQualifica = rif_gradoQualifica;
	}

	public int getRif_operatoreAggiornamentoStato() {
		return this.rif_operatoreAggiornamentoStato;
	}

	public void setRif_operatoreAggiornamentoStato(int rif_operatoreAggiornamentoStato) {
		this.rif_operatoreAggiornamentoStato = rif_operatoreAggiornamentoStato;
	}

	public int getRifPosizione() {
		return this.rifPosizione;
	}

	public void setRifPosizione(int rifPosizione) {
		this.rifPosizione = rifPosizione;
	}

	public int getRifRapporto() {
		return this.rifRapporto;
	}

	public void setRifRapporto(int rifRapporto) {
		this.rifRapporto = rifRapporto;
	}

	public String getRifStato() {
		return this.rifStato;
	}

	public void setRifStato(String rifStato) {
		this.rifStato = rifStato;
	}

	public String getSesso() {
		return this.sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getTelCellulare() {
		return this.telCellulare;
	}

	public void setTelCellulare(String telCellulare) {
		this.telCellulare = telCellulare;
	}

	public String getTelUfficio() {
		return this.telUfficio;
	}

	public void setTelUfficio(String telUfficio) {
		this.telUfficio = telUfficio;
	}


	/*
	        private String codiceFiscale;
        private String rif_gradoQualifica;
        private String rif_posizione;
        private String nome;
        private String cognome;
        private String nascitaData;
        private String nascitaLuogo;
        private String nazionalita;
        private String fototessera;
        private String anagraficaVerificataDa;
        private String enteAppartenenza;
        private String telUfficio;
        private String telCellulare;
        private String email;
        private String rif_rapporto;
        private String pin;
        private String sesso;

	 */

	public Tabsoggetto(int anagraficaVerificataDa, String codiceFiscale, String cognome, String email, String enteAppartenenza, String fototessera, String nascitaData, String nascitaLuogo, String nazionalita, String nome, String pin, int rif_gradoQualifica, int rifPosizione, int rifRapporto, String sesso, String telCellulare, String telUfficio, String rifStato) {
		this.anagraficaVerificataDa = anagraficaVerificataDa;
		this.codiceFiscale = codiceFiscale;
		this.cognome = cognome;
		this.email = email;
		this.enteAppartenenza = enteAppartenenza;
		this.fototessera = fototessera;
		this.nascitaData = nascitaData;
		this.nascitaLuogo = nascitaLuogo;
		this.nazionalita = nazionalita;
		this.nome = nome;
		this.pin = pin;
		this.rif_gradoQualifica = rif_gradoQualifica;
		this.rifPosizione = rifPosizione;
		this.rifRapporto = rifRapporto;
		this.sesso = sesso;
		this.telCellulare = telCellulare;
		this.telUfficio = telUfficio;
		this.rifStato = rifStato;
	}
}
