# Usa un'immagine di base con Java
FROM openjdk:19-alpine

# Copia il file JAR dell'applicativo nella directory /app
COPY target/user-1.0.jar /app/app.jar

# Imposta la directory di lavoro corrente
WORKDIR /app

# Avvia l'applicativo Spring Boot
CMD ["java", "-jar", "app.jar"]
