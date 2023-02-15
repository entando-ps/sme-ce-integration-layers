package com.entando.sme.cartaesercito.smeceintegrationlayers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@Slf4j
@ConfigurationPropertiesScan
public class SmeCeIntegrationLayersApplication  {


    public static void main(String[] args) {
        SpringApplication.run(SmeCeIntegrationLayersApplication.class, args);
    }

}
