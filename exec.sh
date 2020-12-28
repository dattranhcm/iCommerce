#!/bin/bash
echo "Run Icommergce scrip"
echo "STOP api-gateway order-service product-service"
sudo docker-compose stop tracking-service api-gateway
echo "BUILD PACKAGE"
mvn clean package -Dmaven.test.skip=true
echo "REMOVE CONTAINERS"
sudo docker rm tracking-service api-gateway-service
echo "REMOVE IMAGES"
sudo docker rmi icommerce_tracking-service icommerce_api-gateway
echo "BUILD IMAGES"
sudo docker-compose build tracking-service api-gateway
echo "RUN CONTAINERS"
sudo docker-compose up tracking-service api-gateway
