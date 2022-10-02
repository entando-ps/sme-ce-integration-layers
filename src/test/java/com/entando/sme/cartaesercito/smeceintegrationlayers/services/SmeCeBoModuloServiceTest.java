package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmeCeBoModuloServiceTest {

    @Autowired
    private SmeCeBoModuloService smeCeBoModuloService;
    @Autowired
    private SmeCeBoCostiService smeCeBoCostiService;

    @Test
    void moduloNucleiSuCEBODataBase() {

        EasyRandom generator = new EasyRandom();
        ModuloDTO moduloDTO = generator.nextObject(ModuloDTO.class);
        CostiDTO costiDTO = smeCeBoCostiService.calcoloCosti(moduloDTO);

        System.out.println("costiDTO.calcolaTotale() = " + costiDTO.calcolaTotaleSenzaSpedizione());
        System.out.println("costiDTO.getCostoSpedizione() = " + costiDTO.getCostoSpedizione());

        smeCeBoModuloService.moduloNucleiSuCEBODataBase(moduloDTO, costiDTO);


    }

}
