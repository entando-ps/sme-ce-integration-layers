package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MandatoServiceTest {

    //inserire un codice valido per non ricevere errore
    private Integer codiceMandato = 4;
    private Integer codiceMandatoPVC = 26;
    @Autowired
    MandatoService mandatoService;
    @Test
    void aggiornaMandato() {
        mandatoService.aggiornaMandatoConCRO(codiceMandato, "cro-update");
    }

    @Test
    void aggiornaMandatoPVC() {
        mandatoService.aggiornaMandatoPVCConCRO(codiceMandatoPVC, "cro-update");
    }

}
