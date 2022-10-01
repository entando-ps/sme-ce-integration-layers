package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.*;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.*;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.QueryExecutorService;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.SmeCEBOJdbcProtocolConfigurationParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.entando.sme.cartaesercito.smeceintegrationlayers.Utils.readCsv;

@Service
public class SmeCeBoModuloService {

    final
    private SmeCEBOJdbcProtocolConfigurationParameters configParameters;

    final
    private TabsoggettoJPARepository tabsoggettoJPARepository;
    final
    private TabresidenzeJPARepository tabresidenzeJPARepository;
    final
    private TabistanzaJPARepository tabistanzaJPARepository;
    final
    private TabsoggettiistanzeJPARepository tabsoggettiistanzeJPARepository;
    final
    private TabnucleifullJPARepository tabnucleifullJPARepository;
    final
    private TabmandatoJPARepository tabmandatoJPARepository;
    final
    private TabmandatopvcJPARepository tabmandatopvcJPARepository;

    final
    private SmeCeBoCostiService smeCeBoCostiService;

    final
    private QueryExecutorService queryExecutorService;


    public SmeCeBoModuloService(SmeCEBOJdbcProtocolConfigurationParameters configParameters, TabsoggettoJPARepository tabsoggettoJPARepository, TabresidenzeJPARepository tabresidenzeJPARepository, TabistanzaJPARepository tabistanzaJPARepository, TabsoggettiistanzeJPARepository tabsoggettiistanzeJPARepository, TabnucleifullJPARepository tabnucleifullJPARepository, TabmandatoJPARepository tabmandatoJPARepository, TabmandatopvcJPARepository tabmandatopvcJPARepository, SmeCeBoCostiService smeCeBoCostiService, QueryExecutorService queryExecutorService) {
        this.configParameters = configParameters;
        this.tabsoggettoJPARepository = tabsoggettoJPARepository;
        this.tabresidenzeJPARepository = tabresidenzeJPARepository;
        this.tabistanzaJPARepository = tabistanzaJPARepository;
        this.tabsoggettiistanzeJPARepository = tabsoggettiistanzeJPARepository;
        this.tabnucleifullJPARepository = tabnucleifullJPARepository;
        this.tabmandatoJPARepository = tabmandatoJPARepository;
        this.tabmandatopvcJPARepository = tabmandatopvcJPARepository;
        this.smeCeBoCostiService = smeCeBoCostiService;
        this.queryExecutorService = queryExecutorService;
    }

    /*
                    inserisce i soggetti e le residenze
                    se il soggetto esiste lo sovrascrive con quello passato
                 */
    protected List<Tabsoggetto> insertSoggettiAndResidenze(List<ModuloDTO.Soggetto> soggetti) {
        //inserimento di tutti i soggetti
        String pin = ""; //todo
        List<Tabsoggetto> tabSoggettiDaSalvare = soggetti.stream().map(soggetto -> {
            Tabsoggetto currentSavedTabSoggetto = tabsoggettoJPARepository.findByCodiceFiscale(soggetto.getCodiceFiscale());
            Tabsoggetto tabSoggettoDaSalvare = new Tabsoggetto(pin, configParameters.getSoggettoRifStato(), soggetto);
            if (currentSavedTabSoggetto != null) {
                tabSoggettoDaSalvare.setIdSoggetto(currentSavedTabSoggetto.getIdSoggetto());
            }
            return tabSoggettoDaSalvare;
        }).collect(Collectors.toList());
        //i soggetti vengono inseriti se non esistono aggiornati in caso contrario
        List<Tabsoggetto> tabSoggettiSalvati = tabsoggettoJPARepository.saveAll(tabSoggettiDaSalvare);

        List<Tabresidenze> tabResidenzeDaSalvare = IntStream.range(0, soggetti.size()).mapToObj(index -> {
            ModuloDTO.Soggetto soggetto = soggetti.get(index);
            Tabresidenze tabresidenze = new Tabresidenze(soggetto.getResidenza());
            tabresidenze.setRifSoggetto(tabSoggettiSalvati.get(index).getIdSoggetto());
            return tabresidenze;
        }).collect(Collectors.toList());
        tabresidenzeJPARepository.saveAll(tabResidenzeDaSalvare);
        return tabSoggettiSalvati;

    }


