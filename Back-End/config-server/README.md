# Config Server

Il Config Server di Spring Cloud gestisce le configurazioni esterne per le applicazioni in un'architettura distribuita. Centralizza le configurazioni in un repository esterno, come Git, fornendo flessibilità e gestione semplificata per gli ambienti di sviluppo. Le applicazioni client recuperano dinamicamente le proprie configurazioni dal Config Server.

## Utilizzo

Questo Config Server si collega a un repository Git remoto dove sono memorizzate le configurazioni delle diverse applicazioni. Le applicazioni client (come ad esempio altri servizi o microservizi) si collegano a questo server per recuperare le loro configurazioni in base ai profili specificati.

## Repository di Configurazione

Il Config Server recupera le configurazioni da un repository Git esterno. È possibile configurare l'URL del repository e il percorso delle configurazioni all'interno del file `application.properties` o `application.yml` del Config Server.

Per esempio:

```yaml
spring:
  cloud:
    config:
      server:
        git:
          uri: <URL_del_tuo_repository_git>
```