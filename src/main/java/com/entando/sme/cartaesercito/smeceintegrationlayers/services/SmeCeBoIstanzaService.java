package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.*;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.*;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.SmeCEBOJdbcProtocolConfigurationParameters;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.QueryExecutorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SmeCeBoIstanzaService {

    final
    private TabsoggettoJPARepository tabsoggettoJPARepository;
    final
    private QueryExecutorService queryExecutorService;

    public SmeCeBoIstanzaService(TabsoggettoJPARepository tabsoggettoJPARepository, QueryExecutorService queryExecutorService) {
        this.tabsoggettoJPARepository = tabsoggettoJPARepository;
        this.queryExecutorService = queryExecutorService;
    }


//capisco quale istanza sto creando per l'inserimento del nucleo famigliare principale
        /*
            1,Nucleo familiare principale
            2,Rinnovo Carta Esercito
            3,Richiesta integrativa per il proprio nucleo Familiare
            4,Nucleo familiare esterno
            5,Richiesta integrativa Nucleo Familiare Esterno
        */


    /**
     * ritorna il tipo d'istanza nucleo principale da inserire
     * dato il modulo
     * per il nucleo famigliare principale:
     * Non esiste lo sponsor --> 1
     * Esiste lo sponsor e:
     * c'è già un nucleo famigliare principale: --> 3
     * Non esiste alcun nucleo principale --> 1
     *
     * @return
     */
    public Integer calcolaTipoIstanzaNucleoPrincipale(String codiceFiscaleSponsor) {
        int tipoIstanzaDaCrearePerNucleoFamiliarePrincipale;
        Tabsoggetto tabsoggettoSponsor = tabsoggettoJPARepository.findByCodiceFiscale(codiceFiscaleSponsor);
        if (tabsoggettoSponsor == null) { //sponsor non c'è
            tipoIstanzaDaCrearePerNucleoFamiliarePrincipale = 1;
        } else { //sponsor c'è
            if (queryExecutorService.nucleoFamiliarePrincipaleSponsor(codiceFiscaleSponsor).size() > 0) { //esiste un nucleo principale
                tipoIstanzaDaCrearePerNucleoFamiliarePrincipale = 3;
            } else {
                tipoIstanzaDaCrearePerNucleoFamiliarePrincipale = 1;
            }
        }

        return tipoIstanzaDaCrearePerNucleoFamiliarePrincipale;
    }

    /**
     * per il nucleo famigliare esterno:
     * Non esiste lo sponsor --> 3 (non è possibile va creato prima il nucleo principale)
     * Esiste lo sponsor e:
     * c'è già un nucleo famigliare esterno: --> 4
     * Non esiste alcun nucleo esterno --> 3
     */
    public Integer calcolaTipoIstanzaNucleiEsterni(String codiceFiscaleSponsor) {
        int tipoIstanzaDaCrearePerNucleoFamiliareEsterno;
        if (queryExecutorService.nucleoFamiliareEsternoSponsor(codiceFiscaleSponsor).size() > 0) { //esiste un nucleo esterno associato TODO WARNING gestiamo solo uno!
            tipoIstanzaDaCrearePerNucleoFamiliareEsterno = 4;
        } else {
            tipoIstanzaDaCrearePerNucleoFamiliareEsterno = 3;
        }
        return tipoIstanzaDaCrearePerNucleoFamiliareEsterno;
    }

}
