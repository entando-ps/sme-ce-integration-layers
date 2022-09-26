package com.entando.sme.cartaesercito.smeceintegrationlayers;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabistanza;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabresidenze;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabsoggetto;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.TabistanzaJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.TabresidenzeJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.TabsoggettoJPARepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class SmeCeIntegrationLayersJPAProtocolTests {


    @Autowired
    TabsoggettoJPARepository tabsoggettoJPARepository;
    @Autowired
    TabresidenzeJPARepository tabresidenzeJPARepository;
    @Autowired
    TabistanzaJPARepository tabistanzaJPARepository;

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


    }


}
