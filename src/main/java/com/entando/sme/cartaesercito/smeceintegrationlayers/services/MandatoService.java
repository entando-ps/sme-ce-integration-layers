package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.config.ConfigurationParameters;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabmandato;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabmandatopvc;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.TabmandatoJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.TabmandatopvcJPARepository;
import org.springframework.stereotype.Service;

@Service

public class MandatoService {

    final private TabmandatoJPARepository tabmandatoJPARepository;
    final private TabmandatopvcJPARepository tabmandatopvcJPARepository;

    public MandatoService(TabmandatoJPARepository tabmandatoJPARepository, TabmandatopvcJPARepository tabmandatopvcJPARepository, ConfigurationParameters configParam) {
        this.tabmandatoJPARepository = tabmandatoJPARepository;
        this.tabmandatopvcJPARepository = tabmandatopvcJPARepository;
    }

    /**metodo che passato l'id del mandato ed il cro aggiorna tabMandato e tabMandato pvc
     *
     *  !!! da rieseguire per ogni nucleo !!!
     *
     * @param codiceMandato id del mandato da aggiornare
     * @param codiceMandatoPVC id del mandato pvc da aggiornare
     * @param croMandato codice ricevuto da UI che attesta il pagamento mandato
     * @param croMandatoPVC codice ricevuto da UI che attesta il pagamento mandato pvc
     */
    public void aggiornaMandatiConCRO(Integer codiceMandato, Integer codiceMandatoPVC, String croMandato, String croMandatoPVC){
        // update su tabMandato con controllo se cro esiste
        aggiornaMandatoConCRO(codiceMandato, croMandato);

        // update su tabMandatoPVC con controllo se cro esiste
        aggiornaMandatoPVCConCRO(codiceMandatoPVC, croMandatoPVC);
    }

    /**
     * metodo che passato l'id del mandato ed il cro aggiorna tabMandato
     *
     * !!! da rieseguire per ogni mandato !!!
     *
     * @param codiceMandato id del mandato da aggiornare
     * @param cro           codice ricevuto da UI che attesta il pagamento mandato
     */
    public void aggiornaMandatoConCRO(Integer codiceMandato, String cro) {
        // cerca il mandato se non lo trova lancia NoSuchElementException
        Tabmandato mandato = tabmandatoJPARepository.findById(codiceMandato).get();

        // verifico se è già stato caricato un cro associato a quel mandato
        if (mandato.getAttestazionePagamento() != null) {
            throw new UnsupportedOperationException("per il mandato con codice " + codiceMandato + " è già presente un'attestazione di pagamento");
        }
        mandato.setAttestazionePagamento(cro);
        tabmandatoJPARepository.save(mandato);
    }

    /**
     * metodo che passato l'id del mandato ed il cro aggiorna tabMandatoPvc
     *
     * !!! da rieseguire per ogni mandato pvc !!!
     *
     * @param codiceMandato id del mandato pvc da aggiornare
     * @param cro           codice ricevuto da UI che attesta il pagamento mandato pvc
     */
    public void aggiornaMandatoPVCConCRO(Integer codiceMandato, String cro) {
        // cerca il mandato se non lo trova lancia NoSuchElementException
        Tabmandatopvc mandatoPVC = tabmandatopvcJPARepository.findById(codiceMandato).get();

        // verifico se è già stato caricato un cro associato a quel mandato
        if (mandatoPVC.getAttestazionePagamento() != null) {
            throw new UnsupportedOperationException("per il mandato PVC con codice " + codiceMandato + " è già presente un'attestazione di pagamento");
        }

        mandatoPVC.setAttestazionePagamento(cro);
        tabmandatopvcJPARepository.save(mandatoPVC);
    }
}
