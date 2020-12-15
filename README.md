mvn clean package -Dmaven.test.skip=true
docker-compose build
docker-compose up

#docker comment
sudo docker images
sudo docker image prune -a
sudo docker ps -a
sudo docker container prune

#fist api: http://localhost:8081/communicationservice/welcome (move file application ra ben ngoai cung`)
