package com.entando.sme.cartaesercito.smeceintegrationlayers.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MandatoDTO {

        private Integer idMandato;
        private Integer rifStatoMandato;
        private Integer rifNucleoFull;
        private Integer quotaMandato; // qui non arriva in centesimi
        private String dataMandato; // varchar
        private String dataEmissione; // date
        private String dataScadenza; // date
        private Integer rifSponsor;
        private String nota;
        private String notaConvalida;
        private String notaAnnullamento;
        private String dataAggiornamento; // timestamp letta correttamente
        private Integer convalidaMandatoCovid;

        private String cro;

        public MandatoDTO(Integer idMandato, String cro, Integer rifStatoMandato, Integer quotaMandato, String dataMandato,Integer rifNucleoFull){
                this.idMandato = idMandato;
                this.cro = cro;
                this.rifStatoMandato = rifStatoMandato;
                this.quotaMandato = quotaMandato;
                this.dataMandato = dataMandato;
                this.rifNucleoFull = rifNucleoFull;
        }

}