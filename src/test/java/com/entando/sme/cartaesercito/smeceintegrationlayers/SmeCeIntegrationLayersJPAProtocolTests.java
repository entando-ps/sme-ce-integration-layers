package com.entando.sme.cartaesercito.smeceintegrationlayers;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.*;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

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

        Tabsoggetto sponsor = new Tabsoggetto(
                1,
                "GDFGMN70D16H501T",
                "cognome",
                "email",
                "emteAppartenenza",
                "fototessera",
                "nascitaData",
                "nascitaLuogo",
                "nazionalita",
                "sponsor",
                "pin1",
                1,
                1,
                1,
                "sesso",
                "telCellulare",
                "telUfficio",
                "1"
        );
        Tabsoggetto figlio = new Tabsoggetto(
                1,
                "GDFGPR70D16H501T",
                "cognome",
                "email",
                "emteAppartenenza",
                "fototessera",
                "nascitaData",
                "nascitaLuogo",
                "nazionalita",
                "figlio",
                "pin2",
                1,
                1,
                1,
                "sesso",
                "telCellulare",
                "telUfficio",
                "1"
        );

        tabsoggettoJPARepository.save(sponsor);
        tabsoggettoJPARepository.save(figlio);


        Tabresidenze residenzaSponsor = new Tabresidenze("00100","Roma","1","","RM",sponsor.getIdSoggetto(),"V. del corso");
        Tabresidenze residenzaFiglio = new Tabresidenze("00100","Roma","1","","RM",sponsor.getIdSoggetto(),"V. Tuscolana");
        tabresidenzeJPARepository.save(residenzaSponsor);
        tabresidenzeJPARepository.save(residenzaFiglio);

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

        Tabnucleifull tabnucleifullSponsor = new Tabnucleifull(true,true,tabistanza.getIdIstanza(),-1, sponsor.getIdSoggetto());
        tabnucleifullJPARepository.save(tabnucleifullSponsor);
        tabnucleifullSponsor.setRifNucleo(tabnucleifullSponsor.getId());
        tabnucleifullJPARepository.save(tabnucleifullSponsor);

        Tabnucleifull tabnucleifullFiglio = new Tabnucleifull(false,false,tabistanza.getIdIstanza(),tabnucleifullSponsor.getId(), figlio.getIdSoggetto());
        tabnucleifullJPARepository.save(tabnucleifullFiglio);

    }


}
