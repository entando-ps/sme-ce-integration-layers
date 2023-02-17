package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MandatoServiceTest {

    @Autowired
    MandatoService mandatoService;
    @Test
    void aggiornaMandato() {
        mandatoService.aggiornaMandatoConCRO(25, "cro-update");
    }

    @Test
    void aggiornaMandatoPVC() {
        mandatoService.aggiornaMandatoPVCConCRO(25, "cro-update");
    }

}
