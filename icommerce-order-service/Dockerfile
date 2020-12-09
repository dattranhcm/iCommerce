FROM openjdk:11.0.2-jre-slim
COPY target/icommerce-order-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT exec java -jar icommerce-order-service-0.0.1-SNAPSHOT.jar
#CMD /usr/bin/java -Xmx400m -Xms400m  -XX:TieredStopAtLevel=1 -noverify -jar icommerce-order-service-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
