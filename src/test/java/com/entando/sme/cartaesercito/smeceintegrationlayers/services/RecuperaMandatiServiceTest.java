package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.MandatiDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.MandatiPVCDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RecuperaMandatiServiceTest {

    @Autowired
    RecuperaMandatiService recuperaMandatiService;

    @Test
    void recuperaMandatiPVC() {
        List<MandatiPVCDTO> mandatiPVC = recuperaMandatiService.recuperaMandatiPVC("testSp");
        System.out.println("mandatiPVC = " + mandatiPVC);
    }

    @Test
    void recuperaMandati(){
        List<MandatiDTO> mandati = recuperaMandatiService.recuperaMandati("testSp");
        System.out.println("mandati =" + mandati);
    }

}
