package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CostiDTOdopoSMETest {

    static CostiDTOdopoSME costiDTOdopoSME;
    static Integer costoPerSponsor = 800;
    static Integer costoPerFamigliare = 400;
    static Integer costoPerOspite = 1000;
    static Integer costoSpedizione = 450;
    static Integer limiteNucleoFamigliarePrincipale = 2000;

    @BeforeAll
    static void beforeAll() {
        ModuloDTODopoSME.Soggetto sponsor = new ModuloDTODopoSME.Soggetto();
        sponsor.setIsSponsor(true);
        List<CostiDTOdopoSME.CostoPerSoggettoDTO> nucleoPrincipaleConSponsor = List.of(new CostiDTOdopoSME.CostoPerSoggettoDTO(sponsor, costoPerSponsor), (new CostiDTOdopoSME.CostoPerSoggettoDTO(new ModuloDTODopoSME.Soggetto(), costoPerFamigliare)));

        List<CostiDTOdopoSME.CostoPerSoggettoDTO> componentiNucleoEsterno1 = List.of((new CostiDTOdopoSME.CostoPerSoggettoDTO(new ModuloDTODopoSME.Soggetto(), costoPerOspite)), new CostiDTOdopoSME.CostoPerSoggettoDTO(new ModuloDTODopoSME.Soggetto(), costoPerOspite));
        List<CostiDTOdopoSME.CostoPerSoggettoDTO> componentiNucleoEsterno2 = List.of((new CostiDTOdopoSME.CostoPerSoggettoDTO(new ModuloDTODopoSME.Soggetto(), costoPerOspite)), new CostiDTOdopoSME.CostoPerSoggettoDTO(new ModuloDTODopoSME.Soggetto(), costoPerOspite), new CostiDTOdopoSME.CostoPerSoggettoDTO(new ModuloDTODopoSME.Soggetto(), costoPerOspite));
        List<CostiDTOdopoSME.CostoPerNucleoEsternoDTO> nucleiEsterni = List.of(new CostiDTOdopoSME.CostoPerNucleoEsternoDTO(componentiNucleoEsterno1), new CostiDTOdopoSME.CostoPerNucleoEsternoDTO(componentiNucleoEsterno2));
        costiDTOdopoSME = new CostiDTOdopoSME(nucleoPrincipaleConSponsor, nucleiEsterni, costoSpedizione, limiteNucleoFamigliarePrincipale);
    }

    @Test
    void calcolaTotaleNucleoPrincipaleSenzaSponsor() {
        assertEquals((costiDTOdopoSME.getNucleoPrincipaleConSponsor().size() - 1) * costoPerFamigliare, costiDTOdopoSME.calcolaTotaleNucleoPrincipaleSenzaSponsor());
    }

    @Test
    void calcolaCostoSponsor() {
        assertEquals(costoPerSponsor, costiDTOdopoSME.calcolaCostoSponsor());
    }

    @Test
    void calcolaTotaleNucleoPrincipaleConSponsor() {
        assertEquals(costoPerSponsor + (costiDTOdopoSME.getNucleoPrincipaleConSponsor().size() - 1) * costoPerFamigliare, costiDTOdopoSME.calcolaTotaleNucleoPrincipaleConSponsor());
    }

    @Test
    void calcolaTotaleNucleiEsterni() {
        assertEquals(costiDTOdopoSME.getNucleiEsterni().get(0).getComponenti().size() * costoPerOspite + costiDTOdopoSME.getNucleiEsterni().get(1).getComponenti().size() * costoPerOspite, costiDTOdopoSME.calcolaTotaleNucleiEsterni());
    }

    @Test
    void calcolaTotaleSenzaSpedizione() {
        assertEquals(costoPerSponsor + (costiDTOdopoSME.getNucleoPrincipaleConSponsor().size() - 1) * costoPerFamigliare + costiDTOdopoSME.getNucleiEsterni().get(0).getComponenti().size() * costoPerOspite + costiDTOdopoSME.getNucleiEsterni().get(1).getComponenti().size() * costoPerOspite, costiDTOdopoSME.calcolaTotaleSenzaSpedizione());
    }

    @Test
    void limiteNucleoPrincipaleSuperato() {
        assertEquals((costoPerSponsor + (costiDTOdopoSME.getNucleoPrincipaleConSponsor().size() - 1) * costoPerFamigliare) > limiteNucleoFamigliarePrincipale, costiDTOdopoSME.limiteNucleoPrincipaleSuperato());
    }
}
