package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabsoggetto;
import lombok.Data;

import java.util.List;

/**
 * Dati ricevuti per la creazione delle istanze
 */
@Data
public class ModuloDTO {
    private Tabsoggetto sponsor;
    private List<Soggetto> nucleoPrincipale;
    private List<List<Soggetto>> nucleiEsterni;
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
        private int rif_gradoQualifica;
        private int rifPosizione;
        private int rifRapporto;
        private String sesso;
        private String telCellulare;
        private String telUfficio;
        private Boolean isSponsor;
        private Boolean isCapofamiglia;
        private Residenza residenza;
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
        private Integer importo;
        private Integer importoSpedizione;
    }
}


