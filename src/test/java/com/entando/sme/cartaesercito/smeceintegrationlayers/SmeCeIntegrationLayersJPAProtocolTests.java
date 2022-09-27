package com.entando.sme.cartaesercito.smeceintegrationlayers;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.*;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.entando.sme.cartaesercito.smeceintegrationlayers.Utils.parseString;

@SpringBootTest
class SmeCeIntegrationLayersJPAProtocolTests {


    @Autowired
    TabsoggettoJPARepository tabsoggettoJPARepository;
    @Autowired
    TabresidenzeJPARepository tabresidenzeJPARepository;
    @Autowired
    TabistanzaJPARepository tabistanzaJPARepository;
    @Autowired
    TabsoggettiistanzeJPARepository tabsoggettiistanzeJPARepository;
    @Autowired
    TabnucleifullJPARepository tabnucleifullJPARepository;
    @Autowired
    TabmandatoJPARepository tabmandatoJPARepository;
    @Autowired
    TabmandatopvcJPARepository tabmandatopvcJPARepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void istanzaNucleoPrincipaleConNuoviSoggetti() {

        String csvSponsor = "/Users/germano/projects/SME/sme-ce-integration-layers/files/sponsor.csv";
        String csvNucleoPrincipale = "/Users/germano/projects/SME/sme-ce-integration-layers/files/nucleoprincipale.csv";
        String csvResidenzaSponsor = "/Users/germano/projects/SME/sme-ce-integration-layers/files/residenzasponsor.csv";
        String csvResidenzeNucleoPrincipale = "/Users/germano/projects/SME/sme-ce-integration-layers/files/residenzaparentinucleoprincipale.csv";
        String csvIstanzaNucleoPrincipale = "/Users/germano/projects/SME/sme-ce-integration-layers/files/istanzanucleoprincipale.csv";
        String csvMandatoPagamento = "/Users/germano/projects/SME/sme-ce-integration-layers/files/mandatopagamento.csv";
        String csvMandatoPagamentoPVC = "/Users/germano/projects/SME/sme-ce-integration-layers/files/mandatopagamentopvc.csv";

        List<String[]> sponsorProperties = readCsv(csvSponsor);
        Tabsoggetto sponsor = new Tabsoggetto(sponsorProperties.get(0));
        List<String[]> nucleoPrincipale = readCsv(csvNucleoPrincipale);
        List<Tabsoggetto> parentinucleoprincipale = nucleoPrincipale.stream().map(Tabsoggetto::new).collect(Collectors.toList());

        tabsoggettoJPARepository.save(sponsor);
        tabsoggettoJPARepository.saveAll(parentinucleoprincipale);

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

        Tabistanza tabistanza = new Tabistanza(readCsv(csvIstanzaNucleoPrincipale).get(0));
        tabistanza.setIdSponsor(sponsor.getIdSoggetto());
        tabistanzaJPARepository.save(tabistanza);

        Tabsoggettiistanze tabsoggettiistanzeSponsor = new Tabsoggettiistanze(new TabsoggettiistanzePK(tabistanza.getIdIstanza(), sponsor.getIdSoggetto()));

        List<Tabsoggettiistanze> tabsoggettiistanzeList = parentinucleoprincipale.stream().map(tabsoggettoNucleoFamiliare -> new Tabsoggettiistanze(new TabsoggettiistanzePK(tabistanza.getIdIstanza(), tabsoggettoNucleoFamiliare.getIdSoggetto()))).collect(Collectors.toList());
        tabsoggettiistanzeList.add(tabsoggettiistanzeSponsor);
        tabsoggettiistanzeJPARepository.saveAll(tabsoggettiistanzeList);

        Tabnucleifull tabnucleifullSponsor = new Tabnucleifull(true, true, tabistanza.getIdIstanza(), -1, sponsor.getIdSoggetto());
        tabnucleifullJPARepository.save(tabnucleifullSponsor);
        tabnucleifullSponsor.setRifNucleo(tabnucleifullSponsor.getId());


        List<Tabnucleifull> tabNucleiFullParenti = parentinucleoprincipale.stream().map(tabsoggettoNucleoFamiliare -> new Tabnucleifull(false, false, tabistanza.getIdIstanza(), tabnucleifullSponsor.getId(), tabsoggettoNucleoFamiliare.getIdSoggetto())).collect(Collectors.toList());
        tabNucleiFullParenti.add(tabnucleifullSponsor);
        tabnucleifullJPARepository.saveAll(tabNucleiFullParenti);


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


    public List<String[]> readCsv(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String fileLine = " ";
            List<String[]> csvLine = new ArrayList<>();
            boolean isHeader = true;
            while ((fileLine = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                csvLine.add(fileLine.split(","));

            }
            br.close();
            return csvLine;
        } catch (Exception ioe) {
            throw new RuntimeException(ioe);
        }
    }


}
