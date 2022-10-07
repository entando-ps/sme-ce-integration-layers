package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QueryExecutorServiceIntegrationTest {

    @Autowired
    QueryExecutorService queryExecutorService;

    @Test
    void getCarteEsercitoPerSponsor() {
        queryExecutorService.getCarteEsercitoPerSponsor("");
    }

    @Test
    void getNextRifNucleo() {
        Integer nextRifNucleo = queryExecutorService.getNextRifNucleo();
        System.out.println(nextRifNucleo);
    }
}
