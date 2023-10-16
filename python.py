#!/usr/bin/env python3
import os
import subprocess
from concurrent.futures import ThreadPoolExecutor

# Ottieni il percorso della directory principale in cui si trova lo script Python
base_dir = os.path.dirname(os.path.abspath(__file__))

# Comando da eseguire in ogni sottodirectory
mvn_command = 'mvn clean package'

# Funzione per eseguire il comando in una directory specifica
def run_mvn_command_in_directory(directory_path):
    try:
        print(f"Esecuzione di '{mvn_command}' in '{directory_path}'")
        subprocess.run(mvn_command, shell=True, cwd=directory_path, check=True)
        print(f"Completato: '{directory_path}'")
    except subprocess.CalledProcessError as e:
        print(f"Errore nell'esecuzione di '{mvn_command}' in '{directory_path}': {e}")

# Scansiona le sottodirectory solo al primo livello
subdirectories = [os.path.join(base_dir, directory) for directory in os.listdir(base_dir) if os.path.isdir(os.path.join(base_dir, directory))]

# Parallelizza l'esecuzione del comando Maven in diverse directory
with ThreadPoolExecutor(max_workers=4) as executor:
    executor.map(run_mvn_command_in_directory, subdirectories)
