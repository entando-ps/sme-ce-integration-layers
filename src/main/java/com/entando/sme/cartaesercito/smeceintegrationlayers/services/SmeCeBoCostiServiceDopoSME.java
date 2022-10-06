package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTOdopoSME;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTODopoSME;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.QueryExecutorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmeCeBoCostiServiceDopoSME {
    private Integer costoPerSponsor = 800;
    private Integer costoPerFamigliare = 400;
    private Integer costoPerOspite = 1000;
    private Integer costoSpedizione = 450;
    private Integer limiteNucleoFamigliarePrincipale = 2000;


    public CostiDTOdopoSME calcoloCostiNuovoSponsor(ModuloDTODopoSME moduloDTO) {
        //controllo che lo sponsor sia effettivamente nuovo
        ModuloDTODopoSME.Sponsor sponsor = moduloDTO.getSponsor();
        CostiDTOdopoSME.CostoPerSoggettoDTO costoPerSoggettoDTO = new CostiDTOdopoSME.CostoPerSoggettoDTO(sponsor, costoPerSponsor);
        List<CostiDTOdopoSME.CostoPerSoggettoDTO > costiPerNucleoPrincipaleConSponsor = new ArrayList<>();
        costiPerNucleoPrincipaleConSponsor.add(costoPerSoggettoDTO);
        List<CostiDTOdopoSME.CostoPerSoggettoDTO> costiPerNucleoPrincipale = moduloDTO.getNucleoPrincipale().getComponenti().stream().map(soggetto -> new CostiDTOdopoSME.CostoPerSoggettoDTO(soggetto, costoPerFamigliare)).collect(Collectors.toList());
        costiPerNucleoPrincipaleConSponsor.addAll(costiPerNucleoPrincipale);

        List<CostiDTOdopoSME.CostoPerNucleoEsternoDTO> costiPerNucleoEsterno= moduloDTO.getNucleiEsterni().stream().map(nucleoEsterno ->
                new CostiDTOdopoSME.CostoPerNucleoEsternoDTO(nucleoEsterno.getComponenti().stream().map(soggetto -> new CostiDTOdopoSME.CostoPerSoggettoDTO(soggetto, costoPerOspite)).collect(Collectors.toList()))
        ).collect(Collectors.toList());

        return new CostiDTOdopoSME(costiPerNucleoPrincipaleConSponsor, costiPerNucleoEsterno, moduloDTO.getResidenzaDiSpedizione()!=null?costoSpedizione:0,limiteNucleoFamigliarePrincipale);

    }



}
