package com.entando.sme.cartaesercito.smeceintegrationlayers;

import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.projections.SoggettoNucleoFamiliareDTOView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

@SpringBootTest
class SmeCeIntegrationLayersJdbcTemplateTests {


    @Value("${app.query.nucleoFamiliarePrincipaleSponsor}")
    String nucleoFamiliarePrincipaleSponsorQuery;
    @Value("${app.query.nucleoFamiliareEsternoSponsor}")
    String nucleoFamiliareEsternoSponsorQuery;

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Test
    void nucleoFamiliarePrincipaleSponsor() throws IOException {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceFiscale", "GDFGMN70D16H501T");
        Stream<SoggettoNucleoFamiliareDTOView> soggettoNucleoFamiliareDTOViewStream = jdbcTemplate.queryForStream(nucleoFamiliarePrincipaleSponsorQuery, namedParameters, new RowMapper<SoggettoNucleoFamiliareDTOView>() {
            @Override
            public SoggettoNucleoFamiliareDTOView mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SoggettoNucleoFamiliareDTOView(rs);
            }
        });

        soggettoNucleoFamiliareDTOViewStream.forEach(soggettoNucleoFamiliareDTOView -> System.out.println("soggettoNucleoFamiliareDTOView = " + soggettoNucleoFamiliareDTOView));

    }
    @Test
    void nucleoFamiliareEsternoSponsor() throws IOException {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceFiscale", "GDFGMN70D16H501T");
        Stream<SoggettoNucleoFamiliareDTOView> soggettoNucleoFamiliareDTOViewStream = jdbcTemplate.queryForStream(nucleoFamiliareEsternoSponsorQuery, namedParameters, new RowMapper<SoggettoNucleoFamiliareDTOView>() {
            @Override
            public SoggettoNucleoFamiliareDTOView mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SoggettoNucleoFamiliareDTOView(rs);
            }
        });

        soggettoNucleoFamiliareDTOViewStream.forEach(soggettoNucleoFamiliareDTOView -> System.out.println("soggettoNucleoFamiliareDTOView = " + soggettoNucleoFamiliareDTOView));

    }


}
