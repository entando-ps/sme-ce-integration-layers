`mvn clean install spring-boot:repackage -DskipTests`
`java -jar sme-ce-integration-layers-0.0.1-SNAPSHOT.jar --app.csv.folder=/Users/germano/temp/files`


1,Nucleo familiare principale
2,Rinnovo Carta Esercito
3,Richiesta integrativa per il proprio nucleo Familiare
4,Nucleo familiare esterno
5,Richiesta integrativa Nucleo Familiare Esterno


Soggetto non dipende da nulla
Residenza --> Soggetto non unique (un Soggetto può avere n residenze)
Istanza --> Soggetto (Sponsor) (una sigola istanza (tipo istanza) ha solo uno sponsor)
Nucleo Familiare --> Soggetto,Istanza (una riga per soggetto, con riferimento alla singola istanza)
Come funziona la Richiesta Integrativa? Deve contenere la fotografia dell'ultimo nucleo o solo i modificati?
Mandato(PVC) --> NucleoFamiliare, Soggetto (Sponsor) (Il mandato si riferisce alle persone che pagano la carta esercito)



**Soggetti**
Presrequisito per tutte le istanze è il recupero dei soggetti agganciati alle proprie residenze
Creazione dei soggetti e delle residenze se non esistono
Recupero dei soggetti se esistono

**Nucleo Familiare Principale**
Se esiste lo Sponsor:
istanza 3 (Richiesta integrativa per il proprio nucleo Familiare)  
Altrimenti 
istanza 1 (Nucleo Familiare Principale) anche con solo se stesso

**Nucleo Familiare Esterno:**
Se esiste lo Sponsor:
non esiste il nucleo familiare esterno: istanza 3 (Nucleo familiare esterno)
esiste il nucleo familiare esterno: istanza 4 (Richiesta integrativa per il proprio nucleo Familiare)

**Mandato:**
per ogni nucleo familiare un mandato ed un mandato pvc agganciato al nucleo familiare del capofamiglie ed allo sponsor


**- limite protocollo: non posso aggiornare una residenza tramite un istanza**

**Come funziona il rinnovo? viene creato un nuovo nucleo?**
