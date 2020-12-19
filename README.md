mvn clean package -Dmaven.test.skip=true
docker-compose build
docker-compose up

#docker comment
sudo docker images
sudo docker image prune -a
sudo docker ps -a
sudo docker container prune

# build war -> build image to create container -> up container to fist start and restart instant

#list apis: 
## http://localhost:8081/gateway-service/welcome (move file application ra ben ngoai cung`)
## http://localhost:8081/gateway-service/call-customer-service

## http://localhost:8082/order-service/welcome
## http://localhost:8083/customer-service/welcome 
