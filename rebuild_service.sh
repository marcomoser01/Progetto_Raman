#!/bin/bash

# Verifica se è stato passato un argomento al file
if [ -z "$1" ]; then
    echo "Usage: $0 <service_name>"
    exit 1
fi

# Nome del servizio passato come argomento
service_name="$1"
container_id=""

# Controllo se esiste un container con lo stesso nome
if [ "$(docker ps -aq -f name=${service_name})" ]; then
    container_id=$(docker ps -aq -f name=${service_name})
    image_id=$(docker inspect -f '{{.Image}}' "${container_id}")
else
    echo "No running container found with the name ${service_name}."
fi

# Naviga nella cartella del servizio e esegui la build Maven
cd "${service_name}"
mvn clean package
cd ..

# Rimuovi il container e l'immagine Docker associati, se esistono
if [ -n "$container_id" ]; then
    docker stop "${service_name}" > /dev/null && docker rm "${service_name}" > /dev/null
    echo "Container ${service_name} stopped and removed."
    if [ -n "$image_id" ]; then
        docker rmi "${image_id}" > /dev/null
        echo "Image ${image_id} removed."
    else
        echo "No image found for ${service_name}."
    fi
else
    echo "No running container found with the name ${service_name}."
fi

docker-compose up --no-deps -d "${service_name}"
