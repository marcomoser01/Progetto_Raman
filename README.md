# COSE CHE MARCO AVREBBE DOVUTO DIRCI PRIMA

1. installare maven
2. bisogna prima eseguire il file Javascript con 

```shell
node mavenScript.js
```

3. Alternativamente runna
```python
python3 mvn-package.py
```

4. eseguire il comando `sudo docker compose up -d`

potrebbe essere utile

questo serve per restartare solo un container
```shell
docker compose up -d --no-deps containerName
```

non funziona qualcosa?
runna `mvn clean package` nella cartella di un modulo (che sia catalog, rating, gateway, etc)
senza i permessi root.
