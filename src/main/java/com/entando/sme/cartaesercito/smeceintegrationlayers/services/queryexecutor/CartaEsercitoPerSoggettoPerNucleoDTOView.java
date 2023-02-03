package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Data
@AllArgsConstructor
public class CartaEsercitoPerSoggettoPerNucleoDTOView {
    private Integer rifNucleo;
    private Integer rifTipoIstanza; //1,3 nucleo principale 4,5 nucleo secondario
    private String nome;
    private String cognome;
    private String email;
    private String enteAppartenenza;
    private String fototessera;
    private String nascitaData;
    private String nascitaLuogo;
    private String nazionalita;
    private Integer rifGradoQualifica;
    private Integer rifPosizione;
    private Integer rifRapporto;
    private String sesso;
    private String telCellulare;
    private String telUfficio;

    private String codiceFiscale;
    private Boolean isSponsor;
    private Integer rifStatoIstanza;
    private String numeroCarta;
    private Integer rifStatoCarta;
    private Date dataScadenzaCarta;
    private Date dataRilascioCarta;
    private Integer rifStato;


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

    public static ModuloDTO.Soggetto converti(CartaEsercitoPerSoggettoPerNucleoDTOView daConvertire){
        return new ModuloDTO.Soggetto(
                daConvertire.getCodiceFiscale(),
                daConvertire.getNome(),
                daConvertire.getCognome(),
                daConvertire.getEmail(),
                daConvertire.getEnteAppartenenza(),
                daConvertire.getFototessera(),
                daConvertire.getNascitaData(),
                daConvertire.getNascitaLuogo(),
                daConvertire.getNazionalita(),
                daConvertire.getRifGradoQualifica(),
                daConvertire.getRifPosizione(),
                daConvertire.getRifRapporto(),
                daConvertire.getSesso(),
                daConvertire.getTelCellulare(),
                daConvertire.getTelUfficio(),
                daConvertire.getIsSponsor(),
                null,
                new ModuloDTO.CartaEsercito(daConvertire.getRifStatoCarta(), daConvertire.getNumeroCarta(), daConvertire.getDataRilascioCarta(), daConvertire.getDataScadenzaCarta()),
                daConvertire.getRifStato());
    }


    public static List<ModuloDTO.Nucleo> estraiNucleiEsterni(Map<CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> daConvertire) {
        Map<Integer, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> nucleiEsterniPerId = daConvertire.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Esterno).stream().collect(Collectors.groupingBy(CartaEsercitoPerSoggettoPerNucleoDTOView::getRifNucleo));

        return nucleiEsterniPerId.keySet().stream().map(rifNucleoId -> {
            List<ModuloDTO.Soggetto> componentiNucleo = convertiViewInListaSoggetti(nucleiEsterniPerId.get(rifNucleoId));
            ModuloDTO.Nucleo nucleoEsterno = new ModuloDTO.Nucleo(componentiNucleo);
            nucleoEsterno.setId(rifNucleoId);
            return nucleoEsterno;
        }).collect(Collectors.toList());
    }


    public static Pair<ModuloDTO.Sponsor, ModuloDTO.Nucleo> estraiSponsorENucleoPrincipale(Map<CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> daConvertire) {
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> nucleoPrincipaleConSponsorView = daConvertire.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Principale);
        Integer rifNucleoPrincipale = nucleoPrincipaleConSponsorView.get(0).getRifNucleo();
        List<ModuloDTO.Soggetto> componentiNucleoPrincipaleConSponsor = convertiViewInListaSoggetti(nucleoPrincipaleConSponsorView);
        Optional<ModuloDTO.Soggetto> soggettoOptional = componentiNucleoPrincipaleConSponsor.stream().filter(ModuloDTO.Soggetto::getIsSponsor).findAny();
        if (soggettoOptional.isEmpty()) {
            throw new RuntimeException("Il nucleo principale con rifNucleo " + rifNucleoPrincipale + " non contiene uno sponsor");
        }
        ModuloDTO.Sponsor sponsor = new ModuloDTO.Sponsor(soggettoOptional.get());
        ModuloDTO.Nucleo nucleoPrincipale = new ModuloDTO.Nucleo(componentiNucleoPrincipaleConSponsor.stream().filter(soggetto -> !soggetto.getIsSponsor()).collect(Collectors.toList()));
        nucleoPrincipale.setId(rifNucleoPrincipale);
        return Pair.of(sponsor, nucleoPrincipale);
    }

    public static List<ModuloDTO.Soggetto> convertiViewInListaSoggetti(List<CartaEsercitoPerSoggettoPerNucleoDTOView> view) {
        return view.stream().map(CartaEsercitoPerSoggettoPerNucleoDTOView::converti).collect(Collectors.toList());
    }
    public static ModuloDTO converti(Map<CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> daConvertire) {
        ModuloDTO moduloDTO = new ModuloDTO();

        Pair<ModuloDTO.Sponsor, ModuloDTO.Nucleo> sponsorEnucleoPrincipale = estraiSponsorENucleoPrincipale(daConvertire);
        moduloDTO.setSponsor(sponsorEnucleoPrincipale.getFirst());
        moduloDTO.setNucleoPrincipale(sponsorEnucleoPrincipale.getSecond());

        List<ModuloDTO.Nucleo> nucleiEsterni = estraiNucleiEsterni(daConvertire);
        // TODO da capire come inserire scelta unica spedizione per tutti
        // TODO da capire se arriva residenza spedizione query da aggiornare (forse)
//        nucleiEsterni.forEach(nucleo -> nucleo.setResidenzaDiSpedizione(null));
        moduloDTO.setNucleiEsterni(nucleiEsterni);

        //TODO da capire
        moduloDTO.setPagamento(null);
        // TODO da capire se arriva residenza query da aggiornare (forse)
        moduloDTO.getNucleoPrincipale().setResidenzaDiSpedizione(null);

        return moduloDTO;
    }

}

