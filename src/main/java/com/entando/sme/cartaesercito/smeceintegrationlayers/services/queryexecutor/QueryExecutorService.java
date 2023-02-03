package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import com.entando.sme.cartaesercito.smeceintegrationlayers.config.ConfigurationParameters;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QueryExecutorService {
    private final String carteEsercitoPerSponsor;
    private final Set<Integer> tipiIstanzaNucleoPrincipale;
    private final Set<Integer> tipiIstanzaNucleoEsterno;


    private final
    NamedParameterJdbcTemplate jdbcTemplate;

    public QueryExecutorService(NamedParameterJdbcTemplate jdbcTemplate, ConfigurationParameters configurationParameters) {
        this.jdbcTemplate = jdbcTemplate;
        tipiIstanzaNucleoPrincipale = Set.of(configurationParameters.getIstanza().getNucleoPrincipale(), configurationParameters.getIstanza().getItegrNucleoPrincipale());
        tipiIstanzaNucleoEsterno = Set.of(configurationParameters.getIstanza().getNucleoEsterno(), configurationParameters.getIstanza().getIntegrNucleoEsterno());
        carteEsercitoPerSponsor = configurationParameters.getQuery().getCarteEsercitoPerSponsor();

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

    public Integer getNextRifNucleo(){
        //TODO WARN problematiche di concorrenza! implementare lato nostro e BO autoincrement
        Integer rifNucleo = jdbcTemplate.queryForObject("select max(rif_nucleo) from tabnucleifull", new HashMap<>(), Integer.class);
        return rifNucleo!=null?rifNucleo+1:1;
    }
}
