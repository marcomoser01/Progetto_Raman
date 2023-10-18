#!/usr/bin/env python3
import os
import subprocess
from concurrent.futures import ThreadPoolExecutor

# # Comando da eseguire in ogni sottodirectory
mvn_command = 'mvn clean package'


def esegui_comando(directory, comando):
    try:
        # Cambia la directory corrente alla directory del progetto
        os.chdir(directory)

        # Esegui il comando nella directory del progetto e cattura l'output
        result = subprocess.run(comando, shell=True, capture_output=True, text=True)

        if result.returncode == 0:
            # Comando eseguito con successo
            print(f"Il progetto {directory} è stato compilato con successo")
        else:
            # Comando fallito, stampa l'output di errore
            print(
                f"Errore durante l'esecuzione di {comando} nella directory {directory}:")
            print(result.stderr)
    except Exception as e:
        print(
            f"Errore durante l'esecuzione di {comando} nella directory {directory}: {e}")


def compila_progetti(mavenProjects, comando):
    # Crea un ThreadPoolExecutor per eseguire comandi in parallelo
    with ThreadPoolExecutor(max_workers=len(mavenProjects)) as executor:
        # Esegui il comando in parallelo per ciascun progetto
        for project in mavenProjects:
            executor.submit(esegui_comando, project, comando)


def getMavenProject(path):
    mavenProject = []

    # Verifica se il percorso esiste ed è una directory
    if os.path.exists(path) and os.path.isdir(path):
        # Utilizza os.listdir per ottenere l'elenco di tutti i file e cartelle
        contenuti = os.listdir(path)

        for cartella in contenuti:
            # Verifica se è una cartella
            cartella_path = os.path.join(path, cartella)
            if os.path.isdir(cartella_path):
                # Verifica se all'interno della cartella è presente un file pom.xml
                if "pom.xml" in os.listdir(cartella_path):
                    mavenProject.append(path + "/" + cartella)

    return mavenProject


def main() -> None:
    # Ottieni il percorso della directory principale in cui si trova lo script Python
    pwd = os.path.dirname(os.path.abspath(__file__))
    mavenProject = getMavenProject(pwd)
    compila_progetti(mavenProject, mvn_command)


if __name__ == '__main__':
    main()
