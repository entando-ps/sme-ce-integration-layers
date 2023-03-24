package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTO;
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

    /**
     * metodo che si occupa di recuperare tutte le informazioni di tutti i nuclei collegati allo sponsor
     *
     * scompatta il "modulo" e legge le informazioni in cerca degli indirizzi di spedizione (per nucleo) e calcola costo abbonamento secondo direttive
     * ritorna anche le informazioni dei mandati "in attesa di pagamento" per nucleo
     * @param codiceFiscale del soggetto sponsor cercato
     * @return ModuloDTO con tutte le informazioni dello sponsor e tutti i soggetti di tutti i inuclei a esso collegati
     * @see ModuloDTO
     */
    ModuloDTO recuperaModulo(String codiceFiscale){
        Map<CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> carteEsercitoPerSponsor = queryExecutorService.getCarteEsercitoPerSponsor(codiceFiscale);
        return CartaEsercitoPerSoggettoPerNucleoDTOView.converti(carteEsercitoPerSponsor);
    }
}
