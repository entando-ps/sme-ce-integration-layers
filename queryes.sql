select * from tabsoggetto join tabresidenze on tabsoggetto.idSoggetto=tabresidenze.rif_soggetto;

select * from tabistanza join tabsoggetto on tabistanza.id_sponsor=tabsoggetto.idSoggetto;

select * from tabsoggettiistanze
    join tabsoggetto on tabsoggettiistanze.rif_soggetto=tabsoggetto.idSoggetto
    join tabistanza on tabsoggettiistanze.rif_istanza=tabistanza.idIstanza;

select * from tabnucleifull
    join tabsoggetto on tabnucleifull.rif_soggetto=tabsoggetto.idSoggetto
    join tabistanza on tabnucleifull.rif_istanza=tabistanza.idIstanza;

select * from tabmandato
    join tabnucleifull on tabmandato.rif_nucleofull=tabnucleifull.id
    join tabsoggetto on tabmandato.rif_sponsor=tabsoggetto.idSoggetto;

select * from tabMandatoPVC
    join tabnucleifull on tabMandatoPVC.rifNucleoFull=tabnucleifull.id
    join tabsoggetto on tabMandatoPVC.rifSponsor=tabsoggetto.idSoggetto;

delete from tabsoggetto;

delete from tabresidenze;

delete from tabistanza;

delete from tabsoggettiistanze;

delete from tabnucleifull;

delete from tabmandato;
