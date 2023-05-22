package com.entando.sme.cartaesercito.smeceintegrationlayers.services;


import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo.Tabtipocanale;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo.Tabtipoistanza;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo.Tabtipopagamento;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo.Tabtiporapporto;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo.Tabtipostatocarta;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo.Tabtipostatomandato;
import com.entando.sme.cartaesercito.smeceintegrationlayers.entities.tipo.Tabtipostatosoggetto;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.tipo.TabtipocanaleJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.tipo.TabtipoistanzaJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.tipo.TabtipopagamentoJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.tipo.TabtiporapportoJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.tipo.TabtipostatocartaJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.tipo.TabtipostatomandatoJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.tipo.TabtipostatosoggettoJPARepository;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo.TabtipocanaleDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo.TabtipoistanzaDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo.TabtipopagamentoDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo.TabtiporapportoDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo.TabtipostatocartaDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo.TabtipostatomandatoDTO;
import com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto.tipo.TabtipostatosoggettoDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TipologicheService {

    final private TabtipocanaleJPARepository tabtipocanaleJPARepository;
    final private TabtipoistanzaJPARepository tabtipoistanzaJPARepository;
    final private TabtipopagamentoJPARepository tabtipopagamentoJPARepository;
    final private TabtipostatocartaJPARepository tabtipostatocartaJPARepository;
    final private TabtipostatomandatoJPARepository tabtipostatomandatoJPARepository;
    final private TabtipostatosoggettoJPARepository tabtipostatosoggettoJPARepository; 
    final private TabtiporapportoJPARepository tabtiporapportoJPARepository; 

    

    public TipologicheService(TabtipocanaleJPARepository tabtipocanaleJPARepository,
			TabtipoistanzaJPARepository tabtipoistanzaJPARepository,
			TabtipopagamentoJPARepository tabtipopagamentoJPARepository,
			TabtipostatocartaJPARepository tabtipostatocartaJPARepository,
			TabtipostatomandatoJPARepository tabtipostatomandatoJPARepository,
			TabtipostatosoggettoJPARepository tabtipostatosoggettoJPARepository, 
			TabtiporapportoJPARepository tabtiporapportoJPARepository) {
		super();
		this.tabtipocanaleJPARepository = tabtipocanaleJPARepository;
		this.tabtipoistanzaJPARepository = tabtipoistanzaJPARepository;
		this.tabtipopagamentoJPARepository = tabtipopagamentoJPARepository;
		this.tabtipostatocartaJPARepository = tabtipostatocartaJPARepository;
		this.tabtipostatomandatoJPARepository = tabtipostatomandatoJPARepository;
		this.tabtipostatosoggettoJPARepository = tabtipostatosoggettoJPARepository;
		this.tabtiporapportoJPARepository = tabtiporapportoJPARepository;

	}

    public TabtipocanaleDTO toDTO(Tabtipocanale tabtipocanale) {
    	return new TabtipocanaleDTO(tabtipocanale.getIdTipoCanale(), tabtipocanale.getDataAggiornamento(), tabtipocanale.getDenominazioneCanale());
    }
    public TabtipoistanzaDTO toDTO(Tabtipoistanza tabtipoistanza) {
    	return new TabtipoistanzaDTO(tabtipoistanza.getIdTipoIstanza(), tabtipoistanza.getDataAggiornamento(), tabtipoistanza.getDenominazioneTipoIstanza());
    }
    public TabtipopagamentoDTO toDTO(Tabtipopagamento tabtipopagamento) {
    	return new TabtipopagamentoDTO(tabtipopagamento.getIdTipoPagamento(),
    			tabtipopagamento.getDenominazionePagamento(),
    			tabtipopagamento.getRiscossoDaDitta(),
    			tabtipopagamento.getRiscossoDaSoggiorno(), 
    			tabtipopagamento.getVisibile());
    }
    public TabtipostatocartaDTO toDTO(Tabtipostatocarta tabtipostatocarta) {
    	return new TabtipostatocartaDTO(tabtipostatocarta.getIdtipostatocarta(), tabtipostatocarta.getDataAggiornamento(), tabtipostatocarta.getDescrizioneStatoCarta());
    }
    public TabtipostatomandatoDTO toDTO(Tabtipostatomandato tabtipostatomandato) {
    	return new TabtipostatomandatoDTO(tabtipostatomandato.getIdStatoMandato(), tabtipostatomandato.getDenominazioneMandato());
    }
    public TabtipostatosoggettoDTO toDTO(Tabtipostatosoggetto tabtipostatosoggetto) {
    	return new TabtipostatosoggettoDTO(tabtipostatosoggetto.getIdStatoSoggetto(), tabtipostatosoggetto.getDenominazioneStato());
    }
    public TabtiporapportoDTO toDTO(Tabtiporapporto source) {
    	return new TabtiporapportoDTO(source.getIdRapporto(), source.getDenominazioneRapporto());
    }
    public List<TabtipocanaleDTO>  getAllTabtipocanaleDTO(){
        log.info("recupero Tabtipocanale");
    	return tabtipocanaleJPARepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
    public List<TabtipoistanzaDTO>  getAllTabtipoistanzaDTO(){
        log.info("recupero TabtipoistanzaDTO");
    	return tabtipoistanzaJPARepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
    public List<TabtipopagamentoDTO>  getAllTabtipopagamentoDTO(){
        log.info("recupero TabtipopagamentoDTO");
    	return tabtipopagamentoJPARepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
    public List<TabtipostatocartaDTO>  getAllTabtipostatocartaDTO(){
        log.info("recupero TabtipostatocartaDTO");
    	return tabtipostatocartaJPARepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
    public List<TabtipostatomandatoDTO>  getAllTabtipostatomandatoDTO(){
        log.info("recupero TabtipostatomandatoDTO");
    	return tabtipostatomandatoJPARepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
    public List<TabtipostatosoggettoDTO>  getAllTabtipostatosoggettoDTO(){
        log.info("recupero TabtipostatosoggettoDTO");
    	return tabtipostatosoggettoJPARepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    public List<TabtiporapportoDTO>  getAllTabtiporapportoDTO(){
        log.info("recupero TabtiporapportoDTO");
    	return tabtiporapportoJPARepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }	
}
