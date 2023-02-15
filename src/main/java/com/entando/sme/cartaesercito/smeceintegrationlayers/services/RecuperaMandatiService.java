package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.MandatiDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.MandatiPVCDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.CartaEsercitoPerSoggettoPerNucleoDTOView;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.QueryExecutorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecuperaMandatiService {

    private final QueryExecutorService queryExecutorService;

    public RecuperaMandatiService (QueryExecutorService queryExecutorService) {
        this.queryExecutorService = queryExecutorService;
    }

    List<MandatiDTO> recuperaMandati(String codiceFiscale){
        List<MandatiDTO> mandatiPerSponsor = queryExecutorService.getMandatiPerSponsor(codiceFiscale);
        return mandatiPerSponsor;
    }

    List<MandatiPVCDTO> recuperaMandatiPVC(String codiceFiscale){
        List<MandatiPVCDTO> mandatiPvcPerSponsor = queryExecutorService.getMandatiPVCPerSponsor(codiceFiscale);
        return mandatiPvcPerSponsor;
    }

}
