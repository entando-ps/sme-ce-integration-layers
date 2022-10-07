package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.config.ConfigurationParameters;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CostiService {

    private final ConfigurationParameters.Costi configurazioneCosti;

    public CostiService(ConfigurationParameters configurationParameters) {
        this.configurazioneCosti = configurationParameters.getCosti();
    }


    public CostiDTO calcoloCostiNuovoSponsor(ModuloDTO moduloDTO) {
        //controllo che lo sponsor sia effettivamente nuovo
        ModuloDTO.Sponsor sponsor = moduloDTO.getSponsor();
        CostiDTO.CostoPerSoggettoDTO costoPerSoggettoDTO = new CostiDTO.CostoPerSoggettoDTO(sponsor, configurazioneCosti.getPerSponsor());
        List<CostiDTO.CostoPerSoggettoDTO > costiPerNucleoPrincipaleConSponsor = new ArrayList<>();
        costiPerNucleoPrincipaleConSponsor.add(costoPerSoggettoDTO);
        List<CostiDTO.CostoPerSoggettoDTO> costiPerNucleoPrincipale = moduloDTO.getNucleoPrincipale().getComponenti().stream().map(soggetto -> new CostiDTO.CostoPerSoggettoDTO(soggetto, configurazioneCosti.getPerFamigliare())).collect(Collectors.toList());
        costiPerNucleoPrincipaleConSponsor.addAll(costiPerNucleoPrincipale);

        List<CostiDTO.CostoPerNucleoEsternoDTO> costiPerNucleoEsterno= moduloDTO.getNucleiEsterni().stream().map(nucleoEsterno ->
                new CostiDTO.CostoPerNucleoEsternoDTO(nucleoEsterno.getComponenti().stream().map(soggetto -> new CostiDTO.CostoPerSoggettoDTO(soggetto, configurazioneCosti.getPerOspite())).collect(Collectors.toList()))
        ).collect(Collectors.toList());

        return new CostiDTO(costiPerNucleoPrincipaleConSponsor, costiPerNucleoEsterno, moduloDTO.getResidenzaDiSpedizione()!=null? configurazioneCosti.getSpedizione():0,configurazioneCosti.getLimiteNucleoFamigliarePrincipaleNoSponsor());

    }



}
