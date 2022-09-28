package com.entando.sme.cartaesercito.smeceintegrationlayers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SmeCeIntegrationLayersJdbcTemplateTests {


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Test
    void query() throws IOException {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceFiscale", "GDFGMN70D16H501T");
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from tabnucleifull " +
                "join tabsoggetto on tabnucleifull.rif_soggetto=tabsoggetto.idSoggetto " +
                "where rif_istanza = (select idIstanza from tabistanza " +
                "where id_sponsor=(select idSoggetto from tabsoggetto where codiceFiscale=:codiceFiscale) and rif_tipoIstanza in (1, 3) ORDER BY idIstanza DESC LIMIT 1); ", namedParameters);

        System.out.println("maps = " + maps);

    }


}
