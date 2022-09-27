package com.entando.sme.cartaesercito.smeceintegrationlayers;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.SmeCeBoJPAIntegrationService;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class SmeCeIntegrationLayersJPAProtocolTests {

    String csvPath = "/Users/germano/projects/SME/sme-ce-integration-layers/files/";


    @Autowired
    SmeCeBoJPAIntegrationService smeCeBoJPAIntegrationService;

    @Test
    @Transactional
    @Rollback(value = false)
    @Disabled
    public void istanzaNucleoPrincipaleConNuoviSoggetti() {
        smeCeBoJPAIntegrationService.istanzaNucleoPrincipaleConNuoviSoggetti(csvPath);

    }
}
