#!/bin/bash
echo "Run Icommergce scrip"
echo "STOP api-gateway order-service product-service"
sudo docker-compose stop api-gateway order-service
echo "BUILD PACKAGE"
mvn clean package -Dmaven.test.skip=true
echo "REMOVE CONTAINERS"
sudo docker rm api-gateway-service order-service
echo "REMOVE IMAGES"
sudo docker rmi icommerce_api-gateway icommerce_order-service
echo "BUILD IMAGES"
sudo docker-compose build api-gateway order-service
echo "RUN CONTAINERS"
sudo docker-compose up api-gateway order-service
