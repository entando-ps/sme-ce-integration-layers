package com.entando.sme.cartaesercito.smeceintegrationlayers.services.queryexecutor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueryExecutorServiceDopoSMETest {

    @Autowired
    QueryExecutorServiceDopoSME queryExecutorService;

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
