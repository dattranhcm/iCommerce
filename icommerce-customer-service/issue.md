#1
order-service_1  | APPLICATION FAILED TO START
order-service_1  | ***************************
order-service_1  | 
order-service_1  | Description:
order-service_1  | 
order-service_1  | Flyway failed to initialize: none of the following migration scripts locations could be found:
order-service_1  | 
order-service_1  |      - classpath:db/migration
order-service_1  | 
order-service_1  | 
order-service_1  | Action:
order-service_1  | 
order-service_1  | Review the locations above or check your Flyway configuration
solution: due to flyway core auto check script follow the path syntax: post/pre, so, it can not find any scrip, we can fix by temporary disable flyway and design scrip path later

#2
can check version of lib by the comment:  mvn dependency:tree -Dverbose
#3
***************************
APPLICATION FAILED TO START
***************************

Description:

An attempt was made to call a method that does not exist. The attempt was made from the following location:

    org.webjars.WebJarAssetLocator.findWebJars(WebJarAssetLocator.java:122)

The following method did not exist:

    io.github.classgraph.Resource.getClasspathElementURI()Ljava/net/URI;

The method's class, io.github.classgraph.Resource, is available from the following locations:

    jar:file:/C:/Users/X048833/.m2/repository/io/github/classgraph/classgraph/4.6.32/classgraph-4.6.32.jar!/io/github/classgraph/Resource.class

it cause by the lib declare on pom:
<dependency>-->
<!--			<groupId>org.springdoc</groupId>-->
<!--			<artifactId>springdoc-openapi-core</artifactId>-->
<!--			<version>1.1.49</version>-->
<!--		</dependency>-->
<!--		<dependency>-->
<!--			<groupId>org.springdoc</groupId>-->
<!--			<artifactId>springdoc-openapi-ui</artifactId>-->
<!--			<version>1.1.49</version>-->
<!--		</dependency>
SOLUTION: temporary disable and handle swagger later

#4
api-gateway_1    | ***************************
api-gateway_1    | APPLICATION FAILED TO START
api-gateway_1    | ***************************
api-gateway_1    | 
api-gateway_1    | Description:
api-gateway_1    | 
api-gateway_1    | Failed to bind properties under 'spring.kafka.consumer.value-deserializer' to java.lang.Class<?>:
api-gateway_1    | 
api-gateway_1    |     Property: spring.kafka.consumer.value-deserializer
api-gateway_1    |     Value: com.ewolff.microservice.invoicing.events.InvoiceDeserializer
api-gateway_1    |     Origin: class path resource [application.properties] from icommerce-api-gateway-0.0.1-SNAPSHOT.jar - 6:42
api-gateway_1    |     Reason: No converter found capable of converting from type [java.lang.String] to type [java.lang.Class<?>]
api-gateway_1    | 
api-gateway_1    | Action:
api-gateway_1    | 
api-gateway_1    | Update your application's configuration
api-gateway_1    | 
SOLOTION: temporary lock the config `spring.kafka.consumer.value-deserializer` on application.properties and fix it later
#5 note: 
recommend: change user service to customer service, api gateway to communication service

#6
after remove container and rebuild new replace postgres container
still cant drop db by scrip
postgres_1       | server started
postgres_1       | CREATE DATABASE
postgres_1       | 
postgres_1       | 
postgres_1       | /usr/local/bin/docker-entrypoint.sh: running /docker-entrypoint-initdb.d/create_user_table.sql
postgres_1       | 2020-12-10 16:07:27.090 UTC [71] ERROR:  cannot drop the currently open database
postgres_1       | 2020-12-10 16:07:27.090 UTC [71] STATEMENT:  DROP DATABASE IF EXISTS dbuser;
postgres_1       | psql:/docker-entrypoint-initdb.d/create_user_table.sql:1: ERROR:  cannot drop the currently open database


#7
redis.clients.jedis.exceptions.JedisConnectionException: Failed connecting to localhost:6379
>> change the host to redis container name

#8[Mongo]
com.mongodb.MongoCommandException: Command failed with error 13 (Unauthorized): 'command insert requires authentication' on server mongo:27017. The full response is {"ok": 0.0, "errmsg": "command insert requires authentication", "code": 13, "codeName": "Unauthorized"}
>> need to define an Icommerce Exception
>> solution: add
    spring.data.mongodb.username=admin
    spring.data.mongodb.password=123456
com.mongodb.MongoCommandException: Command failed with error 17 (ProtocolError): 'Attempt to switch database target during SASL authentication.' on server mongo:27017. The full response is {"ok": 0.0, "errmsg": "Attempt to switch database target during SASL authentication.", "code": 17, "codeName": "ProtocolError"}
>> spring.data.mongodb.database=admin
Query failed with error code 2 and error message 'Field 'locale' is invalid
annotation used @Document(collation = "user-activity"), just remove the collection from the @Document annotation provided by spring-data and replace it with @Document("user-activity")

#9 Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: Infinite recursion (StackOverflowError)
>> https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue

#10 org.postgresql.util.PSQLException: ERROR: relation "hibernate_sequence" does not exist
@GeneratedValue(strategy = GenerationType.IDENTITY) instead of @GeneratedValue