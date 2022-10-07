package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CostiDTOTest {

    static CostiDTO costiDTO;
    static Integer costoPerSponsor = 800;
    static Integer costoPerFamigliare = 400;
    static Integer costoPerOspite = 1000;
    static Integer costoSpedizione = 450;
    static Integer limiteNucleoFamigliarePrincipale = 2000;

    @BeforeAll
    static void beforeAll() {
        ModuloDTO.Soggetto sponsor = new ModuloDTO.Soggetto();
        sponsor.setIsSponsor(true);
        List<CostiDTO.CostoPerSoggettoDTO> nucleoPrincipaleConSponsor = List.of(new CostiDTO.CostoPerSoggettoDTO(sponsor, costoPerSponsor), (new CostiDTO.CostoPerSoggettoDTO(new ModuloDTO.Soggetto(), costoPerFamigliare)));

        List<CostiDTO.CostoPerSoggettoDTO> componentiNucleoEsterno1 = List.of((new CostiDTO.CostoPerSoggettoDTO(new ModuloDTO.Soggetto(), costoPerOspite)), new CostiDTO.CostoPerSoggettoDTO(new ModuloDTO.Soggetto(), costoPerOspite));
        List<CostiDTO.CostoPerSoggettoDTO> componentiNucleoEsterno2 = List.of((new CostiDTO.CostoPerSoggettoDTO(new ModuloDTO.Soggetto(), costoPerOspite)), new CostiDTO.CostoPerSoggettoDTO(new ModuloDTO.Soggetto(), costoPerOspite), new CostiDTO.CostoPerSoggettoDTO(new ModuloDTO.Soggetto(), costoPerOspite));
        List<CostiDTO.CostoPerNucleoEsternoDTO> nucleiEsterni = List.of(new CostiDTO.CostoPerNucleoEsternoDTO(componentiNucleoEsterno1), new CostiDTO.CostoPerNucleoEsternoDTO(componentiNucleoEsterno2));
        costiDTO = new CostiDTO(nucleoPrincipaleConSponsor, nucleiEsterni, costoSpedizione, limiteNucleoFamigliarePrincipale);
    }

    @Test
    void calcolaTotaleNucleoPrincipaleSenzaSponsor() {
        assertEquals((costiDTO.getNucleoPrincipaleConSponsor().size() - 1) * costoPerFamigliare, costiDTO.calcolaTotaleNucleoPrincipaleSenzaSponsor());
    }

    @Test
    void calcolaCostoSponsor() {
        assertEquals(costoPerSponsor, costiDTO.calcolaCostoSponsor());
    }

    @Test
    void calcolaTotaleNucleoPrincipaleConSponsor() {
        assertEquals(costoPerSponsor + (costiDTO.getNucleoPrincipaleConSponsor().size() - 1) * costoPerFamigliare, costiDTO.calcolaTotaleNucleoPrincipaleConSponsor());
    }

    @Test
    void calcolaTotaleNucleiEsterni() {
        assertEquals(costiDTO.getNucleiEsterni().get(0).getComponenti().size() * costoPerOspite + costiDTO.getNucleiEsterni().get(1).getComponenti().size() * costoPerOspite, costiDTO.calcolaTotaleNucleiEsterni());
    }

    @Test
    void calcolaTotaleSenzaSpedizione() {
        assertEquals(costoPerSponsor + (costiDTO.getNucleoPrincipaleConSponsor().size() - 1) * costoPerFamigliare + costiDTO.getNucleiEsterni().get(0).getComponenti().size() * costoPerOspite + costiDTO.getNucleiEsterni().get(1).getComponenti().size() * costoPerOspite, costiDTO.calcolaTotaleSenzaSpedizione());
    }

    @Test
    void limiteNucleoPrincipaleSuperato() {
        assertEquals((costoPerSponsor + (costiDTO.getNucleoPrincipaleConSponsor().size() - 1) * costoPerFamigliare) > limiteNucleoFamigliarePrincipale, costiDTO.limiteNucleoPrincipaleSenzaSponsorSuperato());
    }
}
