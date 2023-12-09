# Progetto E-commerce con Spring Boot, Zipkin e MySQL

Questo progetto rappresenta un'applicazione  che emula le funzionalità di un negozio online. <br>Sviluppata mediante l'impiego combinato delle tecnologie Spring Boot, Zipkin e MySQL.

Diviso in vari micro-servizzi, ciascuno di essi è accuratamente documentato tramite un README specifico, offrendo così una visione dettagliata delle funzionalità, delle istruzioni e dei dettagli di implementazione di ogni componente. Questa struttura modulare consente agli sviluppatori di comprendere e approfondire in modo specifico le diverse parti del progetto, facilitando la comprensione dell'intero ecosistema e delle sue singole componenti.

## Prerequisiti

Prima di eseguire il progetto, assicurati di avere installati i seguenti software:
- JDK (Java Development Kit)
- Maven
- Docker

## Compilazione dei Progetti

Per compilare i vari progetti, è possibile utilizzare uno script fornito. Se hai Node.js installato, esegui il seguente comando per avviare lo script Maven:

```bash
node mavenScript.mjs
```

## Avvio del Progetto

Dopo aver compilato i progetti, esegui il seguente comando per avviare i container Docker:

```bash
docker compose up -d
```


L'operazione potrebbe richiedere alcuni minuti per assicurare il corretto funzionamento di tutti i servizi.

## Docker Images

Le immagini Docker per Zipkin e MySQL vengono scaricate da Docker Cloud.

## Collegamenti ai microservizzi


- [Config-Server](./Back-End/config-server/README.md)
- [Registry](./Back-End/registry/README.md)
- [Gateway](./Back-End/gateway/README.md)
- [Catalog](./Back-End/catalog/README.md)
- [User](./Back-End/user/README.md)
- [Rating](./Back-End/rating/README.md)
- [Purchase](./Back-End/purchase/README.md)

## Swagger

Ogni REST API Service ha la sua pagina Swagger. Per i dettagli e l'utilizzo delle API, consulta la documentazione nei rispettivi README.

