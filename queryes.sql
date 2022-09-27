select * from tabsoggetto join tabresidenze on tabsoggetto.idSoggetto=tabresidenze.rif_soggetto;

select * from tabistanza join tabsoggetto on tabistanza.id_sponsor=tabsoggetto.idSoggetto;

select * from tabsoggettiistanze
    join tabsoggetto on tabsoggettiistanze.rif_soggetto=tabsoggetto.idSoggetto
    join tabistanza on tabsoggettiistanze.rif_istanza=tabistanza.idIstanza;

select * from tabnucleifull
    join tabsoggetto on tabnucleifull.rif_soggetto=tabsoggetto.idSoggetto
    join tabistanza on tabnucleifull.rif_istanza=tabistanza.idIstanza;


delete from tabsoggetto;

delete from tabresidenze;

delete from tabistanza;

delete from tabsoggettiistanze;

delete from tabnucleifull;
