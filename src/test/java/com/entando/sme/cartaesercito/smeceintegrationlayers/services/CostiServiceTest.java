package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CostiServiceTest {
    static Integer costoPerSponsor = 800;
    static Integer costoPerFamigliare = 400;
    static Integer costoPerOspite = 1000;
    static Integer costoSpedizione = 450;
    static Integer limiteNucleoFamigliarePrincipale = 2000;


    private CostiService costiService = new CostiService();


    @Test
    void calcoloCostiNuovoSponsor() {
        ModuloDTO moduloDTO = new ModuloDTO();
        moduloDTO.setSponsor(new ModuloDTO.Sponsor("codiceFiscaleSponsor"));

        List<ModuloDTO.Soggetto> nucleoPrincipale =
                IntStream.range(1, 5).mapToObj(index -> new ModuloDTO.Soggetto("codiceFiscaleSoggettoNP" + index)).collect(Collectors.toList());

        moduloDTO.setNucleoPrincipale(new ModuloDTO.Nucleo(nucleoPrincipale));

        List<ModuloDTO.Nucleo> nucleiEsterni =
                IntStream.range(1, 5).mapToObj(index -> {
                    List<ModuloDTO.Soggetto> componenti = IntStream.range(1, 10).mapToObj(i -> new ModuloDTO.Soggetto("codiceFiscaleSoggettoNS" + index + "-" + i)).collect(Collectors.toList());
                    return new ModuloDTO.Nucleo(componenti);
                }).collect(Collectors.toList());

        moduloDTO.setNucleiEsterni(nucleiEsterni);
        moduloDTO.setResidenzaDiSpedizione(new ModuloDTO.Residenza("cap", "citta", "civico", "presso", "provincia", "via"));
        CostiDTO costiDTO = costiService.calcoloCostiNuovoSponsor(moduloDTO);

        assertEquals(moduloDTO.getNucleoPrincipale().getComponenti().size() + 1, costiDTO.getNucleoPrincipaleConSponsor().size());
        assertEquals(moduloDTO.getNucleiEsterni().size(), costiDTO.getNucleiEsterni().size());
        IntStream.range(0, moduloDTO.getNucleiEsterni().size()).forEach(index -> {
                    ModuloDTO.Nucleo nucleoEsterno = moduloDTO.getNucleiEsterni().get(index);
                    CostiDTO.CostoPerNucleoEsternoDTO costoPerNucleoEsternoDTO = costiDTO.getNucleiEsterni().get(index);
                    assertEquals(nucleoEsterno.getComponenti().size(), costoPerNucleoEsternoDTO.getComponenti().size());
                    IntStream.range(0,nucleoEsterno.getComponenti().size()).forEach(i -> assertEquals(nucleoEsterno.getComponenti().get(i).getCodiceFiscale(),costoPerNucleoEsternoDTO.getComponenti().get(i).getSoggetto().getCodiceFiscale()));
                }
        );
        assertEquals(moduloDTO.getSponsor().getCodiceFiscale(), costiDTO.getNucleoPrincipaleConSponsor().get(0).getSoggetto().getCodiceFiscale());

        //nucleo principale
        assertEquals(costoPerSponsor + moduloDTO.getNucleoPrincipale().getComponenti().size() * costoPerFamigliare, costiDTO.calcolaTotaleNucleoPrincipaleConSponsor());
        //nuclei esterni
        assertEquals(moduloDTO.getNucleiEsterni().stream().map(ne-> ne.getComponenti().size()*costoPerOspite).reduce(0,Integer::sum),costiDTO.calcolaTotaleNucleiEsterni());
        //spedizione
        assertEquals(costoSpedizione, costiDTO.getCostoSpedizione());
        assertFalse(costiDTO.limiteNucleoPrincipaleSenzaSponsorSuperato());

    }
}
