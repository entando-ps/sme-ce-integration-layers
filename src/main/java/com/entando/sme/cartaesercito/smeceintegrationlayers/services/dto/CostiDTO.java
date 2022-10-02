package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Data
public class CostiDTO {
    private List<CostoPerSoggettoDTO> nucleoPrincipaleConSponsor = new ArrayList<>();
    ;
    private List<List<CostoPerSoggettoDTO>> nucleiEsterni = new ArrayList<>();


    private Integer costoSpedizione;

    @Data
    public static class CostoPerSoggettoDTO {
        private ModuloDTO.Soggetto soggetto;
        private Integer statoCarta;
        private Integer costo;

    }

    public boolean limiteNucleoPrincipaleSuperato() {
        return calcolaTotaleSenzaSpedizione() > 20 * 100;
    }

    public int calcolaTotaleNucleoPrincipaleConSponsor() {
        return nucleoPrincipaleConSponsor.stream().map(CostoPerSoggettoDTO::getCosto).reduce(0, Integer::sum);

    }

    public int calcolaTotaleNucleiEsterni() {
        return nucleiEsterni.stream().flatMap(Collection::stream).map(CostoPerSoggettoDTO::getCosto).reduce(0, Integer::sum);
    }

    public int calcolaTotaleSenzaSpedizione() {
        return calcolaTotaleNucleiEsterni() + calcolaTotaleNucleoPrincipaleConSponsor();
    }

    public CostoPerSoggettoDTO getCostoPerSoggettoDTOSponsor(){
        Optional<CostoPerSoggettoDTO> sponsorOptional = nucleoPrincipaleConSponsor.stream().filter(costoPerSoggettoDTO -> costoPerSoggettoDTO.getSoggetto().getIsSponsor()).findAny();
        return sponsorOptional.orElseThrow(() -> new RuntimeException("Non esiste uno sponsor per questo modulo: "+ this));
    }

}
