#!/bin/bash
echo "Run Icommergce scrip"
echo "BUILD PACKAGE"
mvn clean package -Dmaven.test.skip=true
echo "BUILD IMAGES"
sudo docker-compose build
echo "RUN CONTAINERS"
sudo docker-compose up