    protected void insertResidenzeSoggetti(List<Tabsoggetto> soggetti, String csvResidenzeSoggetti) {
        List<String[]> residenzeSoggetti = readCsv(csvResidenzeSoggetti);
        List<Tabresidenze> tabResidenzeSoggetti = IntStream.range(0, residenzeSoggetti.size()).mapToObj(index -> {
            Tabresidenze tabresidenze = new Tabresidenze(residenzeSoggetti.get(index));
            tabresidenze.setRifSoggetto(soggetti.get(index).getIdSoggetto());
            return tabresidenze;
        }).collect(Collectors.toList());

        tabresidenzeJPARepository.saveAll(tabResidenzeSoggetti);

    }

    protected Tabistanza insertIstanza(Integer rifTipoIstanza, Tabsoggetto sponsor, List<Tabsoggetto> parentinucleo) {
        Tabistanza tabistanza = new Tabistanza(new java.sql.Date(Calendar.getInstance().getTime().getTime()), configParameters.getIstanzaRifCanale(), configParameters.getIstanzaRifOperatore(), configParameters.getIstanzaRifStatoIstanza(), rifTipoIstanza);
        tabistanza.setIdSponsor(sponsor.getIdSoggetto());
        tabistanzaJPARepository.save(tabistanza);
        List<Tabsoggettiistanze> tabsoggettiistanzeList = parentinucleo.stream().map(tabsoggettoNucleoFamiliare -> new Tabsoggettiistanze(new TabsoggettiistanzePK(tabistanza.getIdIstanza(), tabsoggettoNucleoFamiliare.getIdSoggetto()))).collect(Collectors.toList());
        tabsoggettiistanzeJPARepository.saveAll(tabsoggettiistanzeList);
        return tabistanza;

    }

    protected Tabnucleifull insertNucleiFullPerISoggetti(boolean capofamigliaESponsor, List<Tabsoggetto> soggetti, Tabistanza tabistanza) {
        Tabnucleifull tabnucleifullCapofamigliaoSponsor = new Tabnucleifull(true, capofamigliaESponsor, tabistanza.getIdIstanza(), -1, soggetti.get(0).getIdSoggetto());
        tabnucleifullJPARepository.save(tabnucleifullCapofamigliaoSponsor);
        tabnucleifullCapofamigliaoSponsor.setRifNucleo(tabnucleifullCapofamigliaoSponsor.getId());
        tabnucleifullJPARepository.save(tabnucleifullCapofamigliaoSponsor);
        List<Tabnucleifull> tabNucleiFullRestantiSoggetti = soggetti.subList(1, soggetti.size()).stream().map(tabsoggettoNucleoFamiliare -> new Tabnucleifull(false, false, tabistanza.getIdIstanza(), tabnucleifullCapofamigliaoSponsor.getId(), tabsoggettoNucleoFamiliare.getIdSoggetto())).collect(Collectors.toList());
        tabnucleifullJPARepository.saveAll(tabNucleiFullRestantiSoggetti);
        return tabnucleifullCapofamigliaoSponsor;
    }

    protected void insertMandati(Tabsoggetto sponsor, Tabnucleifull tabnucleifullCapofamiglia, CostiDTO costiDTO) {
        Tabmandato tabmandato = new Tabmandato();
        tabmandato.setRifSponsor(sponsor.getIdSoggetto());
        tabmandato.setRifNucleofull(tabnucleifullCapofamiglia.getId());

        Tabmandatopvc tabmandatopvc = new Tabmandatopvc();
        tabmandatopvc.setRifSponsor(sponsor.getIdSoggetto());
        tabmandatopvc.setRifNucleoFull(tabnucleifullCapofamiglia.getId());
        //il salvataggio avviene semmpre su entrambi cambiano gli importi a seconda del fatto che esista la spedizione tramite posta
        tabmandatoJPARepository.save(tabmandato);
        tabmandatopvcJPARepository.save(tabmandatopvc);

    }


