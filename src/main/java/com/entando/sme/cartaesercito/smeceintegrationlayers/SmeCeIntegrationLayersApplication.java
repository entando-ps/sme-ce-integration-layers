package com.entando.sme.cartaesercito.smeceintegrationlayers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@Slf4j
@ConfigurationPropertiesScan
public class SmeCeIntegrationLayersApplication implements CommandLineRunner {

    @Value("${app.csv.folder}")
    String csvPath;

    public static void main(String[] args) {
        SpringApplication.run(SmeCeIntegrationLayersApplication.class, args);

    }

    @Override
    public void run(String... args) {
        log.info("csv folder path: {}",csvPath);
        //smeCeBoJPAIntegrationService.IstanzaConNuoviSoggetti(csvPath);
    }
}
