package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTODopoSME;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QueryExecutorServiceDopoSME {

    @Value("${app.query.carteEsercitoPerSponsor}")
    private String carteEsercitoPerSponsor;
    static Set<Integer> tipiIstanzaNucleoPrincipale = Set.of(1, 3);
    static Set<Integer> tipiIstanzaNucleoEsterno = Set.of(4, 5);


    final
    NamedParameterJdbcTemplate jdbcTemplate;


    public QueryExecutorServiceDopoSME(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Map<CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> getCarteEsercitoPerSponsor(String codiceFiscale) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceFiscale", codiceFiscale);
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> cartaEsercitoPerSoggettoPerNucleoDTOViewList = jdbcTemplate.queryForStream(carteEsercitoPerSponsor, namedParameters, (rs, rowNum) -> new CartaEsercitoPerSoggettoPerNucleoDTOView(
                rs.getInt("rifNucleo"),
                rs.getInt("rifTipoIstanza"),
                rs.getString("nome"),
                rs.getString("cognome"),
                rs.getString("email"),
                rs.getString("enteAppartenenza"),
                rs.getString("fototessera"),
                rs.getString("nascitaData"),
                rs.getString("nascitaLuogo"),
                rs.getString("nazionalita"),
                rs.getInt("rifGradoQualifica"),
                rs.getInt("rifPosizione"),
                rs.getInt("rifRapporto"),
                rs.getString("sesso"),
                rs.getString("telCellulare"),
                rs.getString("telUfficio"),
                rs.getString("codiceFiscale"),
                rs.getBoolean("isSponsor"),
                rs.getInt("rifStatoIstanza"),
                rs.getString("numeroCarta"),
                rs.getInt("rifStatoCarta"),
                rs.getDate("dataScadenzaCarta"),
                rs.getDate("dataRilascioCarta"),
                rs.getInt("rifStato")
        )).collect(Collectors.toList());
        return CartaEsercitoPerSoggettoPerNucleoDTOView.groupByTipoNucleo(cartaEsercitoPerSoggettoPerNucleoDTOViewList, tipiIstanzaNucleoPrincipale, tipiIstanzaNucleoEsterno);
    }

}
