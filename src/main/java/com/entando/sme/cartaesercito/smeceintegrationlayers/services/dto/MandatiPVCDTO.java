package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MandatiPVCDTO {

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


}


