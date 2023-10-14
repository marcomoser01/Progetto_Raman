# Usa un'immagine di base con Maven per compilare il progetto
FROM maven:4.0.0-openjdk-19 AS build

# Copia il file pom.xml e il file settings.xml (se necessario) nella directory /app
COPY pom.xml /app/
# COPY settings.xml /root/.m2/settings.xml  # Se hai una configurazione personalizzata di Maven

# Imposta la directory di lavoro corrente su /app
WORKDIR /app

# Pulizia e compilazione del progetto
RUN mvn clean
RUN mvn package


# Usa un'immagine di base con Java
FROM openjdk:19-alpine

# Copia il file JAR dell'applicativo nella directory /app
COPY target/catalog-1.0.jar /app/app.jar

# Imposta la directory di lavoro corrente
WORKDIR /app

# Avvia l'applicativo Spring Boot
CMD ["java", "-jar", "app.jar"]
