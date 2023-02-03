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

    /**
     * metodo che si occupa del calcolo dei costi per soggetto (abbonamento) e per nucleo (spedizione)
     * costo di spedizione calcolato su presenza dell'indirizzo di spedizione o meno
     *
     * scompatta il "modulo" e legge le informazioni in cerca degli indirizzi di spedizione (per nucleo) e calcola costo abbonamento secondo direttive
     *
     * @param moduloDTO oggetto contenente tutti i dati di sponsor, nucleo principale e nuclei esterni (il cro "ModuloDTO.Pagamento" non serve in scrittura)
     * @see ModuloDTO per le specifiche di cominio
     *
     * @return CostiDTO ritorna l'oggetto CostiDTO con valorizzati i costi per gli abbonamenti (per soggetto) e spedizione (per nucleo)
     * @see CostiDTO per le specifiche di dominio
     */
    public CostiDTO calcoloCostiNuovoSponsor(ModuloDTO moduloDTO) {
        //controllo che lo sponsor sia effettivamente nuovo
        ModuloDTO.Sponsor sponsor = moduloDTO.getSponsor();
        CostiDTO.CostoPerSoggettoDTO costoPerSoggettoDTO = new CostiDTO.CostoPerSoggettoDTO(sponsor, configurazioneCosti.getPerSponsor());
        List<CostiDTO.CostoPerSoggettoDTO > costiPerNucleoPrincipaleConSponsor = new ArrayList<>();
        costiPerNucleoPrincipaleConSponsor.add(costoPerSoggettoDTO);
        List<CostiDTO.CostoPerSoggettoDTO> costiPerNucleoPrincipale = moduloDTO.getNucleoPrincipale().getComponenti().stream().map(soggetto -> new CostiDTO.CostoPerSoggettoDTO(soggetto, configurazioneCosti.getPerFamigliare())).collect(Collectors.toList());
        costiPerNucleoPrincipaleConSponsor.addAll(costiPerNucleoPrincipale);

        List<CostiDTO.CostoPerNucleoEsternoDTO> costiPerNucleoEsterno= moduloDTO.getNucleiEsterni().stream().map(nucleoEsterno ->
                new CostiDTO.CostoPerNucleoEsternoDTO(nucleoEsterno.getComponenti().stream().map(soggetto -> new CostiDTO.CostoPerSoggettoDTO(soggetto, configurazioneCosti.getPerOspite())).collect(Collectors.toList()), nucleoEsterno.getResidenzaDiSpedizione()!=null? configurazioneCosti.getSpedizione():0)
        ).collect(Collectors.toList());
        /**
         * attualmente calcola costo spedizione per principale e per ogni nucleo esterno
         * controllo costo di spedizione su inidirizzoResidenzaSpedizione, preso da form!!
         *
         *  se inidirizzoResidenzaSpedizione è null la spedizione non si paga perchè richiesta consegna a mano
        */
        return new CostiDTO(costiPerNucleoPrincipaleConSponsor, costiPerNucleoEsterno,
                moduloDTO.getNucleoPrincipale().getResidenzaDiSpedizione()!=null? configurazioneCosti.getSpedizione():0,configurazioneCosti.getLimiteNucleoFamigliarePrincipaleNoSponsor());

    }



}
