finire di commentare rating e user , fare script del esposizione 


# Steps to start environment

1. (installare maven)
2. run

```shell
node mavenScript.js
```

1. (<strong>Alternativamente</strong>)

```python
python3 mvn-package.py
```

1. Entra in modalità root con `sudo su`
2. `docker image ls` per vedere che non ci siano immagini
    1. se ci sono fai `docker image prune -af`
3. `docker ps -a` per assicurarsi che non ci siano containers attivi
4. eseguire il comando `docker compose up -d`

## Altri comandi utili

Per tirare giu i container => `docker compose down`

fai ripartire solo un container

```bash
docker compose up -d --no-deps containerName
```

Elimina TUTTE le immagini dopo che si sono stoppati i container con

```bash
docker image prune -a
```

Per uscire dalla modalità root

```bash
exit
```

### Non funziona qualcosa?

runna `mvn clean package` nella cartella di un modulo (che sia catalog, rating, gateway, etc)
senza i permessi root.

Ricontrolla di aver eliminato tutte le docker images

## Frontend

Apri la cartella 'frontend' -> [FRONTEND-README.md](./frontend/FRONTEND-README.md)