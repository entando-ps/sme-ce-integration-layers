package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Dati ricevuti per la creazione delle istanze
 */
@Data
public class ModuloDTO {
    private Soggetto sponsor;
    //non contiene lo sponsor
    private List<Soggetto> nucleoPrincipale = new ArrayList<>();
    private List<List<Soggetto>> nucleiEsterni = new ArrayList<>();

    //se la residenza di spedizione
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
        private Boolean isCapofamiglia = false;
        private Boolean isSponsor = false;
        private Residenza residenza;

        public Soggetto() {
        }

        public Soggetto(String codiceFiscale) {
            this.codiceFiscale = codiceFiscale;
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

    public List<Soggetto> getAllSoggettiAsList(){
        List<Soggetto> ret = new ArrayList<>();
        ret.add(sponsor);
        ret.addAll(nucleoPrincipale);
        nucleiEsterni.forEach(ret::addAll);
        return ret;
    }

    public List<Soggetto> getNucleoPrincipaleConSponsor(){
        List<Soggetto> ret = new ArrayList<>();
        ret.add(sponsor);
        ret.addAll(nucleoPrincipale);
        return ret;
    }

}


