package com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.tipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo.Tabtiporapporto;


@Repository
public interface TabtiporapportoJPARepository extends JpaRepository<Tabtiporapporto, Integer>{
}