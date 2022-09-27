package com.entando.sme.cartaesercito.smeceintegrationlayers;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.SmeCeBoJPAIntegrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class SmeCeIntegrationLayersJPAProtocolTests {

    String csvSponsor = "/Users/germano/projects/SME/sme-ce-integration-layers/files/sponsor.csv";
    String csvNucleoPrincipale = "/Users/germano/projects/SME/sme-ce-integration-layers/files/nucleoprincipale.csv";
    String csvResidenzaSponsor = "/Users/germano/projects/SME/sme-ce-integration-layers/files/residenzasponsor.csv";
    String csvResidenzeNucleoPrincipale = "/Users/germano/projects/SME/sme-ce-integration-layers/files/residenzaparentinucleoprincipale.csv";
    String csvIstanzaNucleoPrincipale = "/Users/germano/projects/SME/sme-ce-integration-layers/files/istanzanucleoprincipale.csv";
    String csvMandatoPagamento = "/Users/germano/projects/SME/sme-ce-integration-layers/files/mandatopagamento.csv";
    String csvMandatoPagamentoPVC = "/Users/germano/projects/SME/sme-ce-integration-layers/files/mandatopagamentopvc.csv";


    @Autowired
    SmeCeBoJPAIntegrationService smeCeBoJPAIntegrationService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void istanzaNucleoPrincipaleConNuoviSoggetti() {
        smeCeBoJPAIntegrationService.istanzaNucleoPrincipaleConNuoviSoggetti(csvSponsor, csvNucleoPrincipale, csvResidenzaSponsor, csvResidenzeNucleoPrincipale, csvIstanzaNucleoPrincipale, csvMandatoPagamento, csvMandatoPagamentoPVC);

    }


}
