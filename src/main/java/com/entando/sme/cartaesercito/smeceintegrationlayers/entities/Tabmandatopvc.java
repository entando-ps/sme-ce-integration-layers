package com.entando.sme.cartaesercito.smeceintegrationlayers.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the tabmandatopvc database table.
 */
@Entity
@Table(name = "tabMandatoPVC")
@NamedQuery(name = "Tabmandatopvc.findAll", query = "SELECT t FROM Tabmandatopvc t")
public class Tabmandatopvc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMandatoPVC;

    private String attestazionePagamento;

    private Timestamp dataAggiornamento;

    private String dataEmissionePVC;

    private String dataMandatoPVC;

    private String dataPagamento;

    private String dataVersamento;

    private String notaAnnullamento;

    private String notaConvalida;

    private String note;

    private String quotaMandato;

    private String quotaVersata;

    private int rifNucleoFull;

    private int rifOperatore;

    private int rifOperatorePagamento;

    private int rifOps;

    private int rifOpsPagamento;

    private int rifSponsor;

    private int rifStabilimento;

    private int rifStabilimentoPagamento;

    private int rifStatoMandatoPVC;

    private int rifTipoMandato;

    private int rifTipoPagamento;

    public Tabmandatopvc() {
    }

    public int getIdMandatoPVC() {
        return this.idMandatoPVC;
    }

    public void setIdMandatoPVC(int idMandatoPVC) {
        this.idMandatoPVC = idMandatoPVC;
    }

    public String getAttestazionePagamento() {
        return this.attestazionePagamento;
    }

    public void setAttestazionePagamento(String attestazionePagamento) {
        this.attestazionePagamento = attestazionePagamento;
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

    public String getDataMandatoPVC() {
        return this.dataMandatoPVC;
    }

    public void setDataMandatoPVC(String dataMandatoPVC) {
        this.dataMandatoPVC = dataMandatoPVC;
    }

    public String getDataPagamento() {
        return this.dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getDataVersamento() {
        return this.dataVersamento;
    }

    public void setDataVersamento(String dataVersamento) {
        this.dataVersamento = dataVersamento;
    }

    public String getNotaAnnullamento() {
        return this.notaAnnullamento;
    }

    public void setNotaAnnullamento(String notaAnnullamento) {
        this.notaAnnullamento = notaAnnullamento;
    }

    public String getNotaConvalida() {
        return this.notaConvalida;
    }

    public void setNotaConvalida(String notaConvalida) {
        this.notaConvalida = notaConvalida;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getQuotaMandato() {
        return this.quotaMandato;
    }

    public void setQuotaMandato(String quotaMandato) {
        this.quotaMandato = quotaMandato;
    }

    public String getQuotaVersata() {
        return this.quotaVersata;
    }

    public void setQuotaVersata(String quotaVersata) {
        this.quotaVersata = quotaVersata;
    }

    public int getRifNucleoFull() {
        return this.rifNucleoFull;
    }

    public void setRifNucleoFull(int rifNucleoFull) {
        this.rifNucleoFull = rifNucleoFull;
    }

    public int getRifOperatore() {
        return this.rifOperatore;
    }

    public void setRifOperatore(int rifOperatore) {
        this.rifOperatore = rifOperatore;
    }

    public int getRifOperatorePagamento() {
        return this.rifOperatorePagamento;
    }

    public void setRifOperatorePagamento(int rifOperatorePagamento) {
        this.rifOperatorePagamento = rifOperatorePagamento;
    }

    public int getRifOps() {
        return this.rifOps;
    }

    public void setRifOps(int rifOps) {
        this.rifOps = rifOps;
    }

    public int getRifOpsPagamento() {
        return this.rifOpsPagamento;
    }

    public void setRifOpsPagamento(int rifOpsPagamento) {
        this.rifOpsPagamento = rifOpsPagamento;
    }

    public int getRifSponsor() {
        return this.rifSponsor;
    }

    public void setRifSponsor(int rifSponsor) {
        this.rifSponsor = rifSponsor;
    }

    public int getRifStabilimento() {
        return this.rifStabilimento;
    }

    public void setRifStabilimento(int rifStabilimento) {
        this.rifStabilimento = rifStabilimento;
    }

    public int getRifStabilimentoPagamento() {
        return this.rifStabilimentoPagamento;
    }

    public void setRifStabilimentoPagamento(int rifStabilimentoPagamento) {
        this.rifStabilimentoPagamento = rifStabilimentoPagamento;
    }

    public int getRifStatoMandatoPVC() {
        return this.rifStatoMandatoPVC;
    }

    public void setRifStatoMandatoPVC(int rifStatoMandatoPVC) {
        this.rifStatoMandatoPVC = rifStatoMandatoPVC;
    }

    public int getRifTipoMandato() {
        return this.rifTipoMandato;
    }

    public void setRifTipoMandato(int rifTipoMandato) {
        this.rifTipoMandato = rifTipoMandato;
    }

    public int getRifTipoPagamento() {
        return this.rifTipoPagamento;
    }

    public void setRifTipoPagamento(int rifTipoPagamento) {
        this.rifTipoPagamento = rifTipoPagamento;
    }

    public Tabmandatopvc(String attestazionePagamento, Timestamp dataAggiornamento, String dataEmissionePVC, String dataMandatoPVC, String dataPagamento, String dataVersamento, String notaAnnullamento, String notaConvalida, String note, String quotaMandato, String quotaVersata, int rifOperatore, int rifOperatorePagamento, int rifOps, int rifOpsPagamento, int rifStabilimento, int rifStabilimentoPagamento, int rifStatoMandatoPVC, int rifTipoMandato, int rifTipoPagamento) {
        this.attestazionePagamento = attestazionePagamento;
        this.dataAggiornamento = dataAggiornamento;
        this.dataEmissionePVC = dataEmissionePVC;
        this.dataMandatoPVC = dataMandatoPVC;
        this.dataPagamento = dataPagamento;
        this.dataVersamento = dataVersamento;
        this.notaAnnullamento = notaAnnullamento;
        this.notaConvalida = notaConvalida;
        this.note = note;
        this.quotaMandato = quotaMandato;
        this.quotaVersata = quotaVersata;
        this.rifOperatore = rifOperatore;
        this.rifOperatorePagamento = rifOperatorePagamento;
        this.rifOps = rifOps;
        this.rifOpsPagamento = rifOpsPagamento;
        this.rifStabilimento = rifStabilimento;
        this.rifStabilimentoPagamento = rifStabilimentoPagamento;
        this.rifStatoMandatoPVC = rifStatoMandatoPVC;
        this.rifTipoMandato = rifTipoMandato;
        this.rifTipoPagamento = rifTipoPagamento;
    }

    public Tabmandatopvc(String[] attributes) {
        this(
                attributes[0],
                new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()),
                attributes[1],
                attributes[2],
                attributes[3],
                attributes[4],
                attributes[5],
                attributes[6],
                attributes[7],
                attributes[8],
                attributes[9],
                Integer.parseInt(attributes[10]),
                Integer.parseInt(attributes[11]),
                Integer.parseInt(attributes[12]),
                Integer.parseInt(attributes[13]),
                Integer.parseInt(attributes[14]),
                Integer.parseInt(attributes[15]),
                Integer.parseInt(attributes[16]),
                Integer.parseInt(attributes[17]),
                Integer.parseInt(attributes[18])
        );
    }
}
