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


    protected Tabsoggetto insertSponsor(String csvSponsor) {
        //creazione sponsor
        List<String[]> sponsorProperties = readCsv(csvSponsor);
        Tabsoggetto sponsor = new Tabsoggetto(sponsorProperties.get(0));
        //creazione sponsor nucleo
        tabsoggettoJPARepository.save(sponsor);
        return sponsor;

    }

    protected List<Tabsoggetto> insertAltriSoggetti(String csvSoggettiNucleoPrincipale) {
        //creazione sponsor nucleo
        List<String[]> nucleoPrincipale = readCsv(csvSoggettiNucleoPrincipale);
        List<Tabsoggetto> parentinucleoprincipale = nucleoPrincipale.stream().map(Tabsoggetto::new).collect(Collectors.toList());

        tabsoggettoJPARepository.saveAll(parentinucleoprincipale);
        return parentinucleoprincipale;

    }

    protected void insertResidenzaSponsor(Tabsoggetto sponsor, String csvResidenzaSponsor) {
        Tabresidenze residenzaSponsor = new Tabresidenze(readCsv(csvResidenzaSponsor).get(0));
        residenzaSponsor.setRifSoggetto(sponsor.getIdSoggetto());
        tabresidenzeJPARepository.save(residenzaSponsor);
    }

    protected void insertResidenzeAltri(List<Tabsoggetto> parentinucleoprincipale, String csvResidenzeNucleoPrincipale) {
        List<String[]> residenzeNucleoPrincipale = readCsv(csvResidenzeNucleoPrincipale);
        List<Tabresidenze> residenzeParentiNucleoPrincipale = IntStream.range(0, residenzeNucleoPrincipale.size()).mapToObj(index -> {
            Tabresidenze tabresidenze = new Tabresidenze(residenzeNucleoPrincipale.get(index));
            tabresidenze.setRifSoggetto(parentinucleoprincipale.get(index).getIdSoggetto());
            return tabresidenze;
        }).collect(Collectors.toList());

        tabresidenzeJPARepository.saveAll(residenzeParentiNucleoPrincipale);

    }

    protected Tabistanza insertIstanza(String csvIstanzaNucleoPrincipale, Tabsoggetto sponsor, List<Tabsoggetto> parentinucleoprincipale) {
        Tabistanza tabistanza = new Tabistanza(readCsv(csvIstanzaNucleoPrincipale).get(0));
        tabistanza.setIdSponsor(sponsor.getIdSoggetto());
        tabistanzaJPARepository.save(tabistanza);
        Tabsoggettiistanze tabsoggettiistanzeSponsor = new Tabsoggettiistanze(new TabsoggettiistanzePK(tabistanza.getIdIstanza(), sponsor.getIdSoggetto()));
        List<Tabsoggettiistanze> tabsoggettiistanzeList = parentinucleoprincipale.stream().map(tabsoggettoNucleoFamiliare -> new Tabsoggettiistanze(new TabsoggettiistanzePK(tabistanza.getIdIstanza(), tabsoggettoNucleoFamiliare.getIdSoggetto()))).collect(Collectors.toList());
        tabsoggettiistanzeList.add(tabsoggettiistanzeSponsor);
        tabsoggettiistanzeJPARepository.saveAll(tabsoggettiistanzeList);
        return tabistanza;

    }


    protected Tabnucleifull insertNucleoFullForSponsorOCapofamiglia(Tabsoggetto sponsorOCapofamiglia, boolean isSponsor, Tabistanza tabistanza) {
        Tabnucleifull tabnucleifullSponsor = new Tabnucleifull(true, isSponsor, tabistanza.getIdIstanza(), -1, sponsorOCapofamiglia.getIdSoggetto());
        tabnucleifullJPARepository.save(tabnucleifullSponsor);
        tabnucleifullSponsor.setRifNucleo(tabnucleifullSponsor.getId());
        tabnucleifullJPARepository.save(tabnucleifullSponsor);
        return tabnucleifullSponsor;
    }

    protected void insertNucleoFullPerAltri(Tabnucleifull tabnucleifullSponsor, List<Tabsoggetto> parentinucleoprincipale, Tabistanza tabistanza) {
        List<Tabnucleifull> tabNucleiFullParenti = parentinucleoprincipale.stream().map(tabsoggettoNucleoFamiliare -> new Tabnucleifull(false, false, tabistanza.getIdIstanza(), tabnucleifullSponsor.getId(), tabsoggettoNucleoFamiliare.getIdSoggetto())).collect(Collectors.toList());
        tabnucleifullJPARepository.saveAll(tabNucleiFullParenti);
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

        String csvSponsor = csvPath + CSVFileNames.SPONSOR;
        String csvSoggettiNucleoPrincipale = csvPath + CSVFileNames.SOGGETTINUCLEOPRINCIPALE;
        String csvResidenzaSponsor = csvPath + CSVFileNames.RESIDENZASPONSOR;
        String csvResidenzeNucleoPrincipale = csvPath + CSVFileNames.RESIDENZENUCLEOPRINCIPALE;
        String csvIstanzaNucleoPrincipale = csvPath + CSVFileNames.ISTANZANUCLEOPRINCIPALE;
        String csvMandatoPagamentoPrincipale = csvPath + CSVFileNames.MANDATOPAGAMENTONUCLEOPRINCIPALE;
        String csvMandatoPagamentoPVCPrincipale = csvPath + CSVFileNames.MANDATOPAGAMENTOPVCNUCLEOPRINCIPALE;

        //creazione dello sponsor
        Tabsoggetto sponsor = insertSponsor(csvSponsor);
        //creazione degli altri soggetti
        List<Tabsoggetto> listaSoggettiDelNucleoPrincipale = insertAltriSoggetti(csvSoggettiNucleoPrincipale);

        //inserimento residenza sponsor
        insertResidenzaSponsor(sponsor, csvResidenzaSponsor);
        //aggancio delle residenze altri
        insertResidenzeAltri(
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

        //creazione del nucleo familiare: sponsor e capofamiglia
        Tabnucleifull tabnucleifullSponsor = insertNucleoFullForSponsorOCapofamiglia(
                sponsor,
                true,
                tabistanza
        );
        //creazione del nucleo familiare: altri
        insertNucleoFullPerAltri(
                tabnucleifullSponsor,
                listaSoggettiDelNucleoPrincipale,
                tabistanza
        );

        //inserimento dei mandati di pagamento
        insertMandati(
                sponsor,
                tabnucleifullSponsor,
                csvMandatoPagamentoPrincipale,
                csvMandatoPagamentoPVCPrincipale
        );


        String csvSoggettiNucleoEsterno = csvPath + CSVFileNames.SOGGETTINUCLEOESTERNO;
        String csvResidenzeNucleoEsterno = csvPath + CSVFileNames.RESIDENZENUCLEOESTERNO;
        String csvIstanzaNucleoEsterno = csvPath + CSVFileNames.ISTANZANUCLEOESTERNO;
        String csvMandatoPagamentoEsterno = csvPath + CSVFileNames.MANDATOPAGAMENTONUCLEOESTERNO;
        String csvMandatoPagamentoPVCEsterno = csvPath + CSVFileNames.MANDATOPAGAMENTOPVCNUCLEOESTERNO;

        //creazione degli altri soggetti
        List<Tabsoggetto> listaSoggettiDelNucleoEsterno = insertAltriSoggetti(csvSoggettiNucleoEsterno);
        //aggancio delle residenze altri
        insertResidenzeAltri(
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
        //creazione del nucleo familiare esterno: il capofamiglia è la prima riga del csv
        Tabnucleifull tabnucleifullCapofamigliaEsterno = insertNucleoFullForSponsorOCapofamiglia(
                listaSoggettiDelNucleoEsterno.get(0),
                false,
                tabistanzaNucleoEsterno
        );
        //creazione del nucleo familiare: altri (elimino il capofamiglia)
        insertNucleoFullPerAltri(
                tabnucleifullCapofamigliaEsterno,
                listaSoggettiDelNucleoEsterno.subList(1,listaSoggettiDelNucleoEsterno.size()),
                tabistanzaNucleoEsterno
        );

        //inserimento dei mandati di pagamento per il nucleo esterno
        insertMandati(
                sponsor,
                tabnucleifullCapofamigliaEsterno,
                csvMandatoPagamentoEsterno,
                csvMandatoPagamentoPVCEsterno
        );

    }


}
