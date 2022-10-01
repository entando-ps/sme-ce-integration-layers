package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class SoggettoNucleoFamiliareDTOView {
    private Integer idSoggetto;
    private String codiceFiscale;
    private String cognome;
    private String nome;
    private String fototessera;
    private String nascitaData;
    private String nascitaLuogo;
    private String nazionalita;
    private boolean isCapofamiglia;
    private boolean isSponsor;

    public SoggettoNucleoFamiliareDTOView(ResultSet rs) throws SQLException {
        this.idSoggetto=rs.getInt("idSoggetto");
        this.codiceFiscale=rs.getString("codiceFiscale");
        this.cognome=rs.getString("cognome");
        this.nome=rs.getString("nome");
        this.fototessera=rs.getString("fototessera");
        this.nascitaData=rs.getString("nascitaData");
        this.nascitaLuogo=rs.getString("nascitaLuogo");
        this.nazionalita=rs.getString("nazionalita");
        this.isCapofamiglia=rs.getInt("is_capofamiglia")==1;
        this.isSponsor=rs.getInt("is_sponsor")==1;
    }

}
