# Fase di build
FROM maven:latest AS builder

# Copia il codice sorgente dell'applicazione nella directory di lavoro
COPY . /app

# Imposta la directory di lavoro corrente
WORKDIR /app

# Esegui il comando Maven per buildare l'applicativo
RUN mvn clean package

# Fase finale
FROM openjdk:17-alpine

# Copia il file JAR dell'applicativo dalla fase di build precedente
COPY --from=builder /app/target/registry-1.0.jar /app/app.jar

# Imposta la directory di lavoro corrente
WORKDIR /app

# Avvia l'applicativo Spring Boot
CMD ["java", "-jar", "app.jar"]