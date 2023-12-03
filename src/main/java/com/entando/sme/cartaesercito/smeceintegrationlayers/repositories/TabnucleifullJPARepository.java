package com.entando.sme.cartaesercito.smeceintegrationlayers.repositories;

import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.Tabnucleifull;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TabnucleifullJPARepository extends JpaRepository<Tabnucleifull, Integer>{
	List<Tabnucleifull> findByRifNucleo(Integer rifNucleo);
}
