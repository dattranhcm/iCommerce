## I. BRIEF INTRODUCTION
### Springboot Microservices architecture for Icommerce (Backend)

Implementation of a REST Microservices in an E-Commerce with Spring boot, multiple modules.                
Support very basic feature as below:
- **Allow customer registration by their facebook account**
- **Login with facebook account (by facebook address and facebook token provided by facebook)**
- **View list of products**
- **Create and View their shopping cart in multiple device with same customer account**
- **Create an order from shopping cart**
- **Tracking user activity via the api they called and store as raw data**
- **View data of tracking activity by ` customer action` or `customer`**
### Project architecture
![Spring Microservices architecture](resources/ICommerce-Architecture.png)

### Architecture Brief Description
#### Services
- **icommerce-api-gateway** [API Gateway responsible handle process request from FE as a gateway of system, check security and call the special service to handle the request]
- **icommerce-customer-service** [Customer service handle business related to business customer, who use and buy product/goods on system]
- **icommerce-order-service** [Order service responsible create order from existed shopping cart and put INIT ORDER to message queue via topic 'order', listen new order from Message Queue to continue process it ,also support view order detail and all order of customer]
- **icommerce-cart-service** [Cart service allow customer add an item to their shopping cart and process create an order base on their shopping cart]
- **icommerce-product-service** [Product service is simple provide service info (already has some as initial project)]
- **icommerce-tracking-service** [Tracking service are listen from Kafka Message Queue via topic 'tracking-customer-activity' and store the raw data into Mongo db as a data storage for future feature]
#### Tools
- **Redis engine** [As a memory database, in this project scope, Redis is used to store customer shopping cart to make sure the customer shopping cart always available what ever customer use multiple devices] 
- **Mongo** [In this project scope, Mongo is defined as storage to store raw data (user activity) that listen from Tracking Service via topic 'tracking-customer-activity' on Message Queue and save into Mongo]
- **Kafka** [In this current scope, Kafka has 2 topics 'order' and 'tracking-customer-activity' to handle message and provide message for services process]
### Tools and Technologies
- **Java 8**
- **Spring Boot** - version 2.4.0
- **Json Web Token: jsonwebtoken** version 0.9.1
- **Spring Kafka**
- **Redis Client : JEDIS**
- **Spring Data REDIS**
- **Spring Data JPA**
- **Message Queue engine**: Kafka
- **SQL Database engine** : Postgres
- **NOSQL Database engine** : MongoDB
- **In-memory NOSQL data engine** : Redis
- **Maven**
- **Docker** version 19.03.11
- **Docker Compose**  version 1.21.2

## II. RUNNING GUIDELINE
### Requisite on local machine
- Maven
- Docker
- Docker Compose
### Build, Deploy project on local
**Run the comment: bash exec.sh**
**OR Run single comment if you want as below**
- ***Build project: mvn clean package -Dmaven.test.skip=true***
- ***Build docker images/container: sudo docker-compose build***
- ***Run docker containers: sudo docker-compose up***

### 1. Customer registration
> Note: For quickly and simply, at this step are make an assumption that we already has response trust from facebook and provided some bried info like `name`, `facebook address`, `facebook token`, etc..` to use this for our system user registration
```
    curl --location --request POST 'http://localhost:8081/gateway-service/customer-registration' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "userName": "tai.nguyen",
        "facebookId": "tai.nguyen@gmail.com",
        "facebookToken": "e10adc3949ba59abbe56e05711111111",
        "firstName": "Tai",
        "lastName": "Nguyen"
    }'
```
Response:
```
   {
       "code": 0,
       "message": null,
       "content": "Registration success"
   }
