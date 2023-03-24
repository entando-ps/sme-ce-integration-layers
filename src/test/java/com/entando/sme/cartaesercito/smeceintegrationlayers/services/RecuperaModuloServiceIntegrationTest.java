package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class RecuperaModuloServiceIntegrationTest {

    @Autowired
    RecuperaModuloService recuperaModuloService;

    @Test
    void recuperaModulo() {
        ModuloDTO moduloDTO = recuperaModuloService.recuperaModulo("testSp");
        log.info("moduloDTO = " + moduloDTO);
    }
}
