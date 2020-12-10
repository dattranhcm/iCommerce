mvn clean package -Dmaven.test.skip=true
docker-compose build
docker-compose up

#fist api: http://localhost:8081/communicationservice/welcome (move file application ra ben ngoai cung`)
