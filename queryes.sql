select * from tabsoggetto join tabresidenze on tabsoggetto.idSoggetto=tabresidenze.rif_soggetto;
select * from tabistanza join tabsoggetto on tabistanza.id_sponsor=tabsoggetto.idSoggetto;


delete from tabsoggetto;

delete from tabresidenze;

delete from tabistanza;
