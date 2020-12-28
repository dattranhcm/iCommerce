#!/bin/bash
echo "Run Icommergce scrip"
echo "STOP api-gateway order-service product-service"
sudo docker-compose stop customer-service
echo "BUILD PACKAGE"
mvn clean package -Dmaven.test.skip=true
echo "REMOVE CONTAINERS"
sudo docker rm customer-service
echo "REMOVE IMAGES"
sudo docker rmi icommerce_customer-service
echo "BUILD IMAGES"
sudo docker-compose build customer-service
echo "RUN CONTAINERS"
sudo docker-compose up customer-service
