package com.entando.sme.cartaesercito.smeceintegrationlayers.entities;

import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.projections.SoggettoNucleoFamiliareDTOView;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the tabcartaesercito database table.
 */
@Entity
@Table(name = "tabcartaesercito")
public class Tabcartaesercito implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int idCartaEsercito;

    private int controllo;

    private Timestamp dataAggiornamento;

    private String dataEmissionePVC;

    private String dataInvioPROV;

    @Column(columnDefinition = "TEXT")
    private String dataProduzioneCarta;

    private String dataRevocaPVC;

    @Temporal(TemporalType.DATE)
    private Date dataRilascioCarta;

    @Temporal(TemporalType.DATE)
    private Date dataScadenzaCarta;

    private String dataStampaPVC;

    @Column(name = "email_rinnovo_inviata")
    private int emailRinnovoInviata;

    private int emessoPVC;

    private int inviato;

    private int letteraAccompagnamento;

    @Column(columnDefinition = "TEXT")
    private String motivazioneRevoca;

    private String numeroCarta;

    private int provvisorio;

    @Column(name = "rif_mandato")
    private int rifMandato;

    @Column(name = "rif_nucleofull")
    private int rifNucleofull;

    @Column(name = "rif_soggetto")
    private int rifSoggetto;

    private int rif_statoCarta;

    private int stampato;

    private String uid;

    public Tabcartaesercito() {
    }

    public int getIdCartaEsercito() {
        return this.idCartaEsercito;
    }

    public void setIdCartaEsercito(int idCartaEsercito) {
        this.idCartaEsercito = idCartaEsercito;
    }

    public int getControllo() {
        return this.controllo;
    }

    public void setControllo(int controllo) {
        this.controllo = controllo;
    }

    public Timestamp getDataAggiornamento() {
        return this.dataAggiornamento;
    }

    public void setDataAggiornamento(Timestamp dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }

    public String getDataEmissionePVC() {
        return this.dataEmissionePVC;
    }

    public void setDataEmissionePVC(String dataEmissionePVC) {
        this.dataEmissionePVC = dataEmissionePVC;
    }

    public String getDataInvioPROV() {
        return this.dataInvioPROV;
    }

    public void setDataInvioPROV(String dataInvioPROV) {
        this.dataInvioPROV = dataInvioPROV;
    }

    public String getDataProduzioneCarta() {
        return this.dataProduzioneCarta;
    }

    public void setDataProduzioneCarta(String dataProduzioneCarta) {
        this.dataProduzioneCarta = dataProduzioneCarta;
    }

    public String getDataRevocaPVC() {
        return this.dataRevocaPVC;
    }

    public void setDataRevocaPVC(String dataRevocaPVC) {
        this.dataRevocaPVC = dataRevocaPVC;
    }

    public Date getDataRilascioCarta() {
        return this.dataRilascioCarta;
    }

    public void setDataRilascioCarta(Date dataRilascioCarta) {
        this.dataRilascioCarta = dataRilascioCarta;
    }

    public Date getDataScadenzaCarta() {
        return this.dataScadenzaCarta;
    }

    public void setDataScadenzaCarta(Date dataScadenzaCarta) {
        this.dataScadenzaCarta = dataScadenzaCarta;
    }

    public String getDataStampaPVC() {
        return this.dataStampaPVC;
    }

    public void setDataStampaPVC(String dataStampaPVC) {
        this.dataStampaPVC = dataStampaPVC;
    }

    public int getEmailRinnovoInviata() {
        return this.emailRinnovoInviata;
    }

    public void setEmailRinnovoInviata(int emailRinnovoInviata) {
        this.emailRinnovoInviata = emailRinnovoInviata;
    }

    public int getEmessoPVC() {
        return this.emessoPVC;
    }

    public void setEmessoPVC(int emessoPVC) {
        this.emessoPVC = emessoPVC;
    }

    public int getInviato() {
        return this.inviato;
    }

    public void setInviato(int inviato) {
        this.inviato = inviato;
    }

    public int getLetteraAccompagnamento() {
        return this.letteraAccompagnamento;
    }

    public void setLetteraAccompagnamento(int letteraAccompagnamento) {
        this.letteraAccompagnamento = letteraAccompagnamento;
    }

    public String getMotivazioneRevoca() {
        return this.motivazioneRevoca;
    }

    public void setMotivazioneRevoca(String motivazioneRevoca) {
        this.motivazioneRevoca = motivazioneRevoca;
    }

    public String getNumeroCarta() {
        return this.numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public int getProvvisorio() {
        return this.provvisorio;
    }

    public void setProvvisorio(int provvisorio) {
        this.provvisorio = provvisorio;
    }

    public int getRifMandato() {
        return this.rifMandato;
    }

    public void setRifMandato(int rifMandato) {
        this.rifMandato = rifMandato;
    }

    public int getRifNucleofull() {
        return this.rifNucleofull;
    }

    public void setRifNucleofull(int rifNucleofull) {
        this.rifNucleofull = rifNucleofull;
    }

    public int getRifSoggetto() {
        return this.rifSoggetto;
    }

    public void setRifSoggetto(int rifSoggetto) {
        this.rifSoggetto = rifSoggetto;
    }

    public int getRif_statoCarta() {
        return this.rif_statoCarta;
    }

    public void setRif_statoCarta(int rif_statoCarta) {
        this.rif_statoCarta = rif_statoCarta;
    }

    public int getStampato() {
        return this.stampato;
    }

    public void setStampato(int stampato) {
        this.stampato = stampato;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
