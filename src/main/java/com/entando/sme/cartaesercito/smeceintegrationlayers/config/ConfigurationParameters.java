package com.entando.sme.cartaesercito.smeceintegrationlayers.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@ConfigurationProperties("smece.config")
@Data
@Slf4j
@AllArgsConstructor
public class ConfigurationParameters {

    private Soggetto soggetto;
    private Istanza istanza;
    private Mandato mandato;
    private Costi costi;
    private Query query;

    public ConfigurationParameters() {
    }

    @PostConstruct
    public void init() {
        log.info("Current configurationParameters {}", this);
    }

    @Data
    @AllArgsConstructor
    public static class Soggetto {
        //soggetto
        private String rifStato;

        public Soggetto() {
        }
    }

    @Data
    @AllArgsConstructor
    public static class Istanza {
        //istanza
        /*
        1,Nucleo familiare principale
        2,Rinnovo Carta Esercito
        3,Richiesta integrativa per il proprio nucleo Familiare
        4,Nucleo familiare esterno
        5,Richiesta integrativa Nucleo Familiare Esterno
         */
        private Integer rifCanale;
        private Integer rifOperatore;
        private Integer rifStatoIstanza;
        private Integer nucleoPrincipale;
        private Integer itegrNucleoPrincipale;
        private Integer nucleoEsterno;
        private Integer integrNucleoEsterno;

        public Istanza() {
        }
    }

    @Data
    @AllArgsConstructor
    public static class Mandato {
        //mandato
        private Integer rifOperatore;
        private Integer rifOperatorePagamento;
        private Integer rifStatoMandato;
        private Integer rifTipoPagamento;

        public Mandato() {
        }
    }


    @Data
    @AllArgsConstructor
    public static class Costi {
        private Integer perSponsor;
        private Integer perFamigliare;
        private Integer perOspite;
        private Integer spedizione;
        private Integer limiteNucleoFamigliarePrincipaleNoSponsor;

        public Costi() {
        }
    }

    @Data
    @AllArgsConstructor
    public static class Query{
        private String carteEsercitoPerSponsor;

        public Query() {
        }
    }

}
