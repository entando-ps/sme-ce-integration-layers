package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto;

import lombok.AllArgsConstructor;
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
    private Nucleo nucleoPrincipale = new Nucleo(new ArrayList<>());
    private List<Nucleo> nucleiEsterni = new ArrayList<>();

    //se la residenza di spedizione non è null ha richiesto la spedizione postale
    //usato solo nella fase di creazione carta
    private Residenza residenzaDiSpedizione;
    //usato solo nella fase di creazione carta
    private Pagamento pagamento;

    @Data
    public static class Soggetto {
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

        public Soggetto(String codiceFiscale, String nome, String cognome, String email, String enteAppartenenza, String fototessera, String nascitaData, String nascitaLuogo, String nazionalita, int rifGradoQualifica, int rifPosizione, int rifRapporto, String sesso, String telCellulare, String telUfficio, Boolean isSponsor, Residenza residenza, CartaEsercito cartaEsercito, Integer stato) {
            this.codiceFiscale = codiceFiscale;
            this.nome = nome;
            this.cognome = cognome;
            this.email = email;
            this.enteAppartenenza = enteAppartenenza;
            this.fototessera = fototessera;
            this.nascitaData = nascitaData;
            this.nascitaLuogo = nascitaLuogo;
            this.nazionalita = nazionalita;
            this.rifGradoQualifica = rifGradoQualifica;
            this.rifPosizione = rifPosizione;
            this.rifRapporto = rifRapporto;
            this.sesso = sesso;
            this.telCellulare = telCellulare;
            this.telUfficio = telUfficio;
            this.isSponsor = isSponsor;
            this.residenza = residenza;
            this.cartaEsercito = cartaEsercito;
            this.stato = stato;
        }

        public Soggetto(String codiceFiscale) {
            this.codiceFiscale = codiceFiscale;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Sponsor extends Soggetto {
        public Sponsor() {
            this.setIsSponsor(true);
        }

        public Sponsor(Soggetto soggettoSponsor) {
            super(soggettoSponsor.getCodiceFiscale(),
                    soggettoSponsor.getNome(),
                    soggettoSponsor.getCognome(),
                    soggettoSponsor.getEmail(),
                    soggettoSponsor.getEnteAppartenenza(),
                    soggettoSponsor.getFototessera(),
                    soggettoSponsor.getNascitaData(),
                    soggettoSponsor.getNascitaLuogo(),
                    soggettoSponsor.getNazionalita(),
                    soggettoSponsor.getRifGradoQualifica(),
                    soggettoSponsor.getRifPosizione(),
                    soggettoSponsor.getRifRapporto(),
                    soggettoSponsor.getSesso(),
                    soggettoSponsor.getTelCellulare(),
                    soggettoSponsor.getTelUfficio(),
                    soggettoSponsor.getIsSponsor(),
                    soggettoSponsor.getResidenza(),
                    soggettoSponsor.getCartaEsercito(),
                    soggettoSponsor.getStato());
            if (!getIsSponsor()) {
                throw new RuntimeException("conversione di un Soggetto non sponsor! " + this);
            }
        }

        public Sponsor(String codiceFiscale) {
            super(codiceFiscale);
            this.setIsSponsor(true);
        }
    }

    @Data
    public static class Residenza {
        private String cap;
        private String citta;
        private String civico;
        private String presso;
        private String provincia;
        private String via;

        public Residenza() {
        }

        public Residenza(String cap, String citta, String civico, String presso, String provincia, String via) {
            this.cap = cap;
            this.citta = citta;
            this.civico = civico;
            this.presso = presso;
            this.provincia = provincia;
            this.via = via;
        }
    }

    @Data
    public static class Pagamento {
        private String cro;
    }

    @Data
    @AllArgsConstructor
    public static class CartaEsercito {
        private Integer stato;
        private String numero;
        private Date dataRilascio;
        private Date dataScadenza;
    }

    @Data
    public static class Nucleo {
        //lettura
        private Integer id;
        private List<Soggetto> componenti;

        public Nucleo(List<Soggetto> componenti) {
            this.componenti = componenti;
        }
    }


    public List<Soggetto> getAllSoggettiAsList() {
        List<Soggetto> ret = new ArrayList<>();
        ret.add(sponsor);
        ret.addAll(nucleoPrincipale.getComponenti());
        nucleiEsterni.stream().map(Nucleo::getComponenti).forEach(ret::addAll);
        return ret;
    }

    public List<Soggetto> getNucleoPrincipaleConSponsor() {
        List<Soggetto> ret = new ArrayList<>();
        ret.add(sponsor);
        ret.addAll(nucleoPrincipale.getComponenti());
        return ret;
    }

}


