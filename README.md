mvn clean package -Dmaven.test.skip=true
sudo docker-compose build postgres redis order-service
sudo docker-compose up postgres redis order-service

#docker comment
sudo docker images
sudo docker image prune -a
sudo docker ps -a
sudo docker container prune
sudo docker rm icommerce_redis_1 order-service customer-service icommerce_api-gateway_1 icommerce_postgres_1
sudo docker rmi icommerce_order-service redis icommerce_customer-service icommerce_api-gateway icommerce_postgres


sudo docker-compose up postgres order-service

# build war -> build image to create container -> up container to fist start and restart instant

#list apis: 
## http://localhost:8081/gateway-service/welcome (move file application ra ben ngoai cung`)
## http://localhost:8081/gateway-service/call-customer-service

## http://localhost:8082/order-service/welcome
## http://localhost:8083/customer-service/welcome 

sudo docker rm icommerce_tracking-service_1
sudo docker rmi icommerce_tracking-service
mvn clean package -Dmaven.test.skip=true
sudo docker-compose build tracking-service
sudo docker-compose up tracking-service
