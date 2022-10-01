package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.QueryExecutorService;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.StatoCartaEsercitoPerSoggettoDTOView;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
class SmeCeBoCostiServiceTest {


    private final String codiceFiscaleSponsor;

    @Autowired
    private SmeCeBoCostiService smeCeBoCostiService;

    @Autowired
    private QueryExecutorService queryExecutorService;

    public SmeCeBoCostiServiceTest() {
        codiceFiscaleSponsor = "GDFGMN70D16H501T";

    }

    @Test
    public void testUno() {
        ModuloDTO moduloDTO = new ModuloDTO();
        ModuloDTO.Soggetto sponsor = new ModuloDTO.Soggetto();
        sponsor.setCodiceFiscale(codiceFiscaleSponsor);
        sponsor.setIsSponsor(true);
        List<ModuloDTO.Soggetto> nucleoPrincipale = new ArrayList<>();
        nucleoPrincipale.add(new ModuloDTO.Soggetto("mockCF"));
        moduloDTO.setNucleoPrincipale(nucleoPrincipale);
        moduloDTO.setSponsor(sponsor);
        Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> stringStatoCartaEsercitoPerSoggettoDTOViewMap = queryExecutorService.carteEsercitoPerNucleoFamiliarePrincipaleSponsor(moduloDTO);
        List<CostiDTO.CostoPerSoggettoDTO> costoPerSoggettoDTOS = smeCeBoCostiService.calcoloCostiNucleoPrincipaleESponsor(stringStatoCartaEsercitoPerSoggettoDTOViewMap, moduloDTO);
        System.out.println("costoPerSoggettoDTOS = " + costoPerSoggettoDTOS);

    }

    @Test
    public void testDue() {
        ModuloDTO moduloDTO = new ModuloDTO();
        ModuloDTO.Soggetto sponsor = new ModuloDTO.Soggetto();
        sponsor.setCodiceFiscale(codiceFiscaleSponsor);
        sponsor.setIsSponsor(true);
        List<ModuloDTO.Soggetto> nucleoPrincipale = new ArrayList<>();
        nucleoPrincipale.add(new ModuloDTO.Soggetto("mockCF"));
        moduloDTO.setNucleoPrincipale(nucleoPrincipale);
        moduloDTO.setSponsor(sponsor);
        List<ModuloDTO.Soggetto> nucleoEsterno = new ArrayList<>();
        nucleoEsterno.add(new ModuloDTO.Soggetto("mockCFEsterno"));
        nucleoEsterno.add(new ModuloDTO.Soggetto("mockCFEsterno1"));
        List<List<ModuloDTO.Soggetto>> nucleiEsterni = new ArrayList<>();
        nucleiEsterni.add(nucleoEsterno);
        moduloDTO.setNucleiEsterni(nucleiEsterni);
        CostiDTO costiDTO = smeCeBoCostiService.calcoloCosti(moduloDTO);
        System.out.println("costiDTO = " + costiDTO);
        System.out.println("costiDTO calcolaTotale = " + costiDTO.calcolaTotale());
        System.out.println("costiDTO calcolaTotaleNucleiEsterni = " + costiDTO.calcolaTotaleNucleiEsterni());
        System.out.println("costiDTO calcolaTotaleNucleoPrincipaleConSponsor = " + costiDTO.calcolaTotaleNucleoPrincipaleConSponsor());
    }
}
