package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QueryExecutorService {

    @Value("${app.query.nucleoFamiliarePrincipaleSponsor}")
    String nucleoFamiliarePrincipaleSponsorQuery;
    @Value("${app.query.nucleoFamiliareEsternoSponsor}")
    String nucleoFamiliareEsternoSponsorQuery; //TODO WARNING come gestisco + nuclei familiari, questo torna solo l'ultimo

    @Value("${app.query.cartaEsercitoPerSoggettiNucleoPrincipale}")
    String cartaEsercitoPerSoggettiNucleoPrincipale;


    @Value("${app.query.cartaEsercitoPerSoggettiNucleoEsterno}")
    String cartaEsercitoPerSoggettiNucleoEsterno; //TODO WARNING come gestisco + nuclei familiari, questo torna solo l'ultimo


    final
    NamedParameterJdbcTemplate jdbcTemplate;


    public QueryExecutorService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SoggettoNucleoFamiliareDTOView> nucleoFamiliarePrincipaleSponsor(String codiceFiscale) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceFiscale", codiceFiscale);
        Stream<SoggettoNucleoFamiliareDTOView> soggettoNucleoFamiliareDTOViewStream = jdbcTemplate.queryForStream(nucleoFamiliarePrincipaleSponsorQuery, namedParameters, (rs, rowNum) -> new SoggettoNucleoFamiliareDTOView(rs));

        return soggettoNucleoFamiliareDTOViewStream.collect(Collectors.toList());

    }

    //TODO WARNING come gestisco + nuclei familiari, questo torna solo l'ultimo
    public List<SoggettoNucleoFamiliareDTOView> nucleoFamiliareEsternoSponsor(String codiceFiscale) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceFiscale", codiceFiscale);
        Stream<SoggettoNucleoFamiliareDTOView> soggettoNucleoFamiliareDTOViewStream = jdbcTemplate.queryForStream(nucleoFamiliareEsternoSponsorQuery, namedParameters, (rs, rowNum) -> new SoggettoNucleoFamiliareDTOView(rs));
        return soggettoNucleoFamiliareDTOViewStream.collect(Collectors.toList());
    }

    /**
     * ritorna una mappa codicefiscale/carta esercito presente nel db
     * TODO WARNING le query di filtro vanno controllate, non è detto siano corrette
     *
     * @param moduloDTO
     * @return
     */
    public Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> carteEsercitoPerNucleoFamiliarePrincipaleSponsor(ModuloDTO moduloDTO) {

        Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> ret = new HashMap<>(moduloDTO.getNucleoPrincipale().size() + 1);
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceFiscale", moduloDTO.getSponsor().getCodiceFiscale());
        List<StatoCartaEsercitoPerSoggettoDTOView> statoCartaEsercitoPerSoggettoDTOViewList = jdbcTemplate.queryForStream(cartaEsercitoPerSoggettiNucleoPrincipale, namedParameters, (rs, rowNum) -> new StatoCartaEsercitoPerSoggettoDTOView(rs.getString("codiceFiscale"), rs.getInt("rif_statoCarta"), rs.getDate("dataRilascioCarta"), rs.getDate("dataScadenzaCarta"))).collect(Collectors.toList());
        List<ModuloDTO.Soggetto> soggettiNucleoPrincipale = moduloDTO.getNucleoPrincipaleConSponsor();
        soggettiNucleoPrincipale.forEach(soggetto -> {
            ret.put(soggetto.getCodiceFiscale(),Optional.empty());
            StatoCartaEsercitoPerSoggettoDTOView view = statoCartaEsercitoPerSoggettoDTOViewList.stream()
                    .filter(statoCartaEsercitoPerSoggettoDTOView -> soggetto.getCodiceFiscale().equals(statoCartaEsercitoPerSoggettoDTOView.getCodiceFiscale()))
                    .findAny()
                    .orElse(null);
            ret.put(soggetto.getCodiceFiscale(), Optional.ofNullable(view));
        });
        return ret;

    }
    public Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> carteEsercitoPerNucleoEsterno(ModuloDTO moduloDTO) {
        if(moduloDTO.getNucleiEsterni().size()==0) {
            return new HashMap<>();
        }
        Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> ret = new HashMap<>(moduloDTO.getNucleiEsterni().get(0).size()); //TODO WARNING gestione di un solo nucleo familiare esterno
        //inizializzazione mappa con Empty
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceFiscale", moduloDTO.getSponsor().getCodiceFiscale());
        List<StatoCartaEsercitoPerSoggettoDTOView> statoCartaEsercitoPerSoggettoDTOViewList = jdbcTemplate.queryForStream(cartaEsercitoPerSoggettiNucleoEsterno, namedParameters, (rs, rowNum) -> new StatoCartaEsercitoPerSoggettoDTOView(rs.getString("codiceFiscale"), rs.getInt("rif_statoCarta"), rs.getDate("dataRilascioCarta"), rs.getDate("dataScadenzaCarta"))).collect(Collectors.toList());
        List<ModuloDTO.Soggetto> soggettiNucleoEsterno = new ArrayList<>(moduloDTO.getNucleiEsterni().get(0));
        soggettiNucleoEsterno.forEach(soggetto -> {
            ret.put(soggetto.getCodiceFiscale(),Optional.empty());
            StatoCartaEsercitoPerSoggettoDTOView view = statoCartaEsercitoPerSoggettoDTOViewList.stream()
                    .filter(statoCartaEsercitoPerSoggettoDTOView -> soggetto.getCodiceFiscale().equals(statoCartaEsercitoPerSoggettoDTOView.getCodiceFiscale()))
                    .findAny()
                    .orElse(null);
            ret.put(soggetto.getCodiceFiscale(), Optional.ofNullable(view));
        });
        return ret;
    }


}
