package com.entando.sme.cartaesercito.smeceintegrationlayers.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import static com.entando.sme.cartaesercito.smeceintegrationlayers.Utils.parseString;


/**
 * The persistent class for the tabmandato database table.
 */
@Entity
@Table(name = "tabmandato")
@NamedQuery(name = "Tabmandato.findAll", query = "SELECT t FROM Tabmandato t")
public class Tabmandato implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMandato;

    private String attestazionePagamento;

    private int convalidaMandatoCovid;

    private Timestamp dataAggiornamento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataConvalida;

    @Temporal(TemporalType.DATE)
    private Date dataEmissione;

    private String dataMandato;

    @Temporal(TemporalType.DATE)
    private Date dataScadenza;

    @Temporal(TemporalType.DATE)
    private Date dataVersamento;

    //Fix mandati gruppo 1
    private int gruppo=1;

    @Column(columnDefinition = "TEXT")
    private String nota;

    @Column(columnDefinition = "TEXT")
    private String notaAnnullamento;

    @Column(columnDefinition = "TEXT")
    private String notaConvalida;

    private int quotaMandato;

    private int quotaVersata;

    @Column(name = "rif_nucleofull")
    private int rifNucleofull;

    @Column(name = "rif_operatore")
    private int rifOperatore;

    @Column(name = "rif_operatore_pagamento")
    private int rifOperatorePagamento;

    @Column(name = "rif_ops")
    private int rifOps;

    @Column(name = "rif_ops_pagamento")
    private int rifOpsPagamento;

    @Column(name = "rif_sponsor")
    private int rifSponsor;

    @Column(name = "rif_stabilimento")
    private int rifStabilimento;

    @Column(name = "rif_stabilimento_pagamento")
    private int rifStabilimentoPagamento;

    private int rif_statoMandato;

    private int rif_tipoPagamento;

    public Tabmandato() {
    }

    public int getIdMandato() {
        return this.idMandato;
    }

    public void setIdMandato(int idMandato) {
        this.idMandato = idMandato;
    }

    public String getAttestazionePagamento() {
        return this.attestazionePagamento;
    }

    public void setAttestazionePagamento(String attestazionePagamento) {
        this.attestazionePagamento = attestazionePagamento;
    }

    public int getConvalidaMandatoCovid() {
        return this.convalidaMandatoCovid;
    }

    public void setConvalidaMandatoCovid(int convalidaMandatoCovid) {
        this.convalidaMandatoCovid = convalidaMandatoCovid;
    }

    public Timestamp getDataAggiornamento() {
        return this.dataAggiornamento;
    }

    public void setDataAggiornamento(Timestamp dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }

    public Date getDataConvalida() {
        return this.dataConvalida;
    }

    public void setDataConvalida(Date dataConvalida) {
        this.dataConvalida = dataConvalida;
    }

    public Date getDataEmissione() {
        return this.dataEmissione;
    }

    public void setDataEmissione(Date dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public String getDataMandato() {
        return this.dataMandato;
    }

    public void setDataMandato(String dataMandato) {
        this.dataMandato = dataMandato;
    }

    public Date getDataScadenza() {
        return this.dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Date getDataVersamento() {
        return this.dataVersamento;
    }

    public void setDataVersamento(Date dataVersamento) {
        this.dataVersamento = dataVersamento;
    }

    public int getGruppo() {
        return this.gruppo;
    }

    public void setGruppo(int gruppo) {
        this.gruppo = gruppo;
    }

    public String getNota() {
        return this.nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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

    public int getQuotaMandato() {
        return this.quotaMandato;
    }

    public void setQuotaMandato(int quotaMandato) {
        this.quotaMandato = quotaMandato;
    }

    public int getQuotaVersata() {
        return this.quotaVersata;
    }

    public void setQuotaVersata(int quotaVersata) {
        this.quotaVersata = quotaVersata;
    }

    public int getRifNucleofull() {
        return this.rifNucleofull;
    }

    public void setRifNucleofull(int rifNucleofull) {
        this.rifNucleofull = rifNucleofull;
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

    public int getRif_statoMandato() {
        return this.rif_statoMandato;
    }

    public void setRif_statoMandato(int rif_statoMandato) {
        this.rif_statoMandato = rif_statoMandato;
    }

    public int getRif_tipoPagamento() {
        return this.rif_tipoPagamento;
    }

    public void setRif_tipoPagamento(int rif_tipoPagamento) {
        this.rif_tipoPagamento = rif_tipoPagamento;
    }

    public Tabmandato(String attestazionePagamento, int convalidaMandatoCovid, Timestamp dataAggiornamento, Date dataConvalida, Date dataEmissione, String dataMandato, Date dataScadenza, Date dataVersamento, int gruppo, String nota, String notaAnnullamento, String notaConvalida, int quotaMandato, int quotaVersata, int rifOperatore, int rifOperatorePagamento, int rifOps, int rifOpsPagamento, int rifStabilimento, int rifStabilimentoPagamento, int rif_statoMandato, int rif_tipoPagamento) {
        this.attestazionePagamento = attestazionePagamento;
        this.convalidaMandatoCovid = convalidaMandatoCovid;
        this.dataAggiornamento = dataAggiornamento;
        this.dataConvalida = dataConvalida;
        this.dataEmissione = dataEmissione;
        this.dataMandato = dataMandato;
        this.dataScadenza = dataScadenza;
        this.dataVersamento = dataVersamento;
        this.gruppo = gruppo;
        this.nota = nota;
        this.notaAnnullamento = notaAnnullamento;
        this.notaConvalida = notaConvalida;
        this.quotaMandato = quotaMandato;
        this.quotaVersata = quotaVersata;
        this.rifOperatore = rifOperatore;
        this.rifOperatorePagamento = rifOperatorePagamento;
        this.rifOps = rifOps;
        this.rifOpsPagamento = rifOpsPagamento;
        this.rifStabilimento = rifStabilimento;
        this.rifStabilimentoPagamento = rifStabilimentoPagamento;
        this.rif_statoMandato = rif_statoMandato;
        this.rif_tipoPagamento = rif_tipoPagamento;
    }

    public Tabmandato(String[] attributes) {
        this(
                attributes[0],
                Integer.parseInt(attributes[1]),
                new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()),
                parseString(attributes[2]),
                parseString(attributes[3]),
                attributes[4],
                parseString(attributes[5]),
                parseString(attributes[6]),
                Integer.parseInt(attributes[7]),
                attributes[8],
                attributes[9],
                attributes[10],
                Integer.parseInt(attributes[11]),
                Integer.parseInt(attributes[12]),
                Integer.parseInt(attributes[13]),
                Integer.parseInt(attributes[14]),
                Integer.parseInt(attributes[15]),
                Integer.parseInt(attributes[16]),
                Integer.parseInt(attributes[17]),
                Integer.parseInt(attributes[18]),
                Integer.parseInt(attributes[19]),
                Integer.parseInt(attributes[20])
                );
    }

}
