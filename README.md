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





#1
curl --location --request POST 'http://localhost:8081/gateway-service/customer-registration' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userName": "tai.nguyen",
	"facebookId": "tai.nguyen@gmail.com",
	"facebookToken": "e10adc3949ba59abbe56e05711111111",
	"firstName": "Tai",
	"lastName": "Nguyen"
}'
Response:
{
    "code": 0,
    "message": null,
    "content": "Registration success"
}
#2
curl --location --request GET 'http://localhost:8081/gateway-service/login' \
--header 'facebook-id: tai.nguyen@gmail.com' \
--header 'facebook-token: e10adc3949ba59abbe56e05711111111' \
--header 'Content-Type: application/json' \
Response
{
    "code": 0,
    "message": null,
    "content": "Loggin success",
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDkxNDg0OTgsInN1YiI6IjM1MjYzMGRjLTNmMjMtNDRhYy1iMDY4LTdhNmNmNmZiNDk1NyJ9.vArXbxVSKlkSDhE-aClq7tU1POqRhqa_ArbPNitCsTw"
}

#2.1
curl --location --request GET 'http://localhost:8081/gateway-service/products?codes=IP12MN,IP12BLU' \
--header 'facebook-id: tai.nguyen@gmail.com' \
--header 'facebook-token: e10adc3949ba59abbe56e05711111111'
Response
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

#3
curl --location --request POST 'http://localhost:8081/gateway-service/add-cart-item' \
--header 'customer-uuid: a518d032-45f3-11eb-b378-0242ac130002' \
--header 'Content-Type: application/json' \
--data-raw '{
	"productUUID": "90733264-45f1-11eb-b378-0242ac130002",
	"productName": "IPhone 12 mini",
	"productCode": "IP12MN",
	"price":"19000000"
}'
Response
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

#3.1
curl --location --request GET 'http://localhost:8081/gateway-service/shopping-cart-of-customer' \
--header 'customer-uuid: a518d032-45f3-11eb-b378-0242ac130002'

#4
curl --location --request POST 'http://localhost:8081/gateway-service/create-order' \
--header 'customer-uuid: a518d032-45f3-11eb-b378-0242ac130002'
Response
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

#4.1 view all order of customer
curl --location --request GET 'http://localhost:8081/gateway-service/order-detail-of-customer' \
--header 'customer-uuid: a518d032-45f3-11eb-b378-0242ac130002'

Response
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
