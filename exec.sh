#!/bin/bash
echo "Run Icommergce scrip"
echo "STOP api-gateway order-service product-service"
sudo docker-compose stop api-gateway order-service product-service
echo "BUILD PACKAGE"
mvn clean package -Dmaven.test.skip=true
echo "REMOVE CONTAINERS"
sudo docker rm api-gateway-service product-service order-service
echo "REMOVE IMAGES"
sudo docker rmi icommerce_api-gateway icommerce_product-service icommerce_order-service
echo "BUILD IMAGES"
sudo docker-compose build api-gateway product-service order-service
echo "RUN CONTAINERS"
sudo docker-compose up api-gateway product-service order-service
