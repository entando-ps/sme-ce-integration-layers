package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * totti gli importi sono in centesimi
 */
@Data
@AllArgsConstructor
public class CostiDTO {
    private List<CostoPerSoggettoDTO> nucleoPrincipaleConSponsor;

    private List<CostoPerNucleoEsternoDTO> nucleiEsterni;

// necessario spostare su singoli nuclei
    private Integer costoSpedizioneNucleoPricipale;
    private Integer limiteNucleoFamigliarePrincipale;

    @Data
    @AllArgsConstructor
    public static class CostoPerSoggettoDTO {
        private ModuloDTO.Soggetto soggetto;
        private Integer costo;
    }

    @Data
    @AllArgsConstructor
    public static class CostoPerNucleoEsternoDTO {
        private List<CostoPerSoggettoDTO> componenti;

        private Integer costoSpedizioneNucleoEsterno;

        public Integer calcolaCosto() {
            return getComponenti().stream().map(CostoPerSoggettoDTO::getCosto).reduce(0, Integer::sum);
        }
    }

    public boolean limiteNucleoPrincipaleSenzaSponsorSuperato() {
        return calcolaTotaleNucleoPrincipaleSenzaSponsor() > limiteNucleoFamigliarePrincipale;
    }

    public int calcolaTotaleNucleoPrincipaleSenzaSponsor() {
        return nucleoPrincipaleConSponsor.stream().filter(costoPerSoggettoDTO -> !costoPerSoggettoDTO.getSoggetto().getIsSponsor()).map(CostoPerSoggettoDTO::getCosto).reduce(0, Integer::sum);
    }

    public int calcolaCostoSponsor() {
        return nucleoPrincipaleConSponsor.stream().filter(costoPerSoggettoDTO-> costoPerSoggettoDTO.getSoggetto().getIsSponsor()).map(CostoPerSoggettoDTO::getCosto).reduce(0, Integer::sum);
    }

    public int calcolaTotaleNucleoPrincipaleConSponsor() {
        return nucleoPrincipaleConSponsor.stream().map(CostoPerSoggettoDTO::getCosto).reduce(0, Integer::sum);

    }

    public int calcolaTotaleNucleiEsterni() {
        return nucleiEsterni.stream().map(CostoPerNucleoEsternoDTO::calcolaCosto).reduce(0, Integer::sum);
    }

    public int calcolaTotaleSenzaSpedizione() {
        return calcolaTotaleNucleiEsterni() + calcolaTotaleNucleoPrincipaleConSponsor();
    }

}
