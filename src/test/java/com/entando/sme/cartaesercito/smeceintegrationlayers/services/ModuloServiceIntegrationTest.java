package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabsoggetto;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.CartaEsercitoPerSoggettoPerNucleoDTOView;
import java.util.ArrayList;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ModuloServiceIntegrationTest {

    @Autowired
    ModuloService smeCeBoModuloService;
    @Autowired
    CostiService costiService;

    @Test
    void inserimentoNuovoModulo() {


        try {
			smeCeBoModuloService.inserimentoNuovoModulo(creaNuovoModulo());
		} catch (InserimentoModuloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    private ModuloDTO creaNuovoModulo() {
        ModuloDTO moduloDTO = new ModuloDTO();
        ModuloDTO.Soggetto testSp = creaNuovoSoggetto("testSp");
        testSp.setIsSponsor(true);
        ModuloDTO.Sponsor sponsor = new ModuloDTO.Sponsor(testSp);
        moduloDTO.setSponsor(sponsor);

        List<ModuloDTO.Soggetto> nucleoPrincipale =
                IntStream.range(1, 5).mapToObj(index -> creaNuovoSoggetto("NP" + index)).collect(Collectors.toList());

        moduloDTO.setNucleoPrincipale(new ModuloDTO.Nucleo(nucleoPrincipale));

        List<ModuloDTO.Nucleo> nucleiEsterni =
                IntStream.range(1, 5).mapToObj(index -> {
                    List<ModuloDTO.Soggetto> componenti = IntStream.range(1, 10).mapToObj(i -> creaNuovoSoggetto("NS" + index + "-" + i)).collect(Collectors.toList());
                    return new ModuloDTO.Nucleo(componenti);
                }).collect(Collectors.toList());

        nucleiEsterni.forEach(nucleo -> nucleo.setResidenzaDiSpedizione(new ModuloDTO.Residenza("cap", "citta", "civico", "presso", "provincia", "via")));

        moduloDTO.setNucleiEsterni(nucleiEsterni);


        moduloDTO.getNucleoPrincipale().setResidenzaDiSpedizione(new ModuloDTO.Residenza("cap", "citta", "civico", "presso", "provincia", "via"));

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
    
    @Test
    public void insertSoggetto(){
        EasyRandom easyRandom = new EasyRandom();
        CartaEsercitoPerSoggettoPerNucleoDTOView daConvertire = easyRandom.nextObject(CartaEsercitoPerSoggettoPerNucleoDTOView.class);
        ModuloDTO.Soggetto convertito = CartaEsercitoPerSoggettoPerNucleoDTOView.converti(daConvertire);
//        convertito.setCodiceFiscale("FMHVPZXEGNPGBTHP"); //prendo un soggetto già presente
        convertito.setCodiceFiscale("AAAAAAAAAAAAAAAA"); //prendo un soggetto non presente
        convertito.setRifAmministrazione(2);
        convertito.setRifGradoQualifica(2);
        convertito.setRifPosizione(2);
        log.info(convertito.toString());
        List<ModuloDTO.Soggetto> soggetti = new ArrayList<>();
        soggetti.add(convertito);
        log.info("** DA SALVARE -> "+soggetti.size());
        List<Tabsoggetto> tabSoggettiSalvati = smeCeBoModuloService.insertSoggettiAndResidenze(soggetti);
        log.info("** SALVATI -> "+tabSoggettiSalvati.size());
    }

}
