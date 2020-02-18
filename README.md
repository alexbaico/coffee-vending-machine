# Coffee vending machine

This is (now) a stateful app that simulates a coffee vending machine.
It provides a view where you select the type of coffee which is required, and the extra options in case you want any (these are not required). 
Then you insert the tickets you are gonna pay with, and confirm your order.

The app calculates if the money inputted is enough to pay the total price of the coffee, and, in case it's more, calculates 
the change to return to the customer. Then it reduces the inventory of all the products chosen in the order by 1 unit

It also includes an API REST to modify the Products available and the Inventory of each.
You can use the Swagger UI interface to interact with it at [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Pre-requisites

  maven - version 3.* recommended

## Running the app

  run ```mvn spring-boot:run```
  
  enter [localhost:8080](http://localhost:8080)

## Running the tests

  run ```mvn test```
  
## Built With

* [Spring-boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [Thymeleaf](https://www.thymeleaf.org/)
* [Jquery](https://jquery.com/)
* [Bootstrap](https://getbootstrap.com/)
* H2 databse
* [Swagger](https://swagger.io/)