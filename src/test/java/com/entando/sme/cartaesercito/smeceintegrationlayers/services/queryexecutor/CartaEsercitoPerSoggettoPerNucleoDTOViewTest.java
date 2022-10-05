package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartaEsercitoPerSoggettoPerNucleoDTOViewTest {

    static Integer tipoIstanzaNucleoPrincipale = 1;
    static Integer tipoIstanzaAggiuntaNucleoPrincipale = 3;
    static Integer tipoIstanzaNucleoEsterno = 4;
    static Integer tipoIstanzaAggiuntaNucleoEsterno = 5;

    static Set<Integer> tipiIstanzaNucleoPrincipale = Set.of(tipoIstanzaNucleoPrincipale, tipoIstanzaAggiuntaNucleoPrincipale);
    static Set<Integer> tipiIstanzaNucleoEsterno = Set.of(tipoIstanzaNucleoEsterno, tipoIstanzaAggiuntaNucleoEsterno);

    @Test
    void groupByTipoNucleo() {

        List<CartaEsercitoPerSoggettoPerNucleoDTOView> nucleoPrincipale =
                IntStream.range(1, 5).mapToObj(index -> new CartaEsercitoPerSoggettoPerNucleoDTOView(1, index % 2 == 0 ? tipoIstanzaNucleoPrincipale : tipoIstanzaAggiuntaNucleoPrincipale, "nome" + index, "cognome" + index, "codiceFisacle" + index, index == 1, 1, "numeroCarta" + index, 1, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()))).collect(Collectors.toList());
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> nucleoEsterno1 =
                IntStream.range(1, 8).mapToObj(index -> new CartaEsercitoPerSoggettoPerNucleoDTOView(2, index % 2 == 0 ? tipoIstanzaNucleoEsterno : tipoIstanzaAggiuntaNucleoEsterno, "nome" + index, "cognome" + index, "codiceFisacle" + index, false, 1, "numeroCarta" + index, 1, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()))).collect(Collectors.toList());
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> nucleoEsterno2 =
                IntStream.range(1, 8).mapToObj(index -> new CartaEsercitoPerSoggettoPerNucleoDTOView(3, index % 2 == 0 ? tipoIstanzaNucleoEsterno : tipoIstanzaAggiuntaNucleoEsterno, "nome" + index, "cognome" + index, "codiceFisacle" + index, false, 1, "numeroCarta" + index, 1, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()))).collect(Collectors.toList());
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> list = new ArrayList<>(nucleoPrincipale);
        list.addAll(nucleoEsterno1);
        list.addAll(nucleoEsterno2);
        Map<CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> tipoNucleoListMap = CartaEsercitoPerSoggettoPerNucleoDTOView.groupByTipoNucleo(list, tipiIstanzaNucleoPrincipale, tipiIstanzaNucleoEsterno);

        assertEquals(nucleoPrincipale.size(), tipoNucleoListMap.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Principale).size());
        assertEquals(nucleoEsterno1.size() + nucleoEsterno2.size(), tipoNucleoListMap.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Esterno).size());

        tipoNucleoListMap.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Principale).forEach(cartaEsercitoPerSoggettoPerNucleoDTOView -> assertEquals(1, (int) cartaEsercitoPerSoggettoPerNucleoDTOView.getRifNucleo()));
        tipoNucleoListMap.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Esterno).forEach(cartaEsercitoPerSoggettoPerNucleoDTOView -> assertTrue(Set.of(2,3).contains(cartaEsercitoPerSoggettoPerNucleoDTOView.getRifNucleo())));

    }
}
