package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.config.ConfigurationParameters;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.*;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.*;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.QueryExecutorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ModuloService {

    final private ConfigurationParameters configParameters;

    final private TabsoggettoJPARepository tabsoggettoJPARepository;
    final private TabresidenzeJPARepository tabresidenzeJPARepository;
    final private TabistanzaJPARepository tabistanzaJPARepository;
    final private TabsoggettiistanzeJPARepository tabsoggettiistanzeJPARepository;
    final private TabnucleifullJPARepository tabnucleifullJPARepository;
    final private TabmandatoJPARepository tabmandatoJPARepository;
    final private TabmandatopvcJPARepository tabmandatopvcJPARepository;

    final private CostiService smeCeBoCostiService;

    final private QueryExecutorService queryExecutor;

    public ModuloService(ConfigurationParameters configParameters, TabsoggettoJPARepository tabsoggettoJPARepository, TabresidenzeJPARepository tabresidenzeJPARepository, TabistanzaJPARepository tabistanzaJPARepository, TabsoggettiistanzeJPARepository tabsoggettiistanzeJPARepository, TabnucleifullJPARepository tabnucleifullJPARepository, TabmandatoJPARepository tabmandatoJPARepository, TabmandatopvcJPARepository tabmandatopvcJPARepository, CostiService smeCeBoCostiService, QueryExecutorService queryExecutor) {
        this.configParameters = configParameters;
        this.tabsoggettoJPARepository = tabsoggettoJPARepository;
        this.tabresidenzeJPARepository = tabresidenzeJPARepository;
        this.tabistanzaJPARepository = tabistanzaJPARepository;
        this.tabsoggettiistanzeJPARepository = tabsoggettiistanzeJPARepository;
        this.tabnucleifullJPARepository = tabnucleifullJPARepository;
        this.tabmandatoJPARepository = tabmandatoJPARepository;
        this.tabmandatopvcJPARepository = tabmandatopvcJPARepository;
        this.smeCeBoCostiService = smeCeBoCostiService;
        this.queryExecutor = queryExecutor;
    }

    protected List<Tabsoggetto> insertSoggettiAndResidenze(List<ModuloDTO.Soggetto> soggetti) {
        //inserimento di tutti i soggetti
        String pin = ""; //todo pin non necessario: serviva prima per permettere accesso a BO da parte di soggetto richiedente
        // si potrebbe impementare in intranet con il loro generatore di pin (se il pin non esiste si genera, sennò no)
        List<Tabsoggetto> tabSoggettiDaSalvare = soggetti.stream().map(soggetto -> {
            Tabsoggetto tabSoggettoDaSalvare = tabsoggettoJPARepository.findByCodiceFiscale(soggetto.getCodiceFiscale()); //recupero dal db
            if (tabSoggettoDaSalvare != null) {
                //cosa avviene nel caso di salvataggio dei dati di un soggetto già presente? Per ora ricreo pin e metto non validato
                // TODO se soggetto presente pin già resente da non sovrascrivere, lo stato va messo a "in attesa di pagamento" (stato 5)
                //          in funzione della creazione mandato
                tabSoggettoDaSalvare.copyFrom(configParameters.getSoggetto().getRifStato(), soggetto);
            } else {
                // non necessaria creazione del pin (esiste funzione su BO pin alfanum 7 cifre) ma non usata
                tabSoggettoDaSalvare = new Tabsoggetto(configParameters.getSoggetto().getRifStato(), soggetto);
            }
            return tabSoggettoDaSalvare;
        }).collect(Collectors.toList());
        //i soggetti vengono inseriti se non esistono aggiornati in caso contrario (UPSERT)
        List<Tabsoggetto> tabSoggettiSalvati = tabsoggettoJPARepository.saveAll(tabSoggettiDaSalvare);
        insertResidenzeSoggetti(soggetti, tabSoggettiSalvati);

        return tabSoggettiSalvati;

    }


    protected void insertResidenzeSoggetti(List<ModuloDTO.Soggetto> soggetti, List<Tabsoggetto> tabSoggettiSalvati) {
        List<Tabresidenze> tabResidenzeDaSalvare = IntStream.range(0, soggetti.size()).filter(index -> soggetti.get(index).getResidenza() != null).mapToObj(index -> {
            ModuloDTO.Soggetto soggetto = soggetti.get(index);
            Tabresidenze tabresidenze = new Tabresidenze(soggetto.getResidenza());
            tabresidenze.setRifSoggetto(tabSoggettiSalvati.get(index).getIdSoggetto());
            return tabresidenze;
        }).collect(Collectors.toList());
        tabresidenzeJPARepository.saveAll(tabResidenzeDaSalvare);

    }

    protected Tabistanza insertIstanza(Integer rifTipoIstanza, Tabsoggetto sponsor, List<Tabsoggetto> parentinucleo) {
        Tabistanza tabistanza = new Tabistanza(new java.sql.Date(Calendar.getInstance().getTime().getTime()), configParameters.getIstanza().getRifCanale(), configParameters.getIstanza().getRifOperatore(), configParameters.getIstanza().getRifStatoIstanza(), rifTipoIstanza);
        tabistanza.setIdSponsor(sponsor.getIdSoggetto());
        tabistanzaJPARepository.save(tabistanza);
        List<Tabsoggettiistanze> tabsoggettiistanzeList = parentinucleo.stream().map(tabsoggettoNucleoFamiliare -> new Tabsoggettiistanze(new TabsoggettiistanzePK(tabistanza.getIdIstanza(), tabsoggettoNucleoFamiliare.getIdSoggetto()))).collect(Collectors.toList());
        tabsoggettiistanzeJPARepository.saveAll(tabsoggettiistanzeList);
        return tabistanza;

    }

    protected Tabnucleifull insertNucleiFullPerISoggetti(boolean capofamigliaESponsor, List<Tabsoggetto> soggetti, Tabistanza tabistanza) {
        //genero nuovo rifNucleo
        List<Tabnucleifull> tabNucleiFull = new ArrayList<>();
        Integer rifNucleo = queryExecutor.getNextRifNucleo();
        Tabnucleifull tabnucleifullCapofamigliaoSponsor = new Tabnucleifull(true, capofamigliaESponsor, tabistanza.getIdIstanza(), rifNucleo, soggetti.get(0).getIdSoggetto());
        tabNucleiFull.add(tabnucleifullCapofamigliaoSponsor);
        List<Tabnucleifull> tabNucleiFullRestantiSoggetti = soggetti.subList(1, soggetti.size()).stream().map(tabsoggettoNucleoFamiliare -> new Tabnucleifull(false, false, tabistanza.getIdIstanza(), rifNucleo, tabsoggettoNucleoFamiliare.getIdSoggetto())).collect(Collectors.toList());
        tabNucleiFull.addAll(tabNucleiFullRestantiSoggetti);
        tabnucleifullJPARepository.saveAllAndFlush(tabNucleiFull);
        return tabnucleifullCapofamigliaoSponsor;
    }

    protected void insertMandati(Tabsoggetto sponsor, Tabnucleifull tabnucleifullCapofamiglia, String cro, int importoDaPagare, int importoPagato, int importoDaPagareSpedizione, int importoPagatoSpedizione) {
        Tabmandato tabmandato = new Tabmandato();
        tabmandato.setRifSponsor(sponsor.getIdSoggetto());
        tabmandato.setRifNucleofull(tabnucleifullCapofamiglia.getId());
        tabmandato.setQuotaVersata(Math.round((float) importoPagato / 100));
        tabmandato.setAttestazionePagamento(cro);
        tabmandato.setQuotaMandato(Math.round((float) importoDaPagare / 100));

        Tabmandatopvc tabmandatopvc = new Tabmandatopvc();
        tabmandatopvc.setRifSponsor(sponsor.getIdSoggetto());
        tabmandatopvc.setRifNucleoFull(tabnucleifullCapofamiglia.getId());
        tabmandatopvc.setQuotaVersata(String.valueOf((double) importoPagatoSpedizione / 100));
        tabmandatopvc.setAttestazionePagamento(cro);
        tabmandatopvc.setQuotaMandato(String.valueOf((double) importoDaPagareSpedizione / 100));
        /**
         * il salvataggio avviene sempre su entrambi cambiano gli importi a seconda del fatto che esista la spedizione tramite posta
         */
        tabmandatoJPARepository.save(tabmandato);
        tabmandatopvcJPARepository.save(tabmandatopvc);

    }


    @Transactional
    /**
     *  metodo che si occupa dell'inserimento del "modulo" contenente tutti i dati di tutti i nuclei (esterni e principale)
     *  esclusi documenti ed immagini
     *
     *  scompatta il "modulo" e salva le informazioni in tutte le tabelle SME mantenendo la logica preesistente on premise
     *
     *  @param ModuloDTO oggetto contenente tutti i dati di sponsor, nucleo principale e nuclei esterni (il cro "ModuloDTO.Pagamento" non serve in scrittura)
     *  @see ModuloDTO per visualizzare dettaglio del DTO
     */
    public void inserimentoNuovoModulo(ModuloDTO moduloDTO) {

//        che differenza abbiamo tra il rinnovo e la modifica nucleo famigliare?
//                rinnovo crea un nuovo mandato senza mandato pvc e verranno inseriti data pagamento e cro insieme all'aggiornamento dello stato soggetto (e altri domini)
//        da "in attesa di pagamento" a "in regola"
//        modifica al nucleo avviene una modifica nei dati del nucleo familiare, update su tutti i campi dei domini colpiti

        //controllo che i costi non siano cambiati;
        //CostiDTO costiDTO = smeCeBoCostiService.checkCostiNonSonoCambiati(moduloDTO, oldCostiDTO);

        CostiDTO costiDTO = smeCeBoCostiService.calcoloCostiNuovoSponsor(moduloDTO);


        //gestione del nucleo principale
        //TODO dalla lista del nucleo principale di un modulo possono prodursi + istanze
        //nuovo nucleo principale
        //modifica al nucleo principale
        //rinnovo
        //quindi modifica nucleo + rinnovo è una possibilità
        // TODO WARN qui viene calcolato solo costo spedizione per nucleo principale
        Tabsoggetto tabSoggettoSponsor = gestisciNucleoPrincipale(moduloDTO.getNucleoPrincipaleConSponsor(), configParameters.getIstanza().getNucleoPrincipale(), moduloDTO.getPagamento().getCro(), costiDTO.limiteNucleoPrincipaleSenzaSponsorSuperato() ? configParameters.getCosti().getLimiteNucleoFamigliarePrincipaleNoSponsor() + configParameters.getCosti().getPerSponsor() : costiDTO.calcolaTotaleNucleoPrincipaleConSponsor(), costiDTO.limiteNucleoPrincipaleSenzaSponsorSuperato() ? configParameters.getCosti().getLimiteNucleoFamigliarePrincipaleNoSponsor() + configParameters.getCosti().getPerSponsor() : costiDTO.calcolaTotaleNucleoPrincipaleConSponsor(), costiDTO.getCostoSpedizioneNucleoPricipale(), costiDTO.getCostoSpedizioneNucleoPricipale());

        //TODO WARN spedizione già pagata perchè per ora il nucleo principale viene creato, ma doppia (comportamento atteso, controllare costi)

        //TODO dalla lista dei nuclei esterni di un modulo possono prodursi + istanze
        //nuovo nucleo esterno
        //modifica al nucleo esterno
        //rinnovo
        gestisciNucleiEsterni(moduloDTO.getNucleiEsterni(), tabSoggettoSponsor, configParameters.getIstanza().getNucleoEsterno(), moduloDTO.getPagamento().getCro(), costiDTO);
        //quindi modifica nucleo + rinnovo è una possibilità

    }

    protected Tabsoggetto gestisciNucleoPrincipale(List<ModuloDTO.Soggetto> nucleoPricipaleConSponsor, Integer tipoIstanzaDaCrearePerNucleoFamiliarePrincipale, String cro, Integer importoDaPagarePerNucleoPrincipaleESponsor, Integer importoPagatoPerNucleoPrincipaleESponsor, Integer importoDaPagarePerSpedizione, Integer importoPagatoPerSpedizione) {

        List<Tabsoggetto> listaSoggettiDelNucleoPrincipale = insertSoggettiAndResidenze(nucleoPricipaleConSponsor);
        Tabsoggetto tabSoggettoSponsor = listaSoggettiDelNucleoPrincipale.get(0);


        //creazione dell'istanza di creazione delle carte esercito per un nuovo nucleo familiare
        // e aggancio dei soggetti all'istanza
        Tabistanza tabistanza = insertIstanza(tipoIstanzaDaCrearePerNucleoFamiliarePrincipale, tabSoggettoSponsor, listaSoggettiDelNucleoPrincipale);

        //creazione del nucleo familiare principale: il capofamiglia è sponsor
        Tabnucleifull tabnucleifullSponsorECapofamigliaNucleoPrincipale = insertNucleiFullPerISoggetti(true, listaSoggettiDelNucleoPrincipale, tabistanza);


        //inserimento dei mandati di pagamento
        insertMandati(tabSoggettoSponsor, tabnucleifullSponsorECapofamigliaNucleoPrincipale, cro, importoDaPagarePerNucleoPrincipaleESponsor, importoPagatoPerNucleoPrincipaleESponsor, importoDaPagarePerSpedizione, importoPagatoPerSpedizione

        );

        return tabSoggettoSponsor;
    }


    protected void gestisciNucleoEsterno(ModuloDTO.Nucleo nucleoEsterno, Tabsoggetto sponsor, Integer tipoIstanzaDaCrearePerNucleoFamiliareEsterno, String cro, Integer importoDaPagarePerIlNucleoEsterno, Integer importoPagatoPerIlNucleoEsterno, Integer importoDaPagarePerLaSpedizione, Integer importoPagatoPerLaSpedizione) {
        List<Tabsoggetto> listaSoggettiDelNucleoEsterno = insertSoggettiAndResidenze(nucleoEsterno.getComponenti());
        //creazione dell'istanza di creazione delle carte esercito per un nuovo nucleo esterno
        // e aggancio dei soggetti all'istanza
        Tabistanza tabistanzaNucleoEsterno = insertIstanza(tipoIstanzaDaCrearePerNucleoFamiliareEsterno, sponsor, listaSoggettiDelNucleoEsterno);

        //creazione del nucleo familiare principale: il capofamiglia è sponsor
        Tabnucleifull tabnucleifullCapofamigliaNucleoEsterno = insertNucleiFullPerISoggetti(false, listaSoggettiDelNucleoEsterno, tabistanzaNucleoEsterno);

        //inserimento dei mandati di pagamento
        insertMandati(sponsor, tabnucleifullCapofamigliaNucleoEsterno, cro, importoDaPagarePerIlNucleoEsterno, importoPagatoPerIlNucleoEsterno, importoDaPagarePerLaSpedizione, importoPagatoPerLaSpedizione //spedizione già pagata nel mandato del nucleo principale
        );

    }

    protected void gestisciNucleiEsterni(List<ModuloDTO.Nucleo> nucleiEsterni, Tabsoggetto sponsor, Integer tipoIstanzaDaCrearePerNucleoFamiliareEsterno, String cro, CostiDTO costiDTO) {
        IntStream.range(0, nucleiEsterni.size()).forEach(index -> {
            ModuloDTO.Nucleo nucleoEsterno = nucleiEsterni.get(index);
            CostiDTO.CostoPerNucleoEsternoDTO costoPerNucleoEsterno = costiDTO.getNucleiEsterni().get(index);
            costoPerNucleoEsterno.setCostoSpedizioneNucleoEsterno(costiDTO.getNucleiEsterni().get(index).getCostoSpedizioneNucleoEsterno());
            gestisciNucleoEsterno(nucleoEsterno, sponsor, tipoIstanzaDaCrearePerNucleoFamiliareEsterno, cro, costoPerNucleoEsterno.calcolaCosto(), costoPerNucleoEsterno.calcolaCosto(), costoPerNucleoEsterno.getCostoSpedizioneNucleoEsterno(), costoPerNucleoEsterno.getCostoSpedizioneNucleoEsterno());
        });
    }


}
