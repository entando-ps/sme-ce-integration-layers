select *
from tabsoggetto
         join tabresidenze on tabsoggetto.idSoggetto = tabresidenze.rif_soggetto;

select *
from tabistanza
         join tabsoggetto on tabistanza.id_sponsor = tabsoggetto.idSoggetto;

select *
from tabsoggettiistanze
         join tabsoggetto on tabsoggettiistanze.rif_soggetto = tabsoggetto.idSoggetto
         join tabistanza on tabsoggettiistanze.rif_istanza = tabistanza.idIstanza;

select *
from tabnucleifull
         join tabsoggetto on tabnucleifull.rif_soggetto = tabsoggetto.idSoggetto
         join tabistanza on tabnucleifull.rif_istanza = tabistanza.idIstanza;

select tabmandato.*
from tabmandato
         join tabnucleifull on tabmandato.rif_nucleofull = tabnucleifull.id
         join tabsoggetto on tabmandato.rif_sponsor = tabsoggetto.idSoggetto;

select *
from tabMandatoPVC
         join tabnucleifull on tabMandatoPVC.rifNucleoFull = tabnucleifull.id
         join tabsoggetto on tabMandatoPVC.rifSponsor = tabsoggetto.idSoggetto;

-- attenzione il bonifico contiene la cifra unica di entrambi

select idSoggetto
from tabsoggetto
where codiceFiscale in
      ('GDFGMN70D16H501T', 'GDLGMN70D16H501R', 'GDLGMN70D18H501F', 'GDLGMN70D18H501H', 'GDFGMN70D16H501R',
       'GDFGMN70D18H501F', 'GDFGMN70D18H501H');




-- recupero dell'ultima istanza nucleo principale (1 iniziale, 3 modificata)
select idIstanza
from tabistanza
where id_sponsor = (select idSoggetto from tabsoggetto where codiceFiscale = 'GDFGMN70D16H501T')
  and rif_tipoIstanza in (1, 3)
ORDER BY idIstanza DESC
LIMIT 1;

-- recupero dell'ultima istanza nucleo esterno (4 iniziale, 5 modificata)
select idIstanza
from tabistanza
where id_sponsor = (select idSoggetto from tabsoggetto where codiceFiscale = 'GDFGMN70D16H501T')
  and rif_tipoIstanza in (4, 5)
ORDER BY idIstanza DESC
LIMIT 1;

-- recupero dei facenti parte il nucleo principale
select *
from tabnucleifull
         join tabsoggetto on tabnucleifull.rif_soggetto = tabsoggetto.idSoggetto
where rif_istanza = (select idIstanza
                     from tabistanza
                     where id_sponsor = (select idSoggetto from tabsoggetto where codiceFiscale = 'GDFGMN70D16H501T')
                       and rif_tipoIstanza in (1, 3)
                     ORDER BY idIstanza DESC
                     LIMIT 1);

-- recupero dei facenti parte il nucleo esterno
select *
from tabnucleifull
where rif_istanza = (select idIstanza
                     from tabistanza
                     where id_sponsor = (select idSoggetto from tabsoggetto where codiceFiscale = 'GDFGMN70D16H501T')
                       and rif_tipoIstanza in (4, 5)
                     ORDER BY idIstanza DESC
                     LIMIT 1);


/*
1,In corso di validità
2,Scaduta
3,Sospesa
4,Revocata
5,Smarrita
6,Deteriorata
7,In scadenza
8,Requisiti persi
*/

-- solo per test non valida in prod
INSERT INTO cartaesercito.tabcartaesercito (rif_soggetto, numeroCarta, dataRilascioCarta,
                                            dataScadenzaCarta, rif_statoCarta, email_rinnovo_inviata, motivazioneRevoca,
                                            rif_mandato, dataAggiornamento, dataProduzioneCarta, emessoPVC,
                                            dataEmissionePVC, dataRevocaPVC, rif_nucleofull, uid, stampato, inviato,
                                            letteraAccompagnamento, provvisorio, dataStampaPVC, dataInvioPROV,
                                            controllo)
VALUES ((select idSoggetto from tabsoggetto where codiceFiscale in ('GDFGMN70D16H501T')), 'numeroCarta', '2022-09-28',
        '2022-09-28', 1, 1, 'motivazioneRevoca', 1, '2022-09-28 16:09:12',
        'dataProduzioneCarta', 1, 'dataEmissionePvc', 'dataRevocaPvc', (select tabnucleifull.id
                                                                        from tabnucleifull
                                                                                 join tabsoggetto on tabnucleifull.rif_soggetto = tabsoggetto.idSoggetto
                                                                        where tabsoggetto.codiceFiscale in ('GDFGMN70D16H501T')),
        '1', 1, 1, 1, 1, 'dataStampaPVC',
        'dataInvioPvc', 1);


-- carte emesse per istanza
select *
from tabcartaesercito
         join tabnucleifull on tabcartaesercito.rif_soggetto = tabnucleifull.rif_soggetto
where tabnucleifull.rif_istanza = 46;

-- carte emesse per soggetto
select *
from tabcartaesercito
         join tabsoggetto on tabcartaesercito.rif_soggetto = tabsoggetto.idSoggetto
where tabsoggetto.codiceFiscale = 'GDFGMN70D16H501T';


-- carte per nucleo familiare principale
select *
from tabcartaesercito
         join tabnucleifull on tabcartaesercito.rif_nucleofull = tabnucleifull.id
         join tabistanza on tabnucleifull.rif_istanza = tabistanza.idIstanza
         join tabsoggetto on tabcartaesercito.rif_soggetto = tabsoggetto.idSoggetto
where tabistanza.idIstanza = (select idIstanza
                              from tabistanza
                              where id_sponsor =
                                    (select idSoggetto from tabsoggetto where codiceFiscale = 'GDFGMN70D16H501T')
                                and rif_tipoIstanza in (1, 3)
                              ORDER BY idIstanza DESC
                              LIMIT 1);


-- carte per nucleo familiare esterno
select *
from tabcartaesercito
         join tabnucleifull on tabcartaesercito.rif_nucleofull = tabnucleifull.id
         join tabistanza on tabnucleifull.rif_istanza = tabistanza.idIstanza
where tabistanza.idIstanza = (select idIstanza
                              from tabistanza
                              where id_sponsor =
                                    (select idSoggetto from tabsoggetto where codiceFiscale = 'GDFGMN70D16H501T')
                                and rif_tipoIstanza in (4, 5)
                              ORDER BY idIstanza DESC
                              LIMIT 1);


select * from

