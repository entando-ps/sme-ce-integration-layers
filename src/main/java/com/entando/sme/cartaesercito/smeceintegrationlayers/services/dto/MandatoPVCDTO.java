package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MandatoPVCDTO {

    private Integer idmandatopvc;
    private Integer rifnucleofullPvc;
    private Integer quotamandatoPvc;  // non in centesimi
    private Integer rifsponsorPvc;

    private String notePvc;
    private String notaconvalidaPvc;
    private String notaannullamentoPvc;
    private Integer rifstatomandatoPvc;
    private String dataaggiornamentoPvc; // timestamp
    private String datamandatoPvc; // varchar
    private String dataemissionePvc; // varchar
    private Integer riftipomandatoPvc;

    private String cro;

    public MandatoPVCDTO(Integer idMandato, String cro, Integer rifStatoMandato, Integer quotaMandato, String dataMandato,Integer rifNucleoFull){
        this.idmandatopvc = idMandato;
        this.cro = cro;
        this.rifstatomandatoPvc = rifStatoMandato;
        this.quotamandatoPvc = quotaMandato;
        this.datamandatoPvc = dataMandato;
        this.rifnucleofullPvc = rifNucleoFull;
    }
}


