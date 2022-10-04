package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.QueryExecutorService;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.StatoCartaEsercitoPerSoggettoDTOView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LogicheCostiTest {

    private final int costoSponsor=8*100;
    private final int costoFamigliareNucleoPrincipale=4*100;
    private final int costoFamigliareNucleoEsterno=10*100;


    @Autowired
    private SmeCeBoCostiService smeCeBoCostiService;



    @Test
    public void calcoloCostiNucleoPrincipaleESponsorNoSoglia() {
        ModuloDTO moduloDTO = new ModuloDTO();
        ModuloDTO.Sponsor sponsor = new ModuloDTO.Sponsor();
        sponsor.setCodiceFiscale("codiceFiscaleSponsor");
        moduloDTO.setSponsor(sponsor);
        List<ModuloDTO.Soggetto> nucleoPrincipale = new ArrayList<>();
        IntStream.range(0, 3).forEach(index -> nucleoPrincipale.add(new ModuloDTO.Soggetto("mockCF" + index)));
        moduloDTO.setNucleoPrincipale(nucleoPrincipale);
        //Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> stringStatoCartaEsercitoPerSoggettoDTOViewMap = queryExecutorService.carteEsercitoPerNucleoFamiliarePrincipaleSponsor(moduloDTO);
        Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> stringStatoCartaEsercitoPerSoggettoDTOViewMap = new HashMap<>();

        List<CostiDTO.CostoPerSoggettoDTO> costiNucleoPrincipaleConSponsor = smeCeBoCostiService.calcoloCostiNucleoPrincipaleESponsor(stringStatoCartaEsercitoPerSoggettoDTOViewMap, moduloDTO.getNucleoPrincipaleConSponsor());
        assertEquals(moduloDTO.getNucleoPrincipale().size() + 1, costiNucleoPrincipaleConSponsor.size());

        CostiDTO costiDTO = new CostiDTO();
        costiDTO.setNucleoPrincipaleConSponsor(costiNucleoPrincipaleConSponsor);
        assertEquals((costoSponsor+nucleoPrincipale.size()*costoFamigliareNucleoPrincipale),costiDTO.calcolaTotaleNucleoPrincipaleConSponsor());
        assertEquals((nucleoPrincipale.size()*costoFamigliareNucleoPrincipale),costiDTO.calcolaTotaleNucleoPrincipaleSenzaSponsor());
        assertEquals((costoSponsor),costiDTO.calcolaCostoSponsor());

        assertFalse(costiDTO.limiteNucleoPrincipaleSuperato());
    }

    @Test
    public void calcoloCostiNucleoPrincipaleESponsorSopraSoglia() {
        ModuloDTO moduloDTO = new ModuloDTO();
        ModuloDTO.Sponsor sponsor = new ModuloDTO.Sponsor();
        sponsor.setCodiceFiscale("codiceFiscaleSponsor");
        moduloDTO.setSponsor(sponsor);
        List<ModuloDTO.Soggetto> nucleoPrincipale = new ArrayList<>();
        IntStream.range(0, 10).forEach(index -> nucleoPrincipale.add(new ModuloDTO.Soggetto("mockCF" + index)));
        moduloDTO.setNucleoPrincipale(nucleoPrincipale);
        Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> stringStatoCartaEsercitoPerSoggettoDTOViewMap = new HashMap<>();

        List<CostiDTO.CostoPerSoggettoDTO> costiNucleoPrincipaleConSponsor = smeCeBoCostiService.calcoloCostiNucleoPrincipaleESponsor(stringStatoCartaEsercitoPerSoggettoDTOViewMap, moduloDTO.getNucleoPrincipaleConSponsor());
        assertEquals(moduloDTO.getNucleoPrincipale().size() + 1, costiNucleoPrincipaleConSponsor.size());

        CostiDTO costiDTO = new CostiDTO();
        costiDTO.setNucleoPrincipaleConSponsor(costiNucleoPrincipaleConSponsor);
        assertEquals((costoSponsor+nucleoPrincipale.size()*costoFamigliareNucleoPrincipale),costiDTO.calcolaTotaleNucleoPrincipaleConSponsor());
        assertEquals((nucleoPrincipale.size()*costoFamigliareNucleoPrincipale),costiDTO.calcolaTotaleNucleoPrincipaleSenzaSponsor());
        assertEquals((costoSponsor),costiDTO.calcolaCostoSponsor());

        assertTrue(costiDTO.limiteNucleoPrincipaleSuperato());
    }

    @Test
    public void calcoloCostiNucleoEsterno() {

        ModuloDTO moduloDTO = new ModuloDTO();
        ModuloDTO.Sponsor sponsor = new ModuloDTO.Sponsor();
        sponsor.setCodiceFiscale("codiceFiscaleSponsor");
        moduloDTO.setSponsor(sponsor);
        List<ModuloDTO.Soggetto> nucleoPrincipale = new ArrayList<>();
        IntStream.range(0, 3).forEach(index -> nucleoPrincipale.add(new ModuloDTO.Soggetto("mockCF" + index)));
        moduloDTO.setNucleoPrincipale(nucleoPrincipale);
        //Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> stringStatoCartaEsercitoPerSoggettoDTOViewMap = queryExecutorService.carteEsercitoPerNucleoFamiliarePrincipaleSponsor(moduloDTO);
        Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> stringStatoCartaEsercitoPerSoggettoDTOViewMap = new HashMap<>();

        List<CostiDTO.CostoPerSoggettoDTO> costiNucleoPrincipaleConSponsor = smeCeBoCostiService.calcoloCostiNucleoPrincipaleESponsor(stringStatoCartaEsercitoPerSoggettoDTOViewMap, moduloDTO.getNucleoPrincipaleConSponsor());


        List<ModuloDTO.Soggetto> nucleoEsterno = new ArrayList<>();
        IntStream.range(0, 5).forEach(index -> nucleoEsterno.add(new ModuloDTO.Soggetto("mockCFEsterno" + index)));
        List<List<ModuloDTO.Soggetto>> nucleiEsterni = new ArrayList<>();
        nucleiEsterni.add(nucleoEsterno);
        moduloDTO.setNucleiEsterni(nucleiEsterni);
        List<List<CostiDTO.CostoPerSoggettoDTO>> costiNucleoEsterno = smeCeBoCostiService.calcoloCostiNucleiEsterni(stringStatoCartaEsercitoPerSoggettoDTOViewMap, moduloDTO.getNucleiEsterni());


        assertEquals(moduloDTO.getNucleoPrincipale().size() + 1, costiNucleoPrincipaleConSponsor.size());
        assertEquals(moduloDTO.getNucleiEsterni().size(), costiNucleoEsterno.size());
        assertEquals(moduloDTO.getNucleiEsterni().get(0).size(), costiNucleoEsterno.get(0).size());

        CostiDTO costiDTO = new CostiDTO();
        costiDTO.setNucleoPrincipaleConSponsor(costiNucleoPrincipaleConSponsor);
        costiDTO.setNucleiEsterni(costiNucleoEsterno);

        assertEquals((nucleoEsterno.size()*costoFamigliareNucleoEsterno),costiDTO.calcolaTotaleNucleiEsterni());
        assertEquals(1,costiDTO.calcolaTotalePerNucleoEsterni().size());
        assertEquals((nucleoEsterno.size()*costoFamigliareNucleoEsterno),costiDTO.calcolaTotalePerNucleoEsterni().get(0));

    }
}
