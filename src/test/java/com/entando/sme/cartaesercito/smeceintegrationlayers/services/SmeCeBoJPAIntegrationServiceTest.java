package com.entando.sme.cartaesercito.smeceintegrationlayers.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class SmeCeBoJPAIntegrationServiceTest {

    String csvPath = "/Users/germano/projects/SME/sme-ce-integration-layers/files/";


    @Autowired
    SmeCeBoJPAIntegrationService smeCeBoJPAIntegrationService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void istanzaNucleoPrincipaleConNuoviSoggetti() {
        smeCeBoJPAIntegrationService.IstanzaConNuoviSoggetti(csvPath);

    }
}
