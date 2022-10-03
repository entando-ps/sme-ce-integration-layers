package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabcartaesercito;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabsoggetto;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.TabcartaesercitoJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.TabsoggettoJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TabcartaesercitoJPARepositoryTest {


    @Autowired
    TabcartaesercitoJPARepository tabcartaesercitoJPARepository;

    @Autowired
    TabsoggettoJPARepository tabsoggettoJPARepository;

    @Test
    public void cartaEsercitoPerSingoloSoggetto() {
        Tabcartaesercito cartaEsercitoPerSoggetto = tabcartaesercitoJPARepository.findByCodiceFiscaleSoggetto("GDFGMN70wD16H501T");
        System.out.println("cartaEsercitoPerSoggetto = " + cartaEsercitoPerSoggetto);
    }

    @Test
    public void singoloSoggetto() {
        Tabsoggetto soggetto = tabsoggettoJPARepository.findByCodiceFiscale("eOMtThyhVNLWUZNRcBaQK");
        System.out.println("soggetto = " + soggetto);
    }



}
