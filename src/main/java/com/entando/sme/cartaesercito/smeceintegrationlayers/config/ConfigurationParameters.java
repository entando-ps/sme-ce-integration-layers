package com.entando.sme.cartaesercito.smeceintegrationlayers.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@ConfigurationProperties("app.bo-protocol-config")
@Data
@Slf4j
public class ConfigurationParameters {
    //soggetto
    private String soggettoRifStato;

    //istanza
    /*
    1,Nucleo familiare principale
    2,Rinnovo Carta Esercito
    3,Richiesta integrativa per il proprio nucleo Familiare
    4,Nucleo familiare esterno
    5,Richiesta integrativa Nucleo Familiare Esterno
     */
    private Integer istanzaRifCanale;
    private Integer istanzaRifOperatore;
    private Integer istanzaRifStatoIstanza;

    private Integer instanzaNucleoPrincipale;
    private Integer instanzaItegrNucleoPrincipale;
    private Integer instanzaNucleoEsterno;
    private Integer instanzaIntegrNucleoEsterno;
    private Integer instanzaRinnovoCE;

    private String mandatoRifOperatore;
    private String mandatoRifOperatorePagamento;
    private String mandatoRifStatoMandato;
    private String mandatoRifTipoPagamento;

    @PostConstruct
    public void init(){
        log.info("Current SmeCEBOJdbcProtocolConfigurationParameters {}",this);
    }


}
