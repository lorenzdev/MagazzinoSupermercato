# Magazzino di un supermercato

## Verifica TePSIT

### Gruppo 3

Cioffi    
Preti    
Gitto   

### Docente

prof. Vitale Lorenzo

### Descrizione
**Obiettivo minimo (80 punti)**  
Realizzare un programma Client-Server che implementi la gestione di un magazzino merci in cui
viene gestita la banca dati delle merci disponibili in un supermercato.
Ogni utente ha un account composto dalla propria e-mail e da una password in aggiunta ad una serie
di informazioni personali come nome, cognome, numero di cellulare, data di nascita, indirizzo e
città di residenza e ruolo (magazziniere o acquirente).
Le merci hanno un id, una tipologia (salumi, detersivi, surgelati, carni), prezzo, un titolo, una
descrizione e la quantità disponibile.
Il Server offre tre servizi ai Client:  
**1.** Permette ad un nuovo utente di iscriversi inviando i propri dati al Server che procede a
memorizzarli nella propria banca dati;  
**2.** Gli utenti iscritti, dopo essersi loggati inviando la propria e-mail e password di accesso,
possono richiedere l’elenco delle merci memorizzate e loro disponibilità;  
**3.** Gli utenti iscritti di tipo “acquirenti” possono acquistare della merce indicando l’id e la
quantità (ove disponibile);
Le merci mostrate all’utente, devono essere visualizzate secondo l’ordine alfabetico.  

**Obiettivo avanzato (20 punti)**  
Implementare un ulteriore servizio di multicasting che prevede che i Client degli utenti di tipo
“magazzinieri” si mettano in ascolto.
Il Server, quando riceve una richiesta di acquisto da parte di un Client, prima di modificare la nuova
quantità della merce nel database, invia a tutti i Client di tipo “magazzinieri” connessi e in ascolto,
il messaggio: “La merce con id sta finendo essendo rimasti solo totale quantità pezzi” nel caso in
cui la quantità vada sotto le 20 unità.  
 
*SUGGERIMENTO: Per semplificare la risoluzione di questo punto, prevedere che i Client, una volta in modalità
di ricezione multicasting, non ricevano comandi di input dall’utente ma restano esclusivamente in
ascolto.*


### Usage

Prima operazione da effettuare è ottenere il progetto dal repository lanciando il seguente comando dalla bash di git
  
```
git clone https://github.com/lorenzdev/MagazzinoSupermercato.git 
```
  
Successivamente lanciare i seguenti comandi per ottenere le modifiche:

```
git pull origin master
```  

e per pubblicare le proprie modifiche:

```
git add .
```  
```
git commit -m "descrizione del commit"
```  
```
git push origin master
```  

### Consegna
Entro il 31/05/2019
