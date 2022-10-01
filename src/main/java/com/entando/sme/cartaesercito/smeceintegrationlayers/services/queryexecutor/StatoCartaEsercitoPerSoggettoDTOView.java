package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class StatoCartaEsercitoPerSoggettoDTOView {
    private String codiceFiscale;
    private Integer statoCarta;
    private Date dataRilascioCarta;
    private Date dataScadenzaCarta;
    /*
    1,In corso di validità
    2,Scaduta
    3,Sospesa
    4,Revocata
    5,Smarrita
    6,Deteriorata
    7,In scadenza
    8,Requisiti persi
     */
}

