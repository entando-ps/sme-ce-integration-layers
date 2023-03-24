package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.MandatoDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.MandatoPVCDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class RecuperaMandatiServiceTest {

    @Autowired
    RecuperaMandatiService recuperaMandatiService;

    @Test
    @Disabled
    void recuperaMandatiPVC() {
        List<MandatoPVCDTO> mandatiPVC = recuperaMandatiService.recuperaMandatiPVC("testSp");
        log.info("mandatiPVC = " + mandatiPVC);
    }

    @Test
    @Disabled
    void recuperaMandati(){
        List<MandatoDTO> mandati = recuperaMandatiService.recuperaMandati("testSp");
        log.info("mandati =" + mandati);
    }

}
