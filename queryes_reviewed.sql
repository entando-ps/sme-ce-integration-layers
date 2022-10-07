-- nuclei di uno sponsor
-- identificati da rif_nucleo
-- tipo istanza 1/3 -->nucleo principale
-- tipo istanza 4/5 -->nucleo esterno


/*
STATO ISTANZA
1,Pervenuta
2,In attesa di pagamento
3,Autorizzazione negata
4,Istanza revocata dal richiedente
5,Istanza sospesa
6,Istanza processata correttamente
*/

select tabnucleifull.rif_nucleo as rifNucleo,
       tabistanza.rif_tipoIstanza as rifTipoIstanza,
       tabsoggetto.nome,
       tabsoggetto.cognome,
       tabsoggetto.codiceFiscale,
       tabnucleifull.is_sponsor as isSponsor,
       tabistanza.rif_statoIstanza as rifStatoIstanza,
       tabcartaesercito.numeroCarta,
       tabcartaesercito.rif_statoCarta as rifStatoCarta,
       tabcartaesercito.dataScadenzaCarta,
       tabcartaesercito.dataRilascioCarta
from tabnucleifull
         join tabistanza on tabistanza.idIstanza = tabnucleifull.rif_istanza
         join tabsoggetto on tabsoggetto.idSoggetto = tabnucleifull.rif_soggetto
         left join tabcartaesercito on tabnucleifull.rif_soggetto = tabcartaesercito.rif_soggetto
where tabistanza.id_sponsor = (select tabsoggetto.idSoggetto from tabsoggetto where tabsoggetto.codiceFiscale = 'testSp')
  -- and tabistanza.rif_tipoIstanza in (1, 3) -- nucleo principale
  and tabistanza.rif_tipoIstanza in (4, 5); -- nucleo esterno

