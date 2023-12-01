# 

1. installare maven
2. bisogna prima eseguire il file Javascript con 

```shell
node mavenScript.js
```

3. (**Alternativamente**)
```python
python3 mvn-package.py
```
4. Entra in modalità root con `sudo su`
5. `docker image ls` per vedere che non ci siano immagini
   1. se ci sono fai `docker image prune -af`
6. `docker ps -a` per assicurarsi che non ci siano containers attivi
7. eseguire il comando `docker compose up -d`
8. Per tirare giu i container => `docker compose down`

## Altri comandi utili

fai ripartire solo un container
```shell
docker compose up -d --no-deps containerName
```

Elimina TUTTE le immagini dopo che si sono stoppati i container con 
```shell
docker image prune -a
```

### non funziona qualcosa?
runna `mvn clean package` nella cartella di un modulo (che sia catalog, rating, gateway, etc)
senza i permessi root.

Ricontrolla di aver eliminato tutte le docker images

## Frontend

Apri la cartella 'frontend' -> [FRONTEND-README.md](./frontend/FRONTEND-README.md) 