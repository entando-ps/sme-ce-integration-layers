package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.QueryExecutorService;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor.StatoCartaEsercitoPerSoggettoDTOView;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.CostiDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.SmeCEBOJdbcProtocolConfigurationParameters;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SmeCeBoCostiService {

    private final
    SmeCEBOJdbcProtocolConfigurationParameters configParameters;

    private final
    QueryExecutorService queryExecutorService;

    public SmeCeBoCostiService(SmeCEBOJdbcProtocolConfigurationParameters configParameters, QueryExecutorService queryExecutorService) {
        this.configParameters = configParameters;
        this.queryExecutorService = queryExecutorService;
    }


    public CostiDTO calcoloCosti(ModuloDTO moduloDTO) {
        CostiDTO costiDTO = new CostiDTO();
        Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> carteEsercitoNucleoFamiliarePrincipaleConSponsor = queryExecutorService.carteEsercitoPerNucleoFamiliarePrincipaleSponsor(moduloDTO);
        List<CostiDTO.CostoPerSoggettoDTO> costiNucleoPrincipaleESponsor = calcoloCostiNucleoPrincipaleESponsor(carteEsercitoNucleoFamiliarePrincipaleConSponsor, moduloDTO);
        costiDTO.setNucleoPrincipaleConSponsor(costiNucleoPrincipaleESponsor);
        Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> carteEsercitoNucleoEsterno = queryExecutorService.carteEsercitoPerNucleoEsterno(moduloDTO);
        List<CostiDTO.CostoPerSoggettoDTO> costiNucleoEsterno = calcoloCostoNucleoEsterno(carteEsercitoNucleoEsterno, moduloDTO);
        List<List<CostiDTO.CostoPerSoggettoDTO>> costiNucleiEsterni = new ArrayList<>();
        costiNucleiEsterni.add(costiNucleoEsterno); //TODO WARNING UN SOLO NUCLEO
        costiDTO.setNucleiEsterni(costiNucleiEsterni);
        costiDTO.setCostoSpedizione(calcolaCostoSpedizione(moduloDTO));
        return costiDTO;
    }

    protected List<CostiDTO.CostoPerSoggettoDTO> calcoloCostiNucleoPrincipaleESponsor(Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> carteEsercitoNucleoFamiliarePrincipale, ModuloDTO moduloDTO) {
        List<ModuloDTO.Soggetto> nucleoPrincipaleConSponsor = moduloDTO.getNucleoPrincipaleConSponsor();
        Stream<CostiDTO.CostoPerSoggettoDTO> costoPerSoggettoDTOStream = nucleoPrincipaleConSponsor.stream().map(soggetto -> {
            Optional<StatoCartaEsercitoPerSoggettoDTOView> statoCartaEsercitoPerSoggettoDTOView = carteEsercitoNucleoFamiliarePrincipale.get(soggetto.getCodiceFiscale());
            CostiDTO.CostoPerSoggettoDTO costoPerSoggettoDTO = new CostiDTO.CostoPerSoggettoDTO();
            costoPerSoggettoDTO.setSoggetto(soggetto);
            statoCartaEsercitoPerSoggettoDTOView.ifPresentOrElse(cartaEsercito -> {
                //la carta esiste guardo lo stato e decido se deve rinnovarla
                costoPerSoggettoDTO.setStatoCarta(cartaEsercito.getStatoCarta());
                /*
                    1,In corso di validità
                    2,Scaduta
                    3,Sospesa
                    4,Revocata
                    5,Smarrita
                    6,Deteriorata
                    7,In scadenza
                    8,Requisiti persi
                 */
                int costo = 0;
                if (cartaEsercito.getStatoCarta() != 1) {
                    if (soggetto.getIsSponsor()) {
                        costo = 8 * 100;
                    } else {
                        costo = 4 * 100;
                    }
                }
                costoPerSoggettoDTO.setCosto(costo);
            }, () -> {
                //la carta non esiste prima emissione
                costoPerSoggettoDTO.setStatoCarta(-1); //-1 prima emissione
                int costo = 0;
                if (soggetto.getIsSponsor()) {
                    costo = 8 * 100;
                } else {
                    costo = 4 * 100;
                }
                costoPerSoggettoDTO.setCosto(costo);
            });
            return costoPerSoggettoDTO;
        });

        return costoPerSoggettoDTOStream.collect(Collectors.toList());
    }


    protected List<CostiDTO.CostoPerSoggettoDTO> calcoloCostoNucleoEsterno(Map<String, Optional<StatoCartaEsercitoPerSoggettoDTOView>> carteEsercitoNucleoFamiliareEsterno, ModuloDTO moduloDTO) {
        List<List<ModuloDTO.Soggetto>> nucleiEsterni = moduloDTO.getNucleiEsterni();
        if (nucleiEsterni.size() == 0) return new ArrayList<>(); //nessun nucleo esterno
        List<ModuloDTO.Soggetto> nucleoPrincipaleConSponsor = nucleiEsterni.get(0); //TODO WARNING GESTISCO UN SOLO NUCLEO ESTERNO
        Stream<CostiDTO.CostoPerSoggettoDTO> costoPerSoggettoDTOStream = nucleoPrincipaleConSponsor.stream().map(soggetto -> {
            Optional<StatoCartaEsercitoPerSoggettoDTOView> statoCartaEsercitoPerSoggettoDTOView = carteEsercitoNucleoFamiliareEsterno.get(soggetto.getCodiceFiscale());
            CostiDTO.CostoPerSoggettoDTO costoPerSoggettoDTO = new CostiDTO.CostoPerSoggettoDTO();
            costoPerSoggettoDTO.setSoggetto(soggetto);
            statoCartaEsercitoPerSoggettoDTOView.ifPresentOrElse(cartaEsercito -> {
                //la carta esiste guardo lo stato e decido se deve rinnovarla
                costoPerSoggettoDTO.setStatoCarta(cartaEsercito.getStatoCarta());
                /*
                    1,In corso di validità
                    2,Scaduta
                    3,Sospesa
                    4,Revocata
                    5,Smarrita
                    6,Deteriorata
                    7,In scadenza
                    8,Requisiti persi
                 */
                int costo = 0;
                if (cartaEsercito.getStatoCarta() != 1) {
                    costo = 10 * 100;
                }
                costoPerSoggettoDTO.setCosto(costo);
            }, () -> {
                //la carta non esiste prima emissione
                costoPerSoggettoDTO.setStatoCarta(-1); //-1 prima emissione
                int costo = 10 * 100;
                costoPerSoggettoDTO.setCosto(costo);
            });
            return costoPerSoggettoDTO;
        });

        return costoPerSoggettoDTOStream.collect(Collectors.toList());
    }

    protected Integer calcolaCostoSpedizione(ModuloDTO moduloDTO) {
        if (moduloDTO.getResidenzaDiSpedizione() != null) { //aggiungo il costo di spedizione
            return 450;
        }
        return 0;
    }

    protected CostiDTO checkCostiNonSonoCambiati(ModuloDTO moduloDTO, CostiDTO oldCostiDTO) {
        CostiDTO currCostiDTO = calcoloCosti(moduloDTO);

        if (!currCostiDTO.equals(oldCostiDTO)) {
            throw new RuntimeException("costi non uguali!");
        }

        return currCostiDTO;

    }

}
