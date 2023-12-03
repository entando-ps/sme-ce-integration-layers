package com.entando.sme.cartaesercito.smeceintegrationlayers.entities;

import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.ModuloDTO;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the tabtipogradoqualifica database table.
 * 
 * 
 * CREATE TABLE IF NOT EXISTS `tabtipogradoqualifica` (
  `idTipoGradoQualifica` int(3) NOT NULL AUTO_INCREMENT,
  `siglaGradoQualifica` varchar(60) DEFAULT NULL,
  `denominazioneGradoQualifica` varchar(100) DEFAULT NULL,
  `ordine` int(2) DEFAULT NULL,
  `id_forzaArmata` int(2) DEFAULT NULL,
  `visibile` tinyint(1) DEFAULT '1',
  `abbreviazioniGradi` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTipoGradoQualifica`)
)
 */
@Entity
@Table(name = "tabtipogradoqualifica")
@NamedQuery(name = "Tabtipogradoqualifica.findAll", query = "SELECT t FROM Tabtipogradoqualifica t")
public class Tabtipogradoqualifica implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoGradoQualifica;

    private String siglaGradoQualifica;

    private String denominazioneGradoQualifica;

    private Integer ordine;

    private Integer id_forzaArmata;

    private Boolean visibile;
    
    private String abbreviazioniGradi;

	public Tabtipogradoqualifica() {
		super();
	}

	public Integer getIdTipoGradoQualifica() {
		return idTipoGradoQualifica;
	}

	public void setIdTipoGradoQualifica(Integer idTipoGradoQualifica) {
		this.idTipoGradoQualifica = idTipoGradoQualifica;
	}

	public String getSiglaGradoQualifica() {
		return siglaGradoQualifica;
	}

	public void setSiglaGradoQualifica(String siglaGradoQualifica) {
		this.siglaGradoQualifica = siglaGradoQualifica;
	}

	public String getDenominazioneGradoQualifica() {
		return denominazioneGradoQualifica;
	}

	public void setDenominazioneGradoQualifica(String denominazioneGradoQualifica) {
		this.denominazioneGradoQualifica = denominazioneGradoQualifica;
	}

	public Integer getOrdine() {
		return ordine;
	}

	public void setOrdine(Integer ordine) {
		this.ordine = ordine;
	}

	public Integer getId_forzaArmata() {
		return id_forzaArmata;
	}

	public void setId_forzaArmata(Integer id_forzaArmata) {
		this.id_forzaArmata = id_forzaArmata;
	}

	public Boolean getVisibile() {
		return visibile;
	}

	public void setVisibile(Boolean visibile) {
		this.visibile = visibile;
	}

	public String getAbbreviazioniGradi() {
		return abbreviazioniGradi;
	}

	public void setAbbreviazioniGradi(String abbreviazioniGradi) {
		this.abbreviazioniGradi = abbreviazioniGradi;
	}
    

}
