package com.entando.sme.cartaesercito.smeceintegrationlayers.repositories;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabtipogradoqualifica;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TabtipogradoqualificaJPARepository extends JpaRepository<Tabtipogradoqualifica, Integer>{
	List<Tabtipogradoqualifica> findBySiglaGradoQualifica(String grado);    

}
