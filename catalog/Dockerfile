# Usa un'immagine di base con Maven per compilare il progetto
FROM maven:4.0.0-openjdk-19 AS build

# Copia il file pom.xml e il file settings.xml (se necessario) nella directory /app
COPY pom.xml /app/
# COPY settings.xml /root/.m2/settings.xml  # Se hai una configurazione personalizzata di Maven

# Imposta la directory di lavoro corrente su /app
WORKDIR /app

# Pulizia e compilazione del progetto in un'unica istruzione
RUN mvn clean package

# Usa un'immagine di base con Java
FROM openjdk:19-alpine

# Copia il file JAR dell'applicativo dalla fase di compilazione nella directory /app
COPY --from=build /app/target/catalog-1.0.jar /app/app.jar

# Imposta la directory di lavoro corrente
WORKDIR /app

# Avvia l'applicativo Spring Boot
CMD ["java", "-jar", "app.jar"]
