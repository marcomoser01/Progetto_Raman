# Registry Service

Il servizio Registry è il modulo di registrazione e scoperta dei microservizi all'interno dell'architettura a microservizi. Eureka agisce come un registro centrale dove i microservizi si registrano durante l'avvio e possono scoprire informazioni su altri servizi registrati.

## Descrizione

Questo servizio funge da registro centrale per la registrazione e la gestione dei microservizi nell'architettura a microservizi. Utilizzando il protocollo di comunicazione REST, i microservizi si registrano presso il servizio Eureka Registry all'avvio per rendere disponibili le loro informazioni (ad esempio, indirizzo IP, porta e stato) ad altri servizi.

## Documentazione

Il servizio Eureka Registry non ha una documentazione API esplicita in quanto si tratta di un registro e di solito non richiede interazione diretta da parte degli sviluppatori. Tuttavia, è possibile accedere alla console di Eureka per visualizzare i dettagli sui microservizi registrati e i loro stati utilizzando l'URL [http://localhost:8761](http://localhost:8761).

Si consiglia di consultare la console di Eureka per monitorare lo stato della registrazione dei microservizi e per verificare le informazioni disponibili sui servizi attivi all'interno dell'architettura a microservizi.
