package com.entando.sme.cartaesercito.smeceintegrationlayers;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabcartaesercito;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.TabcartaesercitoJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.projections.SoggettoNucleoFamiliareDTOView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SmeCeIntegrationLayersQueryTests {


    @Autowired
    TabcartaesercitoJPARepository tabcartaesercitoJPARepository;


    @Test
    public void cartaEsercitoPerSingoloSoggetto() {
        Tabcartaesercito cartaEsercitoPerSoggetto = tabcartaesercitoJPARepository.findByCodiceFiscaleSoggetto("GDFGMN70D16H501T");
        System.out.println("cartaEsercitoPerSoggetto = " + cartaEsercitoPerSoggetto);
    }



}
