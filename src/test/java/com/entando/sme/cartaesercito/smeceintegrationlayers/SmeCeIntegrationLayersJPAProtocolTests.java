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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Test
    @Transactional
    @Rollback(value = false)
    public void create() {

        List<String[]> sponsorProperties = readCsv("/Users/germano/projects/SME/sme-ce-integration-layers/files/sponsor.csv");
        Tabsoggetto sponsor = new Tabsoggetto(sponsorProperties.get(0));
        List<String[]> nucleoPrincipale = readCsv("/Users/germano/projects/SME/sme-ce-integration-layers/files/nucleoprincipale.csv");
        List<Tabsoggetto> parentinucleoprincipale = nucleoPrincipale.stream().map(Tabsoggetto::new).collect(Collectors.toList());

        tabsoggettoJPARepository.save(sponsor);
        tabsoggettoJPARepository.saveAll(parentinucleoprincipale);


        Tabresidenze residenzaSponsor = new Tabresidenze(readCsv("/Users/germano/projects/SME/sme-ce-integration-layers/files/residenzasponsor.csv").get(0));
        residenzaSponsor.setRifSoggetto(sponsor.getIdSoggetto());
        tabresidenzeJPARepository.save(residenzaSponsor);


        List<String[]> residenzeNucleoPrincipale = readCsv("/Users/germano/projects/SME/sme-ce-integration-layers/files/residenzaparentinucleoprincipale.csv");
        List<Tabresidenze> residenzeParentiNucleoPrincipale = IntStream.range(0,residenzeNucleoPrincipale.size()).mapToObj(index -> {
            Tabresidenze tabresidenze = new Tabresidenze(residenzeNucleoPrincipale.get(index));
            tabresidenze.setRifSoggetto(parentinucleoprincipale.get(index).getIdSoggetto());
            return tabresidenze;
        }).collect(Collectors.toList());

        tabresidenzeJPARepository.saveAll(residenzeParentiNucleoPrincipale);

/*
        Tabistanza tabistanza = new Tabistanza(
                new java.sql.Date(Calendar.getInstance().getTime().getTime()),
                sponsor.getIdSoggetto(),
                1,
                0,
                1,
                1
        );

        tabistanzaJPARepository.save(tabistanza);

        Tabsoggettiistanze tabsoggettiistanzeSponsor = new Tabsoggettiistanze(new TabsoggettiistanzePK(tabistanza.getIdIstanza(), sponsor.getIdSoggetto()));


        Tabsoggettiistanze tabsoggettiistanzeFiglio = new Tabsoggettiistanze(new TabsoggettiistanzePK(tabistanza.getIdIstanza(), figlio.getIdSoggetto()));

        tabsoggettiistanzeJPARepository.save(tabsoggettiistanzeSponsor);
        tabsoggettiistanzeJPARepository.save(tabsoggettiistanzeFiglio);

        Tabnucleifull tabnucleifullSponsor = new Tabnucleifull(true, true, tabistanza.getIdIstanza(), -1, sponsor.getIdSoggetto());
        tabnucleifullJPARepository.save(tabnucleifullSponsor);
        tabnucleifullSponsor.setRifNucleo(tabnucleifullSponsor.getId());
        tabnucleifullJPARepository.save(tabnucleifullSponsor);

        Tabnucleifull tabnucleifullFiglio = new Tabnucleifull(false, false, tabistanza.getIdIstanza(), tabnucleifullSponsor.getId(), figlio.getIdSoggetto());
        tabnucleifullJPARepository.save(tabnucleifullFiglio);
*/

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
