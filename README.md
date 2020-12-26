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
http://localhost:8082/cart-service/fetchProductDetailByProductCode?codes=IP12MN
## http://localhost:8083/customer-service/welcome 
## http://localhost:8084/tracking-service/welcome
## http://localhost:8085/product-service/welcome 

mvn clean package -Dmaven.test.skip=true
sudo docker-compose stop order-service product-service
sudo docker rm product-service order-service
sudo docker rmi icommerce_product-service icommerce_order-service
sudo docker-compose build product-service order-service
sudo docker-compose up product-service order-service

sudo docker-compose stop api-gateway order-service product-service
mvn clean package -Dmaven.test.skip=true
sudo docker rm api-gateway-service product-service order-service
sudo docker rmi icommerce_api-gateway icommerce_product-service icommerce_order-service
sudo docker-compose build api-gateway product-service order-service
sudo docker-compose up api-gateway product-service order-service