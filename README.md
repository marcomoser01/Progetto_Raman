# COSE CHE MARCO AVREBBE DOVUTO DIRCI PRIMA

1. installare maven
2. bisogna prima eseguire il file python `mvn-package`
3. eseguire il comando `docker compose up -d`

potrebbe essere utile

questo serve per restartare solo un container
```bash
docker compose up -d --no-deps myService
```

non funziona qualcosa?
runna `mvn clean package` nella cartella di un modulo (che sia catalog, rating, gateway, etc)

