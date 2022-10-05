package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

@Data
@AllArgsConstructor
public class CartaEsercitoPerSoggettoPerNucleoDTOView {
    private Integer rifNucleo;
    private Integer rifTipoIstanza; //1,3 nucleo principale 4,5 nucleo secondario
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private Boolean isSponsor;
    private Integer rifStatoIstanza;
    private String numeroCarta;
    private Integer rifStatoCarta;
    private Date dataScadenzaCarta;
    private Date dataRilascioCarta;


    public Optional<TipoNucleo> getTipoNucleo(Set<Integer> tipiIstanzaNucleoPrincipale, Set<Integer> tipiIstanzaNucleoEsterno) {
        if (rifTipoIstanza == null) return Optional.empty();
        if (tipiIstanzaNucleoPrincipale.contains(rifTipoIstanza)) {
            return Optional.of(TipoNucleo.Principale);
        }
        if (tipiIstanzaNucleoEsterno.contains(rifTipoIstanza)) {
            return Optional.of(TipoNucleo.Esterno);
        }
        throw new RuntimeException("Il tipo istanza "+rifStatoIstanza+" non è riconosciuto per CartaEsercitoPerSoggettoPerNucleoDTOView "+this);
    }
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

    public enum TipoNucleo {
        Principale,
        Esterno
    }

    public static Map<TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> groupByTipoNucleo(List<CartaEsercitoPerSoggettoPerNucleoDTOView> list, Set<Integer> tipiIstanzaNucleoPrincipale, Set<Integer> tipiIstanzaNucleoEsterno) {
        return list.stream().filter(cartaEsercitoPerSoggettoPerNucleoDTOView -> cartaEsercitoPerSoggettoPerNucleoDTOView.getTipoNucleo(tipiIstanzaNucleoPrincipale, tipiIstanzaNucleoEsterno).isPresent()).collect(groupingBy(cartaEsercitoPerSoggettoPerNucleoDTOView -> cartaEsercitoPerSoggettoPerNucleoDTOView.getTipoNucleo(tipiIstanzaNucleoPrincipale, tipiIstanzaNucleoEsterno).get()));
    }
}