```
### 2. Customer login, get jwt token to use for calling other api
> Note: we will get a jwt after login success and will use this token to continue call the api in gateway service, let`s assume other service are internal service, not publish (those service still not apply any security check).
```
    curl --location --request GET 'http://localhost:8081/gateway-service/login' \
    --header 'facebook-id: tai.nguyen@gmail.com' \
    --header 'facebook-token: e10adc3949ba59abbe56e05711111111'
```
Response:
```
    {
        "code": 0,
        "message": null,
        "content": "Loggin success",
        "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDkxNDg0OTgsInN1YiI6IjM1MjYzMGRjLTNmMjMtNDRhYy1iMDY4LTdhNmNmNmZiNDk1NyJ9.vArXbxVSKlkSDhE-aClq7tU1POqRhqa_ArbPNitCsTw"
    }
```
### 3. Fetch products infomation by product code
```
    curl --location --request GET 'http://localhost:8081/gateway-service/products?codes=IP12MN,IP12BLU' \
    --header 'access-token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlMTg4MzYzMS00YTc5LTRhM2EtOWE4YS01MDNkMmJlNjBjOTUifQ.JmFpM_YRwnURM6D9gxYffqHuHQkx-a9oLs63OTWXHzg'
```
Response:
```
    {
        "code": 0,
        "message": null,
        "content": "",
        "data": [
            {
                "uuid": "90733264-45f1-11eb-b378-0242ac130002",
                "productName": "IPhone 12 mini",
                "productCode": "IP12MN",
                "price": [
                    {
                        "id": 1,
                        "price": 19000000,
                        "createdAt": "2020-12-28T09:09:05.922+00:00",
                        "updatedAt": "2020-12-28T09:09:05.922+00:00",
                        "currentPrice": false
                    },
                    {
                        "id": 2,
                        "price": 22000000,
                        "createdAt": "2020-12-28T09:09:05.927+00:00",
                        "updatedAt": "2020-12-28T09:09:05.927+00:00",
                        "currentPrice": true
                    }
                ],
                "createdAt": "2020-12-28T09:09:05.908+00:00",
                "updatedAt": "2020-12-28T09:09:05.908+00:00"
            },
            {
                "uuid": "ed8fcf20-45f1-11eb-b378-0242ac130002",
                "productName": "IPhone 12 Blue",
                "productCode": "IP12BLU",
                "price": [
                    {
                        "id": 3,
                        "price": 24000000,
                        "createdAt": "2020-12-28T09:09:05.929+00:00",
                        "updatedAt": "2020-12-28T09:09:05.929+00:00",
                        "currentPrice": true
                    }
                ],
                "createdAt": "2020-12-28T09:09:05.909+00:00",
                "updatedAt": "2020-12-28T09:09:05.909+00:00"
            }
        ]
    }
```
### 4. Add a product item into shopping cart
```
    curl --location --request POST 'http://localhost:8081/gateway-service/add-cart-item' \
    --header 'access-token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlMTg4MzYzMS00YTc5LTRhM2EtOWE4YS01MDNkMmJlNjBjOTUifQ.JmFpM_YRwnURM6D9gxYffqHuHQkx-a9oLs63OTWXHzg' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    	"productUUID": "90733264-45f1-11eb-b378-0242ac130002",
    	"productName": "IPhone 12 mini",
    	"productCode": "IP12MN",
    	"price":"19000000"
    }'
```
Response:
```    
    {
        "userUuid": "a518d032-45f3-11eb-b378-0242ac130002",
        "productsInCart": [
            {
                "productUUID": "90733264-45f1-11eb-b378-0242ac130002",
                "productName": "IPhone 12 mini",
                "productCode": "IP12MN",
                "price": 19000000
            }
        ]
    }
```
### 4.1 View Customer`s shopping cart
```
   curl --location --request GET 'http://localhost:8081/gateway-service/shopping-cart-of-customer' \
   --header 'access-token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlMTg4MzYzMS00YTc5LTRhM2EtOWE4YS01MDNkMmJlNjBjOTUifQ.JmFpM_YRwnURM6D9gxYffqHuHQkx-a9oLs63OTWXHzg'
```
### 5 Create order from shopping cart
```
    curl --location --request POST 'http://localhost:8081/gateway-service/create-order' \
    --header 'access-token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlMTg4MzYzMS00YTc5LTRhM2EtOWE4YS01MDNkMmJlNjBjOTUifQ.JmFpM_YRwnURM6D9gxYffqHuHQkx-a9oLs63OTWXHzg'
```
Response:
```
    {
        "code": 0,
        "message": null,
        "content": "Order created",
        "data": {
            "uuid": "cfcca392-fe39-41e3-84bd-1ac431171bd2",
            "customerId": "a518d032-45f3-11eb-b378-0242ac130002",
            "totalAmount": 0,
            "shipAddress": "Any where in HCMC.",
            "orderCode": "A9EB1",
            "status": "INIT",
            "orderItems": [
                {
                    "id": 1,
                    "order": null,
                    "itemUuid": "90733264-45f1-11eb-b378-0242ac130002",
                    "itemPrice": 22000000,
                    "subOrderAmount": 0,
                    "subOrderStatus": "AVAILABLE",
                    "createdAt": "2020-12-28T11:06:43.997+00:00",
                    "updatedAt": "2020-12-28T11:06:43.997+00:00"
                },
                {
                    "id": 2,
                    "order": null,
                    "itemUuid": "ed8fcf20-45f1-11eb-b378-0242ac130002",
                    "itemPrice": 24000000,
                    "subOrderAmount": 0,
                    "subOrderStatus": "AVAILABLE",
                    "createdAt": "2020-12-28T11:06:43.997+00:00",
                    "updatedAt": "2020-12-28T11:06:43.997+00:00"
                }
            ],
            "createdAt": "2020-12-28T11:06:43.996+00:00",
            "updatedAt": "2020-12-28T11:06:43.996+00:00"
        }
    }
```
### 5.1 View all order of customer
```
    curl --location --request GET 'http://localhost:8081/gateway-service/order-detail-of-customer' \
    --header 'access-token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlMTg4MzYzMS00YTc5LTRhM2EtOWE4YS01MDNkMmJlNjBjOTUifQ.JmFpM_YRwnURM6D9gxYffqHuHQkx-a9oLs63OTWXHzg'
```    
Response:
```
    {
        "code": 0,
        "message": null,
        "content": "found",
        "data": [
            {
                "uuid": "cfcca392-fe39-41e3-84bd-1ac431171bd2",
                "customerId": "a518d032-45f3-11eb-b378-0242ac130002",
                "totalAmount": 0,
                "shipAddress": "Any where in HCMC.",
                "orderCode": "A9EB1",
                "status": "INIT",
                "orderItems": [],
                "createdAt": "2020-12-28T11:06:43.996+00:00",
                "updatedAt": "2020-12-28T11:06:43.996+00:00"
            }
        ]
    }
```
### 5.2 View Order detail by uuid
```
    curl --location --request GET 'http://localhost:8082/order-service/order-detail/1155a65b-df95-45dc-aaa7-e0f6edef471d' \
    --header 'access-token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlMTg4MzYzMS00YTc5LTRhM2EtOWE4YS01MDNkMmJlNjBjOTUifQ.JmFpM_YRwnURM6D9gxYffqHuHQkx-a9oLs63OTWXHzg'
```
### 6 Fetch tracking activity by username(facebookID) or user ACTION (Activity can params: `{SEARCH, CLICK, ADD_CART, BUY}`)
```
    curl --location --request GET 'http://localhost:8084/tracking-service/by-customer/tai.nguyen@gmail.com'

    curl --location --request GET 'http://localhost:8084/tracking-service/by-activity/SEARCH'
```
Respone:
```
[
    {
        "id": "5fec3be7265cd621e1fb343e",
        "userName": "tai.nguyen@gmail.com",
        "activityName": "SEARCH",
        "actionContent": "IP12MN,IP12BLU"
    }
]
```
### 6.2 View all tracking activity
```
    curl --location --request GET 'http://localhost:8084/tracking-service/user-activity-all'
```
Respone:
```
[
    {
            "id": "5fec32ed929d3d0743166d8a",
            "userName": tai.nguyen@gmail.com,
            "activityName": "ADD_CART",
            "actionContent": "{\"productUUID\":\"90733264-45f1-11eb-b378-0242ac130002\",\"productName\":\"IPhone 12 mini\",\"productCode\":\"IP12MN\",\"price\":19000000}"
        },
        {
            "id": "5fec391cbe0b670ba38cd68e",
            "userName": tai.nguyen@gmail.com,
            "activityName": "SEARCH",
            "actionContent": "IP12MN,IP12BLU"
        },
        {
            "id": "5fec3968be0b670ba38cd68f",
            "userName": tai.nguyen@gmail.com,
            "activityName": "SEARCH",
            "actionContent": "IP12MN,IP12BLU"
        },
        {
            "id": "5fec3be7265cd621e1fb343e",
            "userName": "tai.nguyen@gmail.com",
            "activityName": "SEARCH",
            "actionContent": "IP12MN,IP12BLU"
        }
]
```

> Thanks for your review this description and project
> If has any question please contact me via skype: trhoangdat89 or email: trhoangdat@gmail.com
> Thanks
> Author by Dat Tran
