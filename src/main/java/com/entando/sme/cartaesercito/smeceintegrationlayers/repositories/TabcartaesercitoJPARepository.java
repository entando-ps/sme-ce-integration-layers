package com.entando.sme.cartaesercito.smeceintegrationlayers.repositories;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabcartaesercito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TabcartaesercitoJPARepository extends JpaRepository<Tabcartaesercito, Integer>{

    @Query(value = "select * from tabcartaesercito join tabsoggetto on tabcartaesercito.rif_soggetto = tabsoggetto.idSoggetto where tabsoggetto.codiceFiscale = :codiceFiscaleSoggetto",nativeQuery = true)
    Tabcartaesercito findByCodiceFiscaleSoggetto(String codiceFiscaleSoggetto);


}
