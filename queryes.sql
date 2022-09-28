select * from tabsoggetto join tabresidenze on tabsoggetto.idSoggetto=tabresidenze.rif_soggetto;

select * from tabistanza join tabsoggetto on tabistanza.id_sponsor=tabsoggetto.idSoggetto;

select * from tabsoggettiistanze
    join tabsoggetto on tabsoggettiistanze.rif_soggetto=tabsoggetto.idSoggetto
    join tabistanza on tabsoggettiistanze.rif_istanza=tabistanza.idIstanza;

select * from tabnucleifull
    join tabsoggetto on tabnucleifull.rif_soggetto=tabsoggetto.idSoggetto
    join tabistanza on tabnucleifull.rif_istanza=tabistanza.idIstanza;

select tabmandato.* from tabmandato
    join tabnucleifull on tabmandato.rif_nucleofull=tabnucleifull.id
    join tabsoggetto on tabmandato.rif_sponsor=tabsoggetto.idSoggetto;

select * from tabMandatoPVC
    join tabnucleifull on tabMandatoPVC.rifNucleoFull=tabnucleifull.id
    join tabsoggetto on tabMandatoPVC.rifSponsor=tabsoggetto.idSoggetto;

-- attenzione il bonifico contiene la cifra unica di entrambi

select idSoggetto from tabsoggetto where codiceFiscale in ('GDFGMN70D16H501T','GDLGMN70D16H501R','GDLGMN70D18H501F','GDLGMN70D18H501H','GDFGMN70D16H501R','GDFGMN70D18H501F','GDFGMN70D18H501H');




delete from tabresidenze where rif_soggetto in (select idSoggetto from tabsoggetto where codiceFiscale in ('GDFGMN70D16H501T','GDLGMN70D16H501R','GDLGMN70D18H501F','GDLGMN70D18H501H','GDFGMN70D16H501R','GDFGMN70D18H501F','GDFGMN70D18H501H'));
delete from tabistanza where id_sponsor in (select idSoggetto from tabsoggetto where codiceFiscale in ('GDFGMN70D16H501T','GDLGMN70D16H501R','GDLGMN70D18H501F','GDLGMN70D18H501H','GDFGMN70D16H501R','GDFGMN70D18H501F','GDFGMN70D18H501H'));
delete from tabsoggettiistanze where rif_soggetto in (select idSoggetto from tabsoggetto where codiceFiscale in ('GDFGMN70D16H501T','GDLGMN70D16H501R','GDLGMN70D18H501F','GDLGMN70D18H501H','GDFGMN70D16H501R','GDFGMN70D18H501F','GDFGMN70D18H501H'));
delete from tabnucleifull where rif_soggetto in (select idSoggetto from tabsoggetto where codiceFiscale in ('GDFGMN70D16H501T','GDLGMN70D16H501R','GDLGMN70D18H501F','GDLGMN70D18H501H','GDFGMN70D16H501R','GDFGMN70D18H501F','GDFGMN70D18H501H'));
delete from tabmandato where rif_sponsor in (select idSoggetto from tabsoggetto where codiceFiscale in ('GDFGMN70D16H501T','GDLGMN70D16H501R','GDLGMN70D18H501F','GDLGMN70D18H501H','GDFGMN70D16H501R','GDFGMN70D18H501F','GDFGMN70D18H501H'));;
delete from tabMandatoPVC where rifSponsor in (select idSoggetto from tabsoggetto where codiceFiscale in ('GDFGMN70D16H501T','GDLGMN70D16H501R','GDLGMN70D18H501F','GDLGMN70D18H501H','GDFGMN70D16H501R','GDFGMN70D18H501F','GDFGMN70D18H501H'));;


delete from tabsoggetto where codiceFiscale in  ('GDFGMN70D16H501T','GDLGMN70D16H501R','GDLGMN70D18H501F','GDLGMN70D18H501H','GDFGMN70D16H501R','GDFGMN70D18H501F','GDFGMN70D18H501H');

-- recupero dell'ultima istanza nucleo principale (1 iniziale, 3 modificata)
select idIstanza from tabistanza
                 where id_sponsor=(select idSoggetto from tabsoggetto where codiceFiscale='GDFGMN70D16H501T') and rif_tipoIstanza in (1, 3) ORDER BY idIstanza DESC LIMIT 1;

-- recupero dell'ultima istanza nucleo esterno (4 iniziale, 5 modificata)
select idIstanza from tabistanza
                 where id_sponsor=(select idSoggetto from tabsoggetto where codiceFiscale='GDFGMN70D16H501T') and rif_tipoIstanza in (4, 5) ORDER BY idIstanza DESC LIMIT 1;

-- recupero dei facenti parte il nucleo principale
select * from tabnucleifull where rif_istanza = 42;

-- recupero dei facenti parte il nucleo esterno
select * from tabnucleifull where rif_istanza = 43;




select * from tabcartaesercito
