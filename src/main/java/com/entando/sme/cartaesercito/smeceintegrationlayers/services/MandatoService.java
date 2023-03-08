package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.config.ConfigurationParameters;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabmandato;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabmandatopvc;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.TabmandatoJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.TabmandatopvcJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MandatoService {

    final private TabmandatoJPARepository tabmandatoJPARepository;
    final private TabmandatopvcJPARepository tabmandatopvcJPARepository;

    public MandatoService(TabmandatoJPARepository tabmandatoJPARepository, TabmandatopvcJPARepository tabmandatopvcJPARepository, ConfigurationParameters configParam) {
        this.tabmandatoJPARepository = tabmandatoJPARepository;
        this.tabmandatopvcJPARepository = tabmandatopvcJPARepository;
    }

    /**
     * metodo che si occupa dell'aggiornamento mandati necessari per il rinnovo carta esercito
     *  passato il modulo contenente i dati del madato con il cro valorizzato aggiorna tabMandato e tabMandato pvc
     * <p>
     * !!! unico per modulo e per tutti i nuclei !!!
     *
     * @param moduloDTO modulo con le info di tutti i nuclei e con i cro per tutti i nuclei
     */
    public void rinnovoCarteTuttiNuclei(ModuloDTO moduloDTO) {

        ModuloDTO.Nucleo nucleoPrinci = moduloDTO.getNucleoPrincipale();
        List<ModuloDTO.Nucleo> nucleiExt = moduloDTO.getNucleiEsterni();

        // controllo che il mandato ed il cro siano valorizzati per il nucleo principale
        if (nucleoPrinci.getMandatoDTO() == null || nucleoPrinci.getMandatoDTO().getCro().isBlank()) {
            throw new RuntimeException("Dati mandato mancanti per chiusura mandato nucleo principale");
        }

        // aggiorno mandato nucleo principale con cro inserito da user
        aggiornaMandatoConCRO(nucleoPrinci.getMandatoDTO().getIdMandato(), nucleoPrinci.getMandatoDTO().getCro());

        // aggiorno mandato per ogni nucleo esterno inserendo cro fornito da user
        nucleiExt.forEach(nucleoExt -> {

            // controllo che il mandato ed il cro siano valorizzati per tutti i nuclei esterni
            if (nucleoExt.getMandatoDTO() == null || nucleoExt.getMandatoDTO().getCro().isBlank()) {
                throw new RuntimeException("Dati mandato mancanti per chiusura mandati nuclei esterni");
            }
            // TODO qui va eseguito controllo se cro gia esiste
            aggiornaMandatoConCRO(nucleoExt.getMandatoDTO().getIdMandato(), nucleoExt.getMandatoDTO().getCro());
        });


    }

    /**
     * metodo che si occupa dell'aggiornamento mandati necessari per primo inserimento
     *  passato il modulo contenente i dati del madato con il cro valorizzato aggiorna tabMandato e tabMandato pvc
     * <p>
     * !!! unico per modulo e per tutti i nuclei !!!
     *
     * @param moduloDTO modulo con le info di tutti i nuclei e con i cro per tutti i nuclei
     */
    public void aggiornaCroNuovaRichiestaPerNuclei(ModuloDTO moduloDTO) {
        ModuloDTO.Nucleo nucleoPrinci = moduloDTO.getNucleoPrincipale();
        List<ModuloDTO.Nucleo> nucleiExt = moduloDTO.getNucleiEsterni();

        // controllo che il mandato ed il cro siano valorizzati per il nucleo principale
        if (nucleoPrinci.getMandatoDTO() == null || nucleoPrinci.getMandatoDTO().getCro().isBlank()) {
            throw new RuntimeException("Dati mandato mancanti per chiusura mandato nucleo principale");
        }
        // aggiorno mandato e mandato pvc nucleo principale con cro inserito da user
        aggiornaMandatoConCRO(nucleoPrinci.getMandatoDTO().getIdMandato(), nucleoPrinci.getMandatoDTO().getCro());
        aggiornaMandatoPVCConCRO(nucleoPrinci.getMandatoPVCDTO().getIdmandatopvc(), nucleoPrinci.getMandatoPVCDTO().getCro());

        // aggiorno mandato e mandato pvc per ogni nucleo esterno inserendo cro fornito da user
        nucleiExt.forEach(nucleoExt -> {

            // controllo che il mandato ed il cro siano valorizzati per tutti i nuclei esterni
            if (nucleoExt.getMandatoDTO() == null || nucleoExt.getMandatoDTO().getCro().isBlank()) {
                throw new RuntimeException("Dati mandato mancanti per chiusura mandati nuclei esterni");
            }
            aggiornaMandatoConCRO(nucleoExt.getMandatoDTO().getIdMandato(), nucleoExt.getMandatoDTO().getCro());
            aggiornaMandatoPVCConCRO(nucleoExt.getMandatoPVCDTO().getIdmandatopvc(), nucleoExt.getMandatoPVCDTO().getCro());
        });
    }


//    public void aggiornaMandatiConCRO(Integer codiceMandato, Integer codiceMandatoPVC, String croMandato, String croMandatoPVC){
//        // update su tabMandato con controllo se cro esiste
//        aggiornaMandatoConCRO(codiceMandato, croMandato);
//
//        // update su tabMandatoPVC con controllo se cro esiste
//        aggiornaMandatoPVCConCRO(codiceMandatoPVC, croMandatoPVC);
//    }

    /**
     * metodo che passato l'id del mandato ed il cro aggiorna tabMandato
     * <p>
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
        mandato.setDataAggiornamento(null);
        mandato.setAttestazionePagamento(cro);
        tabmandatoJPARepository.save(mandato);
    }

    /**
     * metodo che passato l'id del mandato ed il cro aggiorna tabMandatoPvc
     * <p>
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
        mandatoPVC.setDataAggiornamento(null);
        mandatoPVC.setAttestazionePagamento(cro);
        tabmandatopvcJPARepository.save(mandatoPVC);
    }
}
