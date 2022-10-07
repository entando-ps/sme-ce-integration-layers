package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecuperaModuloServiceIntegrationTest {

    @Autowired
    RecuperaModuloService recuperaModuloService;

    @Test
    void recuperaModulo() {
        ModuloDTO moduloDTO = recuperaModuloService.recuperaModulo("testSp");
        System.out.println("moduloDTODopoSME = " + moduloDTO);
    }
}
