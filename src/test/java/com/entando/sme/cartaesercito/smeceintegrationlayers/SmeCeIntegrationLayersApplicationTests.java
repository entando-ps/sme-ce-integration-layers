package com.entando.sme.cartaesercito.smeceintegrationlayers;

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
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class SmeCeIntegrationLayersApplicationTests {


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Data
    @AllArgsConstructor
    private static class InsertTabSoggettoParams {
        private String codiceFiscale;
        private String rif_gradoQualifica;
        private String rif_posizione;
        private String nome;
        private String cognome;
        private String nascitaData;
        private String nascitaLuogo;
        private String nazionalita;
        private String fototessera;
        private String anagraficaVerificataDa;
        private String enteAppartenenza;
        private String telUfficio;
        private String telCellulare;
        private String email;
        private String rif_rapporto;
        private String pin;
        private String sesso;


    }

    @Test
    void insertSubject() throws IOException {
        String insertSubject = "/Users/germano/projects/SME/sme-ce-integration-layers/files/insertSubject.sql";
        String insertTemplate = getFile(insertSubject);

        InsertTabSoggettoParams params = new InsertTabSoggettoParams(
                "GDCGMN73D16H501T",
                "1",
                "1",
                "nome",
                "cognome",
                "datanascita",
                "luogonascita",
                "nazionalita",
                "fototessera",
                "1",
                "enteAppartenenza",
                "telUfficio",
                "telCellulare",
                "email",
                "1",
                "pin",
                "sesso"
        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(params);
        jdbcTemplate.update(insertTemplate, batch[0],keyHolder);

    }

    private String getFile(String sqlFilePath)
            throws IOException {
        return new String(Files.readAllBytes(Paths.get(sqlFilePath)));
    }

}
