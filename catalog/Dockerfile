# Usa un'immagine di base con Maven per compilare il progetto
FROM maven:3.8.3-openjdk-11 AS build

# Copia il file pom.xml e il file settings.xml (se necessario) nella directory /app
COPY pom.xml /app/

# Copia tutto il contenuto del tuo progetto
COPY . /app/

# Imposta la directory di lavoro corrente su /app
WORKDIR /app

# Pulizia e compilazione del progetto
RUN mvn clean package

# Usa un'immagine di base con Java
FROM openjdk:11-jre-slim

# Copia il file JAR dell'applicativo dalla fase di compilazione nella directory /app
COPY --from=build /app/target/catalog-1.0.jar /app/app.jar

# Imposta la directory di lavoro corrente
WORKDIR /app

# Avvia l'applicativo Spring Boot
CMD ["java", "-jar", "app.jar"]
