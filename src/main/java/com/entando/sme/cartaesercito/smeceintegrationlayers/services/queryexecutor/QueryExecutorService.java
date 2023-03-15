package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import com.entando.sme.cartaesercito.smeceintegrationlayers.config.ConfigurationParameters;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.MandatoDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.MandatoPVCDTO;
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

    private final String mandatiPerSponsor;

    private final String mandatiPVCPerSponsor;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public QueryExecutorService(NamedParameterJdbcTemplate jdbcTemplate, ConfigurationParameters configurationParameters) {
        this.jdbcTemplate = jdbcTemplate;
        tipiIstanzaNucleoPrincipale = Set.of(configurationParameters.getIstanza().getNucleoPrincipale(), configurationParameters.getIstanza().getItegrNucleoPrincipale());
        tipiIstanzaNucleoEsterno = Set.of(configurationParameters.getIstanza().getNucleoEsterno(), configurationParameters.getIstanza().getIntegrNucleoEsterno());
        carteEsercitoPerSponsor = configurationParameters.getQuery().getCarteEsercitoPerSponsor();
        mandatiPerSponsor = configurationParameters.getQuery().getMandatiPerSponsor();
        mandatiPVCPerSponsor = configurationParameters.getQuery().getMandatiPVCPerSponsor();
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
                rs.getInt("rifStato"),
                rs.getInt("numeroMandato"),
                rs.getString("croMandato"),
                rs.getInt("rifStatoMandato"),
                rs.getInt("quotaMandato"),
                rs.getString("dataMandato"),
                rs.getInt("rifNucleoFull"),
                rs.getInt("numeroMandatoPVC"),
                rs.getString("croMandatoPVC"), //attestazione pagamento
                rs.getInt("rifNucleoFullPVC"),
                rs.getInt("quotaMandatoPVC"),
                rs.getInt("rifStatoMandatoPVC"),
                rs.getString("dataMandatoPVC")
        )).collect(Collectors.toList());
        return CartaEsercitoPerSoggettoPerNucleoDTOView.groupByTipoNucleo(cartaEsercitoPerSoggettoPerNucleoDTOViewList, tipiIstanzaNucleoPrincipale, tipiIstanzaNucleoEsterno);
    }

    public List<MandatoDTO> getMandatiPerSponsor(String codiceFiscale) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceFiscale", codiceFiscale);
        List<MandatoDTO> mandatiPerNucleoDTOViewList = jdbcTemplate.queryForStream(mandatiPerSponsor, namedParameters, (rs, rowNum) -> new MandatoDTO(
                rs.getInt("idMandato"),
                rs.getInt("rifStatoMandato"),
                rs.getInt("rifNucleoFull"),
                rs.getInt("quotaMandato"),
                rs.getString("dataMandato"),
                rs.getString("dataEmissione"),
                rs.getString("dataScadenza"),
                rs.getInt("rifSponsor"),
                rs.getString("nota"),
                rs.getString("notaConvalida"),
                rs.getString("notaAnnullamento"),
                rs.getString("dataAggiornamento"),
                rs.getInt("convalidaMandatoCovid"),
                rs.getString("attestazionePagamento")
        )).collect(Collectors.toList());
        return mandatiPerNucleoDTOViewList;
    }

    public List<MandatoPVCDTO> getMandatiPVCPerSponsor(String codiceFiscale) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceFiscale", codiceFiscale);
        List<MandatoPVCDTO> mandatiPVCPerNucleoDTOViewList = jdbcTemplate.queryForStream(mandatiPVCPerSponsor, namedParameters, (rs, rowNum) -> new MandatoPVCDTO(
                rs.getInt("idmandatopvc"),
                rs.getInt("rifnucleofullPvc"),
                rs.getInt("quotamandatoPvc"),
                rs.getInt("rifsponsorPvc"),
                rs.getString("notePvc"),
                rs.getString("notaconvalidaPvc"),
                rs.getString("notaannullamentoPvc"),
                rs.getInt("rifstatomandatoPvc"),
                rs.getString("dataaggiornamentoPvc"),
                rs.getString("datamandatoPvc"),
                rs.getString("dataemissionePvc"),
                rs.getInt("riftipomandatoPvc"),
                rs.getString("attestazionePagamento")
        )).collect(Collectors.toList());
        return mandatiPVCPerNucleoDTOViewList;
    }

    public Integer getNextRifNucleo(){
        //TODO WARN problematiche di concorrenza! implementare lato nostro e BO autoincrement aggiungere sequence
        Integer rifNucleo = jdbcTemplate.queryForObject("select max(rif_nucleo) from tabnucleifull", new HashMap<>(), Integer.class);
        return rifNucleo!=null?rifNucleo+1:1;
    }
}
