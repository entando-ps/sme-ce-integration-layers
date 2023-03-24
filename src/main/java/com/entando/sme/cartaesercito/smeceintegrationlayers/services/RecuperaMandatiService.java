package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.MandatoDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.MandatoPVCDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.QueryExecutorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecuperaMandatiService {

    private final QueryExecutorService queryExecutorService;

    public RecuperaMandatiService(QueryExecutorService queryExecutorService) {
        this.queryExecutorService = queryExecutorService;
    }

    /**
     * metodo che passato il codice fiscale dello sponsor estrae tutti i mandati in stato "attesa di pagamento"
     * collegati a tutti i nuclei collegati allo sponsor dato
     *
     * @param codiceFiscale del soggetto sponsor
     * @return List<MandatoDTO> una lista di mandati, un mandato per nucleo;
     * il mandato deve soddisfare il requisito sullo stato = "attesa di pagamento"
     * @see MandatoDTO per visualizzare dettaglio del DTO
     */
    List<MandatoDTO> recuperaMandati(String codiceFiscale) {
        List<MandatoDTO> mandatiPerSponsor = queryExecutorService.getMandatiPerSponsor(codiceFiscale);
        return mandatiPerSponsor;
    }

    /**
     * metodo che passato il codice fiscale dello sponsor estrae tutti i mandatiPVC in stato "attesa di pagamento"
     * collegati a tutti i nuclei collegati allo sponsor dato
     *
     * @param codiceFiscale del soggetto sponsor
     * @return List<MandatoPVCDTO> una lista di mandatiPVC, un mandatiPVC per nucleo;
     * il mandatiPVC deve soddisfare il requisito sullo stato = "attesa di pagamento"
     * @see MandatoPVCDTO per visualizzare dettaglio del DTO
     */
    List<MandatoPVCDTO> recuperaMandatiPVC(String codiceFiscale) {
        List<MandatoPVCDTO> mandatiPvcPerSponsor = queryExecutorService.getMandatiPVCPerSponsor(codiceFiscale);
        return mandatiPvcPerSponsor;
    }

}
