# Funzionamento del Servizio Gateway

Il servizio gateway è un componente fondamentale nell'architettura a microservizi, facilitando l'indirizzamento delle richieste dei client verso le varie applicazioni interne del sistema. Serve da intermediario, instradando le richieste ai servizi appropriati secondo i percorsi e i filtri definiti. Questo assicura flessibilità e gestione centralizzata nell'architettura a microservizi.


## Ruolo del Gateway

- **Instradamento delle Richieste**: Il gateway è configurato per gestire e indirizzare le richieste provenienti dai client verso specifiche applicazioni interne. Utilizza informazioni come i percorsi delle richieste (`/catalog/**`, `/user/**`, `/rating/**`, `/purchase/**`) per reindirizzare le richieste ai servizi corrispondenti (`CATALOG`, `USER`, `RATING`, `PURCHASE`).

## Vantaggi

- **Punto di Accesso Centralizzato**: Fornisce un punto di accesso unificato e centralizzato per le richieste dei client.
- **Gestione delle Richieste**: Permette la gestione e l'indirizzamento delle richieste verso le diverse applicazioni interne del sistema, semplificando la gestione dei percorsi e delle destinazioni per i client.

