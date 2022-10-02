delete
from tabresidenze
where rif_soggetto in (select idSoggetto
                       from tabsoggetto
                       where codiceFiscale in
                             ('GDFGMN70D16H501T', 'GDLGMN70D16H501R', 'GDLGMN70D18H501F', 'GDLGMN70D18H501H',
                              'GDFGMN70D16H501R', 'GDFGMN70D18H501F', 'GDFGMN70D18H501H'));
delete
from tabistanza
where id_sponsor in (select idSoggetto
                     from tabsoggetto
                     where codiceFiscale in
                           ('GDFGMN70D16H501T', 'GDLGMN70D16H501R', 'GDLGMN70D18H501F', 'GDLGMN70D18H501H',
                            'GDFGMN70D16H501R', 'GDFGMN70D18H501F', 'GDFGMN70D18H501H'));
delete
from tabsoggettiistanze
where rif_soggetto in (select idSoggetto
                       from tabsoggetto
                       where codiceFiscale in
                             ('GDFGMN70D16H501T', 'GDLGMN70D16H501R', 'GDLGMN70D18H501F', 'GDLGMN70D18H501H',
                              'GDFGMN70D16H501R', 'GDFGMN70D18H501F', 'GDFGMN70D18H501H'));
delete
from tabnucleifull
where rif_soggetto in (select idSoggetto
                       from tabsoggetto
                       where codiceFiscale in
                             ('GDFGMN70D16H501T', 'GDLGMN70D16H501R', 'GDLGMN70D18H501F', 'GDLGMN70D18H501H',
                              'GDFGMN70D16H501R', 'GDFGMN70D18H501F', 'GDFGMN70D18H501H'));
delete
from tabmandato
where rif_sponsor in (select idSoggetto
                      from tabsoggetto
                      where codiceFiscale in
                            ('GDFGMN70D16H501T', 'GDLGMN70D16H501R', 'GDLGMN70D18H501F', 'GDLGMN70D18H501H',
                             'GDFGMN70D16H501R', 'GDFGMN70D18H501F', 'GDFGMN70D18H501H'));;
delete
from tabMandatoPVC
where rifSponsor in (select idSoggetto
                     from tabsoggetto
                     where codiceFiscale in
                           ('GDFGMN70D16H501T', 'GDLGMN70D16H501R', 'GDLGMN70D18H501F', 'GDLGMN70D18H501H',
                            'GDFGMN70D16H501R', 'GDFGMN70D18H501F', 'GDFGMN70D18H501H'));;

delete
from tabcartaesercito
where rif_soggetto in (select idSoggetto
                       from tabsoggetto
                       where codiceFiscale in
                             ('GDFGMN70D16H501T', 'GDLGMN70D16H501R', 'GDLGMN70D18H501F', 'GDLGMN70D18H501H',
                              'GDFGMN70D16H501R', 'GDFGMN70D18H501F', 'GDFGMN70D18H501H'));;

delete
from tabsoggetto
where codiceFiscale in
      ('GDFGMN70D16H501T', 'GDLGMN70D16H501R', 'GDLGMN70D18H501F', 'GDLGMN70D18H501H', 'GDFGMN70D16H501R',
       'GDFGMN70D18H501F', 'GDFGMN70D18H501H');

-- -------------------------------------------------------------------------
-- -------------------------------------------------------------------------
-- -------------------------------------------------------------------------

delete
from tabsoggetto;
delete
from tabresidenze;
delete
from tabistanza;
delete
from tabsoggettiistanze;
delete
from tabnucleifull;
delete
from tabmandato;
delete
from tabMandatoPVC;
delete
from tabcartaesercito;


