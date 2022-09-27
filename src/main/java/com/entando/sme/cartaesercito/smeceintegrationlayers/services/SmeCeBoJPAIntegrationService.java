package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.*;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.*;
import org.springframework.data.util.Pair;
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

    protected Pair<Tabsoggetto, List<Tabsoggetto>> insertSoggetti(String csvSponsor, String csvSoggettiNucleoPrincipale) {
        //creazione sponsor
        List<String[]> sponsorProperties = readCsv(csvSponsor);
        Tabsoggetto sponsor = new Tabsoggetto(sponsorProperties.get(0));

        //creazione sponsor nucleo
        List<String[]> nucleoPrincipale = readCsv(csvSoggettiNucleoPrincipale);
        List<Tabsoggetto> parentinucleoprincipale = nucleoPrincipale.stream().map(Tabsoggetto::new).collect(Collectors.toList());

        tabsoggettoJPARepository.save(sponsor);
        tabsoggettoJPARepository.saveAll(parentinucleoprincipale);
        return Pair.of(sponsor, parentinucleoprincipale);

    }

    protected void insertResidenze(Tabsoggetto sponsor, String csvResidenzaSponsor, List<Tabsoggetto> parentinucleoprincipale, String csvResidenzeNucleoPrincipale) {
        Tabresidenze residenzaSponsor = new Tabresidenze(readCsv(csvResidenzaSponsor).get(0));
        residenzaSponsor.setRifSoggetto(sponsor.getIdSoggetto());
        tabresidenzeJPARepository.save(residenzaSponsor);

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


    protected Tabnucleifull insertNucleoFull(Tabsoggetto sponsor, List<Tabsoggetto> parentinucleoprincipale, Tabistanza tabistanza) {
        Tabnucleifull tabnucleifullSponsor = new Tabnucleifull(true, true, tabistanza.getIdIstanza(), -1, sponsor.getIdSoggetto());
        tabnucleifullJPARepository.save(tabnucleifullSponsor);
        tabnucleifullSponsor.setRifNucleo(tabnucleifullSponsor.getId());


        List<Tabnucleifull> tabNucleiFullParenti = parentinucleoprincipale.stream().map(tabsoggettoNucleoFamiliare -> new Tabnucleifull(false, false, tabistanza.getIdIstanza(), tabnucleifullSponsor.getId(), tabsoggettoNucleoFamiliare.getIdSoggetto())).collect(Collectors.toList());
        tabNucleiFullParenti.add(tabnucleifullSponsor);
        tabnucleifullJPARepository.saveAll(tabNucleiFullParenti);

        return tabnucleifullSponsor;
    }

    protected void insertMandati(Tabsoggetto sponsor, Tabnucleifull tabnucleifullSponsor, String csvMandatoPagamento, String csvMandatoPagamentoPVC) {
        Tabmandato tabmandato = new Tabmandato(readCsv(csvMandatoPagamento).get(0));
        tabmandato.setRifSponsor(sponsor.getIdSoggetto());
        tabmandato.setRifNucleofull(tabnucleifullSponsor.getId());

        Tabmandatopvc tabmandatopvc = new Tabmandatopvc(readCsv(csvMandatoPagamentoPVC).get(0));

        tabmandatopvc.setRifSponsor(sponsor.getIdSoggetto());
        tabmandatopvc.setRifNucleoFull(tabnucleifullSponsor.getId());
        //il salvataggio avviene semmpre su entrambi cambiano gli importi a seconda del fatto che esista la spedizione tramite posta
        tabmandatoJPARepository.save(tabmandato);
        tabmandatopvcJPARepository.save(tabmandatopvc);

    }

    @Transactional
    public void istanzaNucleoPrincipaleConNuoviSoggetti(
            String csvPath
    ) {

        String csvSponsor = csvPath + CSVFileNames.SPONSOR;
        String csvSoggettiNucleoPrincipale = csvPath + CSVFileNames.SOGGETTINUCLEOPRINCIPALE;
        String csvResidenzaSponsor = csvPath + CSVFileNames.RESIDENZASPONSOR;
        String csvResidenzeNucleoPrincipale = csvPath + CSVFileNames.RESIDENZENUCLEOPRINCIPALE;
        String csvIstanzaNucleoPrincipale = csvPath + CSVFileNames.ISTANZANUCLEOPRINCIPALE;
        String csvMandatoPagamento = csvPath + CSVFileNames.MANDATOPAGAMENTO;
        String csvMandatoPagamentoPVC = csvPath + CSVFileNames.MANDATOPAGAMENTOPVC;

        //creazione dei soggetti
        Pair<Tabsoggetto, List<Tabsoggetto>> tabsoggettoPair = insertSoggetti(csvSponsor, csvSoggettiNucleoPrincipale);
        Tabsoggetto sponsor = tabsoggettoPair.getFirst();
        List<Tabsoggetto> listaSoggettiDelNucleoPrincipale = tabsoggettoPair.getSecond();

        //aggancio delle residenze
        insertResidenze(
                sponsor,
                csvResidenzaSponsor,
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

        //creazione del nucleo familiare
        Tabnucleifull tabnucleifullSponsor = insertNucleoFull(
                sponsor,
                listaSoggettiDelNucleoPrincipale,
                tabistanza
        );

        //inserimento dei mandati di pagamento
        insertMandati(
                sponsor,
                tabnucleifullSponsor,
                csvMandatoPagamento,
                csvMandatoPagamentoPVC
        );

    }


}
