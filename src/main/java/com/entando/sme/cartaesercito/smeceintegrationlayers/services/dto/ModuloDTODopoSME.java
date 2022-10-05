package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Attenzione viene usato nella create, ma anche nella read e nell'update (mantenere gli id)
 * Dati ricevuti per la creazione delle istanze
 */
@Data
public class ModuloDTODopoSME {
    private Sponsor sponsor;
    //non contiene lo sponsor
    private List<Soggetto> nucleoPrincipale = new ArrayList<>();
    private List<NucleoEsterno> nucleiEsterni = new ArrayList<>();

    //se la residenza di spedizione non è null ha richiesto la spedizione postale
    private Residenza residenzaDiSpedizione;
    private Pagamento pagamento;

    @Data
    public static class Soggetto{
        private String codiceFiscale;
        private String nome;
        private String cognome;
        private String email;
        private String enteAppartenenza;
        private String fototessera;
        private String nascitaData;
        private String nascitaLuogo;
        private String nazionalita;
        private int rifGradoQualifica;
        private int rifPosizione;
        private int rifRapporto;
        private String sesso;
        private String telCellulare;
        private String telUfficio;
        private Boolean isSponsor = false;
        //non usato
        private Residenza residenza;
        //lettura
        private CartaEsercito cartaEsercito;
        //lettura
        private Integer stato;

        public Soggetto() {
        }

        public Soggetto(String codiceFiscale) {
            this.codiceFiscale = codiceFiscale;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Sponsor extends Soggetto{
        public Sponsor() {
            this.setIsSponsor(true);
        }

        public Sponsor(String codiceFiscale) {
            super(codiceFiscale);
            this.setIsSponsor(true);
        }
    }

    @Data
    public static class Residenza{
        private String cap;
        private String citta;
        private String civico;
        private String presso;
        private String provincia;
        private String via;
    }

    @Data
    public static class Pagamento{
        private String cro;
    }

    @Data
    private static class CartaEsercito{
        private Integer stato;
        private String numero;
        private Date dataRilascio;
        private Date dataScadenza;
    }

    @Data
    private static class NucleoEsterno{
        //lettura
        private Integer id;
        private List<Soggetto> componenti;
    }


    public List<Soggetto> getAllSoggettiAsList(){
        List<Soggetto> ret = new ArrayList<>();
        ret.add(sponsor);
        ret.addAll(nucleoPrincipale);
        nucleiEsterni.stream().map(NucleoEsterno::getComponenti).forEach(ret::addAll);
        return ret;
    }

    public List<Soggetto> getNucleoPrincipaleConSponsor(){
        List<Soggetto> ret = new ArrayList<>();
        ret.add(sponsor);
        ret.addAll(nucleoPrincipale);
        return ret;
    }

}


