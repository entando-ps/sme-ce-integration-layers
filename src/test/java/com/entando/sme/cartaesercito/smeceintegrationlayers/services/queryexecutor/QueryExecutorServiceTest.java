package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
class QueryExecutorServiceTest {


    private final String codiceFiscaleSponsor;
    @Autowired
    private QueryExecutorService queryExecutorService;

    public QueryExecutorServiceTest() {
        codiceFiscaleSponsor = "GDFGMN70D16H501TL";
    }


    @Test
    void nucleoFamiliarePrincipaleSponsor(){
        queryExecutorService.nucleoFamiliarePrincipaleSponsor(codiceFiscaleSponsor).forEach(soggettoNucleoFamiliareDTOView -> System.out.println("soggettoNucleoFamiliareDTOView = " + soggettoNucleoFamiliareDTOView));

    }
    @Test
    void nucleoFamiliareEsternoSponsor() {
        queryExecutorService.nucleoFamiliareEsternoSponsor(codiceFiscaleSponsor).forEach(soggettoNucleoFamiliareDTOView -> System.out.println("soggettoNucleoFamiliareDTOView = " + soggettoNucleoFamiliareDTOView));

    }

    @Test
    void carteEsercitoPerNucleoFamiliarePrincipaleSponsor(){
        ModuloDTO moduloDTO = new ModuloDTO();
        ModuloDTO.Sponsor sponsor = new ModuloDTO.Sponsor();
        sponsor.setCodiceFiscale(codiceFiscaleSponsor);
        List<ModuloDTO.Soggetto> nucleoPrincipale = new ArrayList<>();
        nucleoPrincipale.add(new ModuloDTO.Soggetto("mockCF"));
        moduloDTO.setNucleoPrincipale(nucleoPrincipale);
        moduloDTO.setSponsor(sponsor);
        Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> stringStatoCartaEsercitoPerSoggettoDTOViewMap = queryExecutorService.carteEsercitoPerNucleoFamiliarePrincipaleSponsor(moduloDTO);
        System.out.println("stringStatoCartaEsercitoPerSoggettoDTOViewMap = " + stringStatoCartaEsercitoPerSoggettoDTOViewMap);

    }


}
