#!/bin/bash
echo "Run Icommergce scrip"
echo "STOP ALL SERVICES"
sudo docker-compose stop
echo "BUILD PACKAGE"
mvn clean package -Dmaven.test.skip=true
echo "REMOVE CONTAINERS"
sudo docker rm customer-service api-gateway-service order-service tracking-service product-service
echo "REMOVE IMAGES"
sudo docker rmi icommerce_product-service icommerce_tracking-service icommerce_customer-service icommerce_order-service icommerce_api-gateway
echo "BUILD IMAGES"
sudo docker-compose build
echo "RUN CONTAINERS"
sudo docker-compose up
