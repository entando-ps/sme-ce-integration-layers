package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.CartaEsercitoPerSoggettoPerNucleoDTOView;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.QueryExecutorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecuperaModuloService {


    private final QueryExecutorService queryExecutorService;

    public RecuperaModuloService(QueryExecutorService queryExecutorService) {
        this.queryExecutorService = queryExecutorService;
    }

    ModuloDTO recuperaModulo(String codiceFiscale){
        Map<CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> carteEsercitoPerSponsor = queryExecutorService.getCarteEsercitoPerSponsor(codiceFiscale);
        return CartaEsercitoPerSoggettoPerNucleoDTOView.converti(carteEsercitoPerSponsor);
    }
}
