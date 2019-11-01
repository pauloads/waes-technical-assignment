#!/bin/bash

container_name=waes-technical-assignment

mvn clean install -DskipTests

docker container stop $container_name && docker container rm $container_name
docker run -d -p 8080:8080 --name waes-technical-assignment paulocorreadev/waes-technical-assignment