    @Transactional
    /**
     * che differenza abbiamo tra il rinnovo e la modifica nucleo famigliare?
     */
    public void moduloNucleiSuCEBODataBase(
            ModuloDTO moduloDTO, CostiDTO oldCostiDTO
    ) {

        //capisco quale istanza sto creando per l'inserimento del nucleo famigliare principale
        /*
            1,Nucleo familiare principale
            2,Rinnovo Carta Esercito
            3,Richiesta integrativa per il proprio nucleo Familiare
            4,Nucleo familiare esterno
            5,Richiesta integrativa Nucleo Familiare Esterno
        */

        //per il nucleo famigliare principale:
        //Non esiste lo sponsor --> 1
        //Esiste lo sponsor e:
        // c'è già un nucleo famigliare principale: --> 3
        // Non esiste alcun nucleo principale --> 1

        //per il nucleo famigliare esterno:
        //Non esiste lo sponsor --> 3
        //Esiste lo sponsor e:
        // c'è già un nucleo famigliare esterno: --> 4
        // Non esiste alcun nucleo esterno --> 3


        //controllo che i costi non siano cambiati;
        CostiDTO costiDTO = checkCostiNonSonoCambiati(moduloDTO, oldCostiDTO);

        int tipoIstanzaDaCrearePerNucleoFamiliarePrincipale;
        String codiceFiscaleSponsor = moduloDTO.getSponsor().getCodiceFiscale();
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

        boolean creaNucleiEsterni = false;
        int tipoIstanzaDaCrearePerNucleoFamiliareEsterno = -1;
        if (moduloDTO.getNucleiEsterni().size() > 0 && moduloDTO.getNucleiEsterni().get(0).size() > 0) {
            creaNucleiEsterni = true;
            if (queryExecutorService.nucleoFamiliareEsternoSponsor(codiceFiscaleSponsor).size() > 0) { //esiste un nucleo esterno associato TODO WARNING gestiamo solo uno!
                tipoIstanzaDaCrearePerNucleoFamiliareEsterno = 4;
            } else {
                tipoIstanzaDaCrearePerNucleoFamiliareEsterno = 3;
            }
        }

        List<Tabsoggetto> listaSoggettiDelNucleoPrincipale = insertSoggettiAndResidenze(moduloDTO.getNucleoPrincipaleConSponsor());
        Tabsoggetto sponsor = listaSoggettiDelNucleoPrincipale.get(0);


        //creazione dell'istanza di creazione delle carte esercito per un nuovo nucleo familiare
        // e aggancio dei soggetti all'istanza
        Tabistanza tabistanza = insertIstanza(
                tipoIstanzaDaCrearePerNucleoFamiliarePrincipale,
                sponsor,
                listaSoggettiDelNucleoPrincipale
        );

        //creazione del nucleo familiare principale: il capofamiglia è sponsor
        Tabnucleifull tabnucleifullSponsorECapofamiglia = insertNucleiFullPerISoggetti(
                true,
                listaSoggettiDelNucleoPrincipale,
                tabistanza
        );

        /*
        //inserimento dei mandati di pagamento
        insertMandati(
                sponsor,
                tabnucleifullSponsorECapofamiglia,
                csvMandatoPagamentoPrincipale,
                csvMandatoPagamentoPVCPrincipale
        );


        String csvSoggettiNucleoEsterno = csvPath + CSVFileNames.SOGGETTINUCLEOESTERNO;
        String csvResidenzeNucleoEsterno = csvPath + CSVFileNames.RESIDENZENUCLEOESTERNO;
        String csvMandatoPagamentoEsterno = csvPath + CSVFileNames.MANDATOPAGAMENTONUCLEOESTERNO;
        String csvMandatoPagamentoPVCEsterno = csvPath + CSVFileNames.MANDATOPAGAMENTOPVCNUCLEOESTERNO;

        //creazione degli altri soggetti
        List<Tabsoggetto> listaSoggettiDelNucleoEsterno = insertSoggetti(csvSoggettiNucleoEsterno);
        //aggancio delle residenze altri
        insertResidenzeSoggetti(
                listaSoggettiDelNucleoEsterno,
                csvResidenzeNucleoEsterno
        );
        //creazione dell'istanza di creazione delle carte esercito per un nuovo nucleo familiare esterno
        // e aggancio dei soggetti all'istanza
        Tabistanza tabistanzaNucleoEsterno = insertIstanza(
                configParameters.getInstanzaNucleoEsterno(),
                sponsor,
                listaSoggettiDelNucleoEsterno
        );
        //creazione del nucleo familiare esterno: il capofamiglia (il primo soggetto nel csv del nucleo esterno) non è sponsor
        Tabnucleifull tabnucleifullCapofamiglia = insertNucleiFullPerISoggetti(
                false,
                listaSoggettiDelNucleoEsterno,
                tabistanzaNucleoEsterno
        );

        //inserimento dei mandati di pagamento per il nucleo esterno
        insertMandati(
                sponsor,
                tabnucleifullCapofamiglia,
                csvMandatoPagamentoEsterno,
                csvMandatoPagamentoPVCEsterno
        );
*/

    }


    protected CostiDTO checkCostiNonSonoCambiati(ModuloDTO moduloDTO, CostiDTO oldCostiDTO) {
        CostiDTO currCostiDTO = smeCeBoCostiService.calcoloCosti(moduloDTO);

        if (!currCostiDTO.equals(oldCostiDTO)) {
            throw new RuntimeException("costi non uguali!");
        }

        return currCostiDTO;

    }
}
