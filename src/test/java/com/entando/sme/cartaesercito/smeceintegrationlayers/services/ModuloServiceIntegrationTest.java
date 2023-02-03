package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class ModuloServiceIntegrationTest {

    @Autowired
    ModuloService smeCeBoModuloService;
    @Autowired
    CostiService costiService;
    @Test
    void inserimentoNuovoModulo() {


        smeCeBoModuloService.inserimentoNuovoModulo(creaNuovoModulo());
    }


    private ModuloDTO creaNuovoModulo() {
        ModuloDTO moduloDTO = new ModuloDTO();
        ModuloDTO.Soggetto testSp = creaNuovoSoggetto("testSp");
        testSp.setIsSponsor(true);
        ModuloDTO.Sponsor sponsor = new ModuloDTO.Sponsor(testSp);
        moduloDTO.setSponsor(sponsor);

        List<ModuloDTO.Soggetto> nucleoPrincipale =
                IntStream.range(1, 5).mapToObj(index -> creaNuovoSoggetto("NP"+index)).collect(Collectors.toList());

        moduloDTO.setNucleoPrincipale(new ModuloDTO.Nucleo(nucleoPrincipale));

        List<ModuloDTO.Nucleo> nucleiEsterni =
                IntStream.range(1, 5).mapToObj(index -> {
                    List<ModuloDTO.Soggetto> componenti = IntStream.range(1, 10).mapToObj(i -> creaNuovoSoggetto("NS"+index+"-"+i)).collect(Collectors.toList());
                    return new ModuloDTO.Nucleo(componenti);
                }).collect(Collectors.toList());

        nucleiEsterni.forEach(nucleo -> nucleo.setResidenzaDiSpedizione(new ModuloDTO.Residenza("cap", "citta", "civico", "presso", "provincia", "via")));

        moduloDTO.setNucleiEsterni(nucleiEsterni);


        moduloDTO.setPagamento(new ModuloDTO.Pagamento("cro-unico"));

        moduloDTO.getNucleoPrincipale().setResidenzaDiSpedizione(new ModuloDTO.Residenza("cap", "citta", "civico", "presso", "provincia", "via"));

//        System.out.println("AAAAAAAAAAAA" + costiService.calcoloCostiNuovoSponsor(moduloDTO).getCostoSpedizioneNucleoPricipale());
//        costiService.calcoloCostiNuovoSponsor(moduloDTO).getNucleiEsterni().forEach(nucleo -> System.out.println("B      " + nucleo.getCostoSpedizioneNucleoEsterno()));
//        moduloDTO.setResidenzaDiSpedizione(new ModuloDTO.Residenza("cap", "citta", "civico", "presso", "provincia", "via"));
//        System.out.println("AAAAAAAAAAAA" + costiService.calcoloCostiNuovoSponsor(moduloDTO).getCostoSpedizione());
//    System.out.println(moduloDTO);
        return moduloDTO;
    }


    private ModuloDTO.Soggetto creaNuovoSoggetto(String cf) {
        EasyRandomParameters easyRandomParameters = new EasyRandomParameters();
        easyRandomParameters.setStringLengthRange(new EasyRandomParameters.Range<>(10, 10));
        EasyRandom generator = new EasyRandom(easyRandomParameters);

        ModuloDTO.Soggetto soggetto = generator.nextObject(ModuloDTO.Soggetto.class);
        soggetto.setCodiceFiscale(cf);
        return soggetto;

    }

}
