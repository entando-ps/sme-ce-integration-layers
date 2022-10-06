package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTODopoSME;
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

    public static ModuloDTODopoSME.Soggetto converti(CartaEsercitoPerSoggettoPerNucleoDTOView daConvertire){
        return new ModuloDTODopoSME.Soggetto(
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
                new ModuloDTODopoSME.CartaEsercito(daConvertire.getRifStatoCarta(), daConvertire.getNumeroCarta(), daConvertire.getDataRilascioCarta(), daConvertire.getDataScadenzaCarta()),
                daConvertire.getRifStato());
    }


    public static List<ModuloDTODopoSME.Nucleo> estraiNucleiEsterni(Map<CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> daConvertire) {
        Map<Integer, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> nucleiEsterniPerId = daConvertire.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Esterno).stream().collect(Collectors.groupingBy(CartaEsercitoPerSoggettoPerNucleoDTOView::getRifNucleo));

        return nucleiEsterniPerId.keySet().stream().map(rifNucleoId -> {
            List<ModuloDTODopoSME.Soggetto> componentiNucleo = convertiViewInListaSoggetti(nucleiEsterniPerId.get(rifNucleoId));
            ModuloDTODopoSME.Nucleo nucleoEsterno = new ModuloDTODopoSME.Nucleo(componentiNucleo);
            nucleoEsterno.setId(rifNucleoId);
            return nucleoEsterno;
        }).collect(Collectors.toList());
    }


    public static Pair<ModuloDTODopoSME.Sponsor, ModuloDTODopoSME.Nucleo> estraiSponsorENucleoPrincipale(Map<CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> daConvertire) {
        List<CartaEsercitoPerSoggettoPerNucleoDTOView> nucleoPrincipaleConSponsorView = daConvertire.get(CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo.Principale);
        Integer rifNucleoPrincipale = nucleoPrincipaleConSponsorView.get(0).getRifNucleo();
        List<ModuloDTODopoSME.Soggetto> componentiNucleoPrincipaleConSponsor = convertiViewInListaSoggetti(nucleoPrincipaleConSponsorView);
        Optional<ModuloDTODopoSME.Soggetto> soggettoOptional = componentiNucleoPrincipaleConSponsor.stream().filter(ModuloDTODopoSME.Soggetto::getIsSponsor).findAny();
        if (soggettoOptional.isEmpty()) {
            throw new RuntimeException("Il nucleo principale con rifNucleo " + rifNucleoPrincipale + " non contiene uno sponsor");
        }
        ModuloDTODopoSME.Sponsor sponsor = new ModuloDTODopoSME.Sponsor(soggettoOptional.get());
        ModuloDTODopoSME.Nucleo nucleoPrincipale = new ModuloDTODopoSME.Nucleo(componentiNucleoPrincipaleConSponsor.stream().filter(soggetto -> !soggetto.getIsSponsor()).collect(Collectors.toList()));
        nucleoPrincipale.setId(rifNucleoPrincipale);
        return Pair.of(sponsor, nucleoPrincipale);
    }

    public static List<ModuloDTODopoSME.Soggetto> convertiViewInListaSoggetti(List<CartaEsercitoPerSoggettoPerNucleoDTOView> view) {
        return view.stream().map(CartaEsercitoPerSoggettoPerNucleoDTOView::converti).collect(Collectors.toList());
    }
    public static ModuloDTODopoSME converti(Map<CartaEsercitoPerSoggettoPerNucleoDTOView.TipoNucleo, List<CartaEsercitoPerSoggettoPerNucleoDTOView>> daConvertire) {
        ModuloDTODopoSME moduloDTODopoSME = new ModuloDTODopoSME();

        Pair<ModuloDTODopoSME.Sponsor, ModuloDTODopoSME.Nucleo> sponsorEnucleoPrincipale = estraiSponsorENucleoPrincipale(daConvertire);
        moduloDTODopoSME.setSponsor(sponsorEnucleoPrincipale.getFirst());
        moduloDTODopoSME.setNucleoPrincipale(sponsorEnucleoPrincipale.getSecond());

        List<ModuloDTODopoSME.Nucleo> nucleiEsterni = estraiNucleiEsterni(daConvertire);
        moduloDTODopoSME.setNucleiEsterni(nucleiEsterni);

        //TODO da capire
        moduloDTODopoSME.setPagamento(null);
        moduloDTODopoSME.setResidenzaDiSpedizione(null);
        return moduloDTODopoSME;
    }

}

