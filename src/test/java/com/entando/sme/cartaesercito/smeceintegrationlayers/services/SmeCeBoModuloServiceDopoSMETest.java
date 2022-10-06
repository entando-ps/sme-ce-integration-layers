package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTODopoSME;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SmeCeBoModuloServiceDopoSMETest {

    @Autowired
    SmeCeBoModuloServiceDopoSME smeCeBoModuloService;

    @Test
    void inserimentoNuovoModulo() {


        smeCeBoModuloService.inserimentoNuovoModulo(creaNuovoModulo());
    }


    private ModuloDTODopoSME creaNuovoModulo() {
        ModuloDTODopoSME moduloDTO = new ModuloDTODopoSME();
        ModuloDTODopoSME.Soggetto testSp = creaNuovoSoggetto("testSp");
        testSp.setIsSponsor(true);
        ModuloDTODopoSME.Sponsor sponsor = new ModuloDTODopoSME.Sponsor(testSp);
        moduloDTO.setSponsor(sponsor);

        List<ModuloDTODopoSME.Soggetto> nucleoPrincipale =
                IntStream.range(1, 5).mapToObj(index -> creaNuovoSoggetto("NP"+index)).collect(Collectors.toList());

        moduloDTO.setNucleoPrincipale(new ModuloDTODopoSME.Nucleo(nucleoPrincipale));

        List<ModuloDTODopoSME.Nucleo> nucleiEsterni =
                IntStream.range(1, 5).mapToObj(index -> {
                    List<ModuloDTODopoSME.Soggetto> componenti = IntStream.range(1, 10).mapToObj(i -> creaNuovoSoggetto("NS"+index+"-"+i)).collect(Collectors.toList());
                    return new ModuloDTODopoSME.Nucleo(componenti);
                }).collect(Collectors.toList());

        moduloDTO.setNucleiEsterni(nucleiEsterni);

        moduloDTO.setPagamento(new ModuloDTODopoSME.Pagamento("cro-unico"));

        moduloDTO.setResidenzaDiSpedizione(new ModuloDTODopoSME.Residenza("cap", "citta", "civico", "presso", "provincia", "via"));


        return moduloDTO;
    }


    private ModuloDTODopoSME.Soggetto creaNuovoSoggetto(String cf) {
        EasyRandomParameters easyRandomParameters = new EasyRandomParameters();
        easyRandomParameters.setStringLengthRange(new EasyRandomParameters.Range<>(10, 10));
        EasyRandom generator = new EasyRandom(easyRandomParameters);

        ModuloDTODopoSME.Soggetto soggetto = generator.nextObject(ModuloDTODopoSME.Soggetto.class);
        soggetto.setCodiceFiscale(cf);
        return soggetto;

    }

}
