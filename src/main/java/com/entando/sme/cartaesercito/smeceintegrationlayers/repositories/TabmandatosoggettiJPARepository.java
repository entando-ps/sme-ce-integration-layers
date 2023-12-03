package com.entando.sme.cartaesercito.smeceintegrationlayers.repositories;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabmandato;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabmandatosoggetti;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TabmandatosoggettiJPARepository extends JpaRepository<Tabmandatosoggetti, Integer>{
}
