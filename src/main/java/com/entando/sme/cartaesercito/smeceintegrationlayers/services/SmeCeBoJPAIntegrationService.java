package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.*;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.entando.sme.cartaesercito.smeceintegrationlayers.Utils.readCsv;

@Service
public class SmeCeBoJPAIntegrationService {

    final
    TabsoggettoJPARepository tabsoggettoJPARepository;
    final
    TabresidenzeJPARepository tabresidenzeJPARepository;
    final
    TabistanzaJPARepository tabistanzaJPARepository;
    final
    TabsoggettiistanzeJPARepository tabsoggettiistanzeJPARepository;
    final
    TabnucleifullJPARepository tabnucleifullJPARepository;
    final
    TabmandatoJPARepository tabmandatoJPARepository;
    final
    TabmandatopvcJPARepository tabmandatopvcJPARepository;

    public SmeCeBoJPAIntegrationService(TabsoggettoJPARepository tabsoggettoJPARepository, TabresidenzeJPARepository tabresidenzeJPARepository, TabistanzaJPARepository tabistanzaJPARepository, TabsoggettiistanzeJPARepository tabsoggettiistanzeJPARepository, TabnucleifullJPARepository tabnucleifullJPARepository, TabmandatoJPARepository tabmandatoJPARepository, TabmandatopvcJPARepository tabmandatopvcJPARepository) {
        this.tabsoggettoJPARepository = tabsoggettoJPARepository;
        this.tabresidenzeJPARepository = tabresidenzeJPARepository;
        this.tabistanzaJPARepository = tabistanzaJPARepository;
        this.tabsoggettiistanzeJPARepository = tabsoggettiistanzeJPARepository;
        this.tabnucleifullJPARepository = tabnucleifullJPARepository;
        this.tabmandatoJPARepository = tabmandatoJPARepository;
        this.tabmandatopvcJPARepository = tabmandatopvcJPARepository;
    }


    protected List<Tabsoggetto> insertSoggetti(String csvSoggetti) {
        //creazione sponsor nucleo
        List<String[]> nucleoPrincipale = readCsv(csvSoggetti);
        List<Tabsoggetto> soggetti = nucleoPrincipale.stream().map(Tabsoggetto::new).collect(Collectors.toList());

        tabsoggettoJPARepository.saveAll(soggetti);
        return soggetti;

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

    protected Tabistanza insertIstanza(String csvIstanzaNucleoPrincipale, Tabsoggetto sponsor, List<Tabsoggetto> parentinucleoprincipale) {
        Tabistanza tabistanza = new Tabistanza(readCsv(csvIstanzaNucleoPrincipale).get(0));
        tabistanza.setIdSponsor(sponsor.getIdSoggetto());
        tabistanzaJPARepository.save(tabistanza);
        List<Tabsoggettiistanze> tabsoggettiistanzeList = parentinucleoprincipale.stream().map(tabsoggettoNucleoFamiliare -> new Tabsoggettiistanze(new TabsoggettiistanzePK(tabistanza.getIdIstanza(), tabsoggettoNucleoFamiliare.getIdSoggetto()))).collect(Collectors.toList());
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

    protected void insertMandati(Tabsoggetto sponsor, Tabnucleifull tabnucleifullCapofamiglia, String csvMandatoPagamento, String csvMandatoPagamentoPVC) {
        Tabmandato tabmandato = new Tabmandato(readCsv(csvMandatoPagamento).get(0));
        tabmandato.setRifSponsor(sponsor.getIdSoggetto());
        tabmandato.setRifNucleofull(tabnucleifullCapofamiglia.getId());

        Tabmandatopvc tabmandatopvc = new Tabmandatopvc(readCsv(csvMandatoPagamentoPVC).get(0));

        tabmandatopvc.setRifSponsor(sponsor.getIdSoggetto());
        tabmandatopvc.setRifNucleoFull(tabnucleifullCapofamiglia.getId());
        //il salvataggio avviene semmpre su entrambi cambiano gli importi a seconda del fatto che esista la spedizione tramite posta
        tabmandatoJPARepository.save(tabmandato);
        tabmandatopvcJPARepository.save(tabmandatopvc);

    }

    @Transactional
    public void IstanzaConNuoviSoggetti(
            String csvPath
    ) {

        String csvSoggettiNucleoPrincipale = csvPath + CSVFileNames.SOGGETTINUCLEOPRINCIPALE;
        String csvResidenzeNucleoPrincipale = csvPath + CSVFileNames.RESIDENZENUCLEOPRINCIPALE;
        String csvIstanzaNucleoPrincipale = csvPath + CSVFileNames.ISTANZANUCLEOPRINCIPALE;
        String csvMandatoPagamentoPrincipale = csvPath + CSVFileNames.MANDATOPAGAMENTONUCLEOPRINCIPALE;
        String csvMandatoPagamentoPVCPrincipale = csvPath + CSVFileNames.MANDATOPAGAMENTOPVCNUCLEOPRINCIPALE;

        //creazione dei soggetti
        List<Tabsoggetto> listaSoggettiDelNucleoPrincipale = insertSoggetti(csvSoggettiNucleoPrincipale);
        Tabsoggetto sponsor = listaSoggettiDelNucleoPrincipale.get(0);

        //aggancio delle residenze ai soggetti
        insertResidenzeSoggetti(
                listaSoggettiDelNucleoPrincipale,
                csvResidenzeNucleoPrincipale
        );

        //creazione dell'istanza di creazione delle carte esercito per un nuovo nucleo familiare
        // e aggancio dei soggetti all'istanza
        Tabistanza tabistanza = insertIstanza(
                csvIstanzaNucleoPrincipale,
                sponsor,
                listaSoggettiDelNucleoPrincipale
        );

        //creazione del nucleo familiare principale: il capofamiglia è sponsor
        Tabnucleifull tabnucleifullSponsorECapofamiglia = insertNucleiFullPerISoggetti(
                true,
                listaSoggettiDelNucleoPrincipale,
                tabistanza
        );

        //inserimento dei mandati di pagamento
        insertMandati(
                sponsor,
                tabnucleifullSponsorECapofamiglia,
                csvMandatoPagamentoPrincipale,
                csvMandatoPagamentoPVCPrincipale
        );


        String csvSoggettiNucleoEsterno = csvPath + CSVFileNames.SOGGETTINUCLEOESTERNO;
        String csvResidenzeNucleoEsterno = csvPath + CSVFileNames.RESIDENZENUCLEOESTERNO;
        String csvIstanzaNucleoEsterno = csvPath + CSVFileNames.ISTANZANUCLEOESTERNO;
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
                csvIstanzaNucleoEsterno,
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

    }


}
