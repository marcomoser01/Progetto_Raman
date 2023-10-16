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
        # Ignora l'output dei comandi
        with open(os.devnull, 'w') as null:
            subprocess.run(mvn_command, shell=True, cwd=directory_path, check=True, stdout=null, stderr=null)
        check_jar_file(directory_path)
    except subprocess.CalledProcessError as e:
        print(f"Errore nell'esecuzione di '{mvn_command}' in '{directory_path}': {e}")

# Funzione per controllare la presenza di un file .jar nella cartella target
def check_jar_file(directory_path):
    target_dir = os.path.join(directory_path, 'target')
    if os.path.exists(target_dir):
        for file in os.listdir(target_dir):
            if file.endswith('.jar'):
                print(f"File .jar trovato in '{target_dir}': {file}")
                return
    print(f"Nessun file .jar trovato in '{target_dir}'")

# Scansiona le sottodirectory solo al primo livello
subdirectories = [os.path.join(base_dir, directory) for directory in os.listdir(base_dir) if os.path.isdir(os.path.join(base_dir, directory))]

# Parallelizza l'esecuzione del comando Maven in diverse directory
with ThreadPoolExecutor(max_workers=4) as executor:
    executor.map(run_mvn_command_in_directory, subdirectories)
