#!/usr/bin/env python3

import os
import subprocess

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

# Scansiona tutte le sottodirectory nella directory principale
for root, dirs, files in os.walk(base_dir):
    for directory in dirs:
        # Crea il percorso completo della sottodirectory
        directory_path = os.path.join(root, directory)
        run_mvn_command_in_directory(directory_path)
