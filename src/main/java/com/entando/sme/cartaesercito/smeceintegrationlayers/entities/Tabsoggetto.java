package com.entando.sme.cartaesercito.smeceintegrationlayers.entities;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;

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

	private int anagraficaVerificataDa;  // id operatore che convalida

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

	// da qui competenza inserimento BO
	@Column(columnDefinition = "TEXT")
	private String nota;

	@Column(columnDefinition = "TEXT")
	private String notaPerditaRequisiti;

	@Column(columnDefinition = "TEXT")
	private String notaRespinta;

	@Column(columnDefinition = "TEXT")
	private String notaRespintaFoto;

	private Integer nuovaDirettiva=0; // non c'è in insert loro (SME)

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

	public Integer getNuovaDirettiva() {
		return this.nuovaDirettiva;
	}

	public void setNuovaDirettiva(Integer nuovaDirettiva) {
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







	public Tabsoggetto(String codiceFiscale, String cognome, String email, String enteAppartenenza, String fototessera, String nascitaData, String nascitaLuogo, String nazionalita, String nome, int rif_gradoQualifica, int rifPosizione, int rifRapporto, String sesso, String telCellulare, String telUfficio, String rifStato) {
		this.codiceFiscale = codiceFiscale;
		this.cognome = cognome;
		this.email = email;
		this.enteAppartenenza = enteAppartenenza;
		this.fototessera = fototessera;
		this.nascitaData = nascitaData;
		this.nascitaLuogo = nascitaLuogo;
		this.nazionalita = nazionalita;
		this.nome = nome;
//		this.pin = pin;
		this.rif_gradoQualifica = rif_gradoQualifica;
		this.rifPosizione = rifPosizione;
		this.rifRapporto = rifRapporto;
		this.sesso = sesso;
		this.telCellulare = telCellulare;
		this.telUfficio = telUfficio;
		this.rifStato = rifStato;
		this.nuovaDirettiva =0;
	}

	public void copyFrom(String rifStato, ModuloDTO.Soggetto soggetto){
		this.codiceFiscale = soggetto.getCodiceFiscale();
		this.cognome = soggetto.getCognome();
		this.email = soggetto.getEmail();
		this.enteAppartenenza = soggetto.getEnteAppartenenza();
		this.fototessera = soggetto.getFototessera();
		this.nascitaData = soggetto.getNascitaData();
		this.nascitaLuogo = soggetto.getNascitaLuogo();
		this.nazionalita = soggetto.getNazionalita();
		this.nome = soggetto.getNome();
		/** si lascia il pin già salvato
		 * se non è già presente non si deve creare
		 */
//		this.pin = soggetto.pin();
		this.rif_gradoQualifica = soggetto.getRifGradoQualifica();
		this.rifPosizione = soggetto.getRifPosizione();
		this.rifRapporto = soggetto.getRifRapporto();
		this.sesso = soggetto.getSesso();
		this.telCellulare = soggetto.getTelCellulare();
		this.telUfficio = soggetto.getTelUfficio();
		this.rifStato = rifStato;
		this.nuovaDirettiva = soggetto.getNuovaDirettiva();

	}

	public Tabsoggetto(String rifStato, String[] attributes){
		this(
				attributes[0],
				attributes[1],
				attributes[2],
				attributes[3],
				attributes[4],
				attributes[5],
				attributes[6],
				attributes[7],
				attributes[8],
//				attributes[9], // pin
				Integer.parseInt(attributes[10]),
				Integer.parseInt(attributes[11]),
				Integer.parseInt(attributes[12]),
				attributes[13],
				attributes[14],
				attributes[15],
				rifStato
		);
	}
	public Tabsoggetto(String rifStato, ModuloDTO.Soggetto soggetto){ //String pin,
		this(
				soggetto.getCodiceFiscale(),
				soggetto.getCognome(),
				soggetto.getEmail(),
				soggetto.getEnteAppartenenza(),
				soggetto.getFototessera(),
				soggetto.getNascitaData(),
				soggetto.getNascitaLuogo(),
				soggetto.getNazionalita(),
				soggetto.getNome(),
//				pin,
				soggetto.getRifGradoQualifica(),
				soggetto.getRifPosizione(),
				soggetto.getRifRapporto(),
				soggetto.getSesso(),
				soggetto.getTelCellulare(),
				soggetto.getTelUfficio(),
				rifStato

		);
	}

}
