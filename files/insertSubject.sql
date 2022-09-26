INSERT INTO tabsoggetto (codiceFiscale,
rif_stato, -- manca la tabella vocabolario ma dato che sono tutti nuovi inserimenti immagino possa essere fisso se ce lo comunicano o configurabile (Fisso in attesa di approvozaione)
rif_amministrazione, -- chiedere , probabilmente può essere impostato fisso rif tipo forza armata sempre 1 esercito
rif_gradoQualifica, -- preso da tabella vocabolario ( con id forza armata con id f a 1 che fa rif amministrazione )
rif_posizione, -- preso da tabella vocabolario  ( combo libera ) se non fa parte delle forze armate switch familiare = 0
nome,
cognome,
nascitaData,
nascitaLuogo,
nazionalita,
fototessera, -- nome file senza percorso con estensione es STRCLD81D05B114V.jpeg (o png)
anagraficaVerificataDa, -- ID fittizio che indica un operatore inseriti da soli
enteAppartenenza, -- opzionale e si scrive a mano controllare presenza campo inserimento testo libero su figma
telUfficio,
telCellulare,
email,
rif_rapporto, -- preso da tabella vocabolario
pin, -- generato prima della scrittura della riga algoritmo di generazione ce lo manda Giovanni Capuano
sesso)
VALUES (
:codiceFiscale,
'1', -- rif stato
'1', -- rif amministrazione
:rif_gradoQualifica,
:rif_posizione,
:nome,
:cognome,
:nascitaData,
:nascitaLuogo,
:nazionalita,
:fototessera,
:anagraficaVerificataDa,
:enteAppartenenza,
:telUfficio,
:telCellulare,
:email,
:rif_rapporto,
:pin,
:sesso)
