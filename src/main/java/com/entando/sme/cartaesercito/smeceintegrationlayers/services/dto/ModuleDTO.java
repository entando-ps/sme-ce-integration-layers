package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabsoggetto;
import lombok.Data;

import java.util.List;

/**
 * Dati ricevuti per la creazione delle istanze
 */
@Data
public class ModuleDTO {
    private Tabsoggetto sponsor;
    private List<Tabsoggetto> nucleoPrincipale;
    private List<List<Tabsoggetto>> nucleiEsterni;

    @Data
    public static class Sponsor extends Soggetto{

    }

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




    }
}


