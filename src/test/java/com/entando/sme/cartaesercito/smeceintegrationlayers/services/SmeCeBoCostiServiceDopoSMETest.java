package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTOdopoSME;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTODopoSME;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SmeCeBoCostiServiceDopoSMETest {
    static Integer costoPerSponsor = 800;
    static Integer costoPerFamigliare = 400;
    static Integer costoPerOspite = 1000;
    static Integer costoSpedizione = 450;
    static Integer limiteNucleoFamigliarePrincipale = 2000;


    private SmeCeBoCostiServiceDopoSME smeCeBoCostiServiceDopoSME = new SmeCeBoCostiServiceDopoSME(null);


    @Test
    void calcoloCostiNuovoSponsor() {
        ModuloDTODopoSME moduloDTO = new ModuloDTODopoSME();
        moduloDTO.setSponsor(new ModuloDTODopoSME.Sponsor("codiceFiscaleSponsor"));

        List<ModuloDTODopoSME.Soggetto> nucleoPrincipale =
                IntStream.range(1, 5).mapToObj(index -> new ModuloDTODopoSME.Soggetto("codiceFiscaleSoggettoNP" + index)).collect(Collectors.toList());

        moduloDTO.setNucleoPrincipale(nucleoPrincipale);

        List<ModuloDTODopoSME.NucleoEsterno> nucleiEsterni =
                IntStream.range(1, 5).mapToObj(index -> {
                    List<ModuloDTODopoSME.Soggetto> componenti = IntStream.range(1, 10).mapToObj(i -> new ModuloDTODopoSME.Soggetto("codiceFiscaleSoggettoNS" + index + "-" + i)).collect(Collectors.toList());
                    return new ModuloDTODopoSME.NucleoEsterno(componenti);
                }).collect(Collectors.toList());

        moduloDTO.setNucleiEsterni(nucleiEsterni);
        moduloDTO.setResidenzaDiSpedizione(new ModuloDTODopoSME.Residenza("cap", "citta", "civico", "presso", "provincia", "via"));
        CostiDTOdopoSME costiDTO = smeCeBoCostiServiceDopoSME.calcoloCostiNuovoSponsor(moduloDTO);

        assertEquals(moduloDTO.getNucleoPrincipale().size() + 1, costiDTO.getNucleoPrincipaleConSponsor().size());
        assertEquals(moduloDTO.getNucleiEsterni().size(), costiDTO.getNucleiEsterni().size());
        IntStream.range(0, moduloDTO.getNucleiEsterni().size()).forEach(index -> {
                    ModuloDTODopoSME.NucleoEsterno nucleoEsterno = moduloDTO.getNucleiEsterni().get(index);
                    CostiDTOdopoSME.CostoPerNucleoEsternoDTO costoPerNucleoEsternoDTO = costiDTO.getNucleiEsterni().get(index);
                    assertEquals(nucleoEsterno.getComponenti().size(), costoPerNucleoEsternoDTO.getComponenti().size());
                    IntStream.range(0,nucleoEsterno.getComponenti().size()).forEach(i -> assertEquals(nucleoEsterno.getComponenti().get(i).getCodiceFiscale(),costoPerNucleoEsternoDTO.getComponenti().get(i).getSoggetto().getCodiceFiscale()));
                }
        );
        assertEquals(moduloDTO.getSponsor().getCodiceFiscale(), costiDTO.getNucleoPrincipaleConSponsor().get(0).getSoggetto().getCodiceFiscale());

        //nucleo principale
        assertEquals(costoPerSponsor + moduloDTO.getNucleoPrincipale().size() * costoPerFamigliare, costiDTO.calcolaTotaleNucleoPrincipaleConSponsor());
        //nuclei esterni
        assertEquals(moduloDTO.getNucleiEsterni().stream().map(ne-> ne.getComponenti().size()*costoPerOspite).reduce(0,Integer::sum),costiDTO.calcolaTotaleNucleiEsterni());
        //spedizione
        assertEquals(costoSpedizione, costiDTO.getCostoSpedizione());
        assertFalse(costiDTO.limiteNucleoPrincipaleSuperato());

    }
}
