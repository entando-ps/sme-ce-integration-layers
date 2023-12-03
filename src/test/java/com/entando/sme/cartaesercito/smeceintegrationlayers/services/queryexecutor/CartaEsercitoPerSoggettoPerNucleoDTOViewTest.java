package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import java.sql.Date;
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
                IntStream.range(1,
                        5).mapToObj(index -> new CartaEsercitoPerSoggettoPerNucleoDTOView(1,
                        index % 2 == 0 ? tipoIstanzaNucleoPrincipale : tipoIstanzaAggiuntaNucleoPrincipale,
                        "nome" + index,
                        "cognome" + index,
                        "email " + index,
                        "enteAppartenenza",
                        "fototessera",
                        "nascitaData",
                        "nascitaLuogo",
                        "nazionalita",
                        1,
                        1,
                        1,
                        1,
                        "sesso",
                        "telCellulare",
                        "telUfficio",
                        "codiceFisacle" + index,
                        index == 1,
                        1,
                        "numeroCarta" + index,
                        1,
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        1,
                        123,
                        "cro-123",
                        1,
                        20,
                        "01/03/2023",
                        1,
                        321,
                        "cro-321",
                        1,
                        20,
                        1,
                        "dataMandatoPVC"
                )).collect(Collectors.toList());
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> nucleoEsterno1 =
                IntStream.range(1,
                        8).mapToObj(index -> new CartaEsercitoPerSoggettoPerNucleoDTOView(2,
                        index % 2 == 0 ? tipoIstanzaNucleoEsterno : tipoIstanzaAggiuntaNucleoEsterno,
                        "nome" + index,
                        "cognome" + index,
                        "email " + index,
                        "enteAppartenenza",
                        "fototessera",
                        "nascitaData",
                        "nascitaLuogo",
                        "nazionalita",
                        1,
                        1,
                        1,
                        1,
                        "sesso",
                        "telCellulare",
                        "telUfficio",
                        "codiceFisacle" + index,
                        index == 1,
                        1,
                        "numeroCarta" + index,
                        1,
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        1,
                        123,
                        "cro-123",
                        1,
                        20,
                        "01/03/2023",
                        2,
                        321,
                        "cro-321",
                        2,
                        20,
                        1,
                        "dataMandatoPVC"
                )).collect(Collectors.toList());
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> nucleoEsterno2 =
                IntStream.range(1,
                        8).mapToObj(index -> new CartaEsercitoPerSoggettoPerNucleoDTOView(3,
                        index % 2 == 0 ? tipoIstanzaNucleoEsterno : tipoIstanzaAggiuntaNucleoEsterno,
                        "nome" + index,
                        "cognome" + index,
                        "email " + index,
                        "enteAppartenenza",
                        "fototessera",
                        "nascitaData",
                        "nascitaLuogo",
                        "nazionalita",
                        1,
                        1,
                        1,
                        1,
                        "sesso",
                        "telCellulare",
                        "telUfficio",
                        "codiceFisacle" + index,
                        index == 1,
                        1,
                        "numeroCarta" + index,
                        1,
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        1,
                        123,
                        "cro-123",
                        1,
                        20,
                        "01/03/2023",
                        2,
                        321,
                        "cro-321",
                        2,
                        20,
                        1,
                        "dataMandatoPVC"
                )).collect(Collectors.toList());
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> list = new ArrayList<>(nucleoPrincipale);
        list.addAll(nucleoEsterno1);
        list.addAll(nucleoEsterno2);
        Map<CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> tipoNucleoListMap = CartaEsercitoPerSoggettoPerNucleoDTOView.groupByTipoNucleo(list, tipiIstanzaNucleoPrincipale, tipiIstanzaNucleoEsterno);

        assertEquals(nucleoPrincipale.size(), tipoNucleoListMap.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Principale).size());
        assertEquals(nucleoEsterno1.size() + nucleoEsterno2.size(), tipoNucleoListMap.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Esterno).size());

        tipoNucleoListMap.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Principale).forEach(cartaEsercitoPerSoggettoPerNucleoDTOView -> assertEquals(1, (int) cartaEsercitoPerSoggettoPerNucleoDTOView.getRifNucleo()));
        tipoNucleoListMap.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Esterno).forEach(cartaEsercitoPerSoggettoPerNucleoDTOView -> assertTrue(Set.of(2, 3).contains(cartaEsercitoPerSoggettoPerNucleoDTOView.getRifNucleo())));
    }


    @Test
    public void convertiInSoggetto() {
        EasyRandom easyRandom = new EasyRandom();
        CartaEsercitoPerSoggettoPerNucleoDTOView daConvertire = easyRandom.nextObject(CartaEsercitoPerSoggettoPerNucleoDTOView.class);
        ModuloDTO.Soggetto convertito = CartaEsercitoPerSoggettoPerNucleoDTOView.converti(daConvertire);

        assertEquals(daConvertire.getNome(), convertito.getNome());
        assertEquals(daConvertire.getCognome(), convertito.getCognome());
        assertEquals(daConvertire.getEmail(), convertito.getEmail());
        assertEquals(daConvertire.getEnteAppartenenza(), convertito.getEnteAppartenenza());
        assertEquals(daConvertire.getFototessera(), convertito.getFototessera());
        assertEquals(daConvertire.getNascitaData(), convertito.getNascitaData());
        assertEquals(daConvertire.getNascitaLuogo(), convertito.getNascitaLuogo());
        assertEquals(daConvertire.getNazionalita(), convertito.getNazionalita());
        assertEquals(daConvertire.getRifAmministrazione(), convertito.getRifAmministrazione());
        assertEquals(daConvertire.getRifGradoQualifica(), convertito.getRifGradoQualifica());
        assertEquals(daConvertire.getRifPosizione(), convertito.getRifPosizione());
        assertEquals(daConvertire.getRifRapporto(), convertito.getRifRapporto());
        assertEquals(daConvertire.getSesso(), convertito.getSesso());
        assertEquals(daConvertire.getTelCellulare(), convertito.getTelCellulare());
        assertEquals(daConvertire.getTelUfficio(), convertito.getTelUfficio());
        assertEquals(daConvertire.getIsSponsor(), convertito.getIsSponsor());
        assertEquals(daConvertire.getRifStato(), convertito.getStato());
        //assertEquals(,convertito.getResidenza());
        assertEquals(daConvertire.getNumeroCarta(), convertito.getCartaEsercito().getNumero());
        assertEquals(daConvertire.getDataRilascioCarta(), convertito.getCartaEsercito().getDataRilascio());
        assertEquals(daConvertire.getDataScadenzaCarta(), convertito.getCartaEsercito().getDataScadenza());
        assertEquals(daConvertire.getRifStatoCarta(), convertito.getCartaEsercito().getStato());
    }


    @Test
    public void convertiInModuloDTO() {
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> nucleoPrincipale =
                IntStream.range(1,
                        5).mapToObj(index -> new CartaEsercitoPerSoggettoPerNucleoDTOView(1,
                        index % 2 == 0 ? tipoIstanzaNucleoPrincipale : tipoIstanzaAggiuntaNucleoPrincipale,
                        "nome" + index,
                        "cognome" + index,
                        "email " + index,
                        "enteAppartenenza",
                        "fototessera",
                        "nascitaData",
                        "nascitaLuogo",
                        "nazionalita",
                        1,
                        1,
                        1,
                        1,
                        "sesso",
                        "telCellulare",
                        "telUfficio",
                        "codiceFisacle" + index,
                        index == 1,
                        1,
                        "numeroCarta" + index,
                        1,
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        1,
                        123,
                        "cro-123",
                        1,
                        20,
                        "01/03/2023",
                        3,
                        321,
                        "cro-321",
                        3,
                        20,
                        1,
                        "dataMandatoPVC"
                )).collect(Collectors.toList());
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> nucleoEsterno1 =
                IntStream.range(1,
                        8).mapToObj(index -> new CartaEsercitoPerSoggettoPerNucleoDTOView(2,
                        index % 2 == 0 ? tipoIstanzaNucleoEsterno : tipoIstanzaAggiuntaNucleoEsterno,
                        "nome" + index,
                        "cognome" + index,
                        "email " + index,
                        "enteAppartenenza",
                        "fototessera",
                        "nascitaData",
                        "nascitaLuogo",
                        "nazionalita",
                        1,
                        1,
                        1,
                        1,
                        "sesso",
                        "telCellulare",
                        "telUfficio",
                        "codiceFisacle" + index,
                        false,
                        1,
                        "numeroCarta" + index,
                        1,
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        1,
                        123,
                        "cro-123",
                        1,
                        20,
                        "01/03/2023",
                        4,
                        321,
                        "cro-321",
                        4,
                        20,
                        1,
                        "dataMandatoPVC"
                )).collect(Collectors.toList());
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> nucleoEsterno2 =
                IntStream.range(1,
                        8).mapToObj(index -> new CartaEsercitoPerSoggettoPerNucleoDTOView(3,
                        index % 2 == 0 ? tipoIstanzaNucleoEsterno : tipoIstanzaAggiuntaNucleoEsterno,
                        "nome" + index,
                        "cognome" + index,
                        "email " + index,
                        "enteAppartenenza",
                        "fototessera",
                        "nascitaData",
                        "nascitaLuogo",
                        "nazionalita",
                        1,
                        1,
                        1,
                        1,
                        "sesso",
                        "telCellulare",
                        "telUfficio",
                        "codiceFisacle" + index,
                        false,
                        1,
                        "numeroCarta" + index,
                        1,
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        1,
                        123,
                        "cro-123",
                        1,
                        20,
                        "01/03/2023",
                        5,
                        321,
                        "cro-321",
                        5,
                        20,
                        1,
                        "dataMandatoPVC"
                )).collect(Collectors.toList());
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> list = new ArrayList<>(nucleoPrincipale);
        list.addAll(nucleoEsterno1);
        list.addAll(nucleoEsterno2);
        Map<CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> daConvertire = CartaEsercitoPerSoggettoPerNucleoDTOView.groupByTipoNucleo(list, tipiIstanzaNucleoPrincipale, tipiIstanzaNucleoEsterno);

        ModuloDTO convertito = CartaEsercitoPerSoggettoPerNucleoDTOView.converti(daConvertire);
        assertEquals(convertito.getSponsor().getCodiceFiscale(), nucleoPrincipale.get(0).getCodiceFiscale());
        assertEquals(convertito.getNucleoPrincipale().getId(), nucleoPrincipale.get(0).getRifNucleo());
        assertEquals(convertito.getNucleoPrincipale().getComponenti().size(), nucleoPrincipale.size() - 1);

        Map<Integer, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> nucleiEsterniDaConvertirePerIdNucleo = daConvertire.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Esterno).stream().collect(Collectors.groupingBy(cartaEsercitoPerSoggettoPerNucleoDTOView -> cartaEsercitoPerSoggettoPerNucleoDTOView.getRifNucleo()));

        convertito.getNucleiEsterni().forEach(nucleoEsternoConvertito -> {
            assertTrue(nucleiEsterniDaConvertirePerIdNucleo.containsKey(nucleoEsternoConvertito.getId()));
            List<CartaEsercitoPerSoggettoPerNucleoDTOView> nucleoEsternoDaConvertire = nucleiEsterniDaConvertirePerIdNucleo.get(nucleoEsternoConvertito.getId());
            assertEquals(nucleoEsternoDaConvertire.size(), nucleoEsternoConvertito.getComponenti().size());
            assertEquals(nucleoEsternoDaConvertire.get(0).getRifNucleo(), nucleoEsternoConvertito.getId());
        });


    }

}
