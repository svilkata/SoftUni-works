# Instructions:

### Using JDK 17

#### webfluxservice app
1. Run it locally on port 9001

---------------------

#### webflux-client app
2. Then initialize the MongoDB via the `./local/docker-compose.yaml` file
3. Run the webflux-client app locally on port 8080 - the ExchangeRateInit will call the reactive web client which will call our webfluxservice app on port 9001 - some records will be saved in the db
4. The webflux-client app is made both with rest controller approach and with router approach
4. You can run Postman and execute:
*    GET on localhost:8080/ratescontroller
*    GET on localhost:8080/ratesrouter
--------------
*    GET localhost:8080/ratescontroller/1
*    GET localhost:8080/ratesrouter/1
--------------
*    POST localhost:8080/ratescontroller
*    POST localhost:8080/ratesrouter