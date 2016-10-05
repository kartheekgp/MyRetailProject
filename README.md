MyRetail Application
====================
<br/>
<br/>This project deals with the myRetail retailer product api. 
<br/>It has two apis exposed:- 
<br/>1. GET request to get the details of a product.
<br/>2. PUT request to update the details of price.

<br/>
## Flow overview

<br/>For GET request to get the details of a product
1. User provides the id in the GET request for which the product details are to be fetched.
2. A REST call is made to get the product name.
3. If product name exists, then a call is made to MongDB to get the details of price for the product.
4. Result is displayed to the user in the form of a JSON.
5. In any step the validation fails, appropriate error message will be sent as response.
6. In case of any exceptions, appropriate exception message/stack will be sent as response.

For PUT request to update the details of price.
1. User provides the id in the API request for which the product details are to be fetched.
2. Provides the product body in PUT request for the product to be updated. - It is assumed that the id in api and body are same.
3. If product name matches with the name from the REST call, it is assumed that it is the right product.
4. If the structure of the request is correct, the product price is updated.
5. Updated product is sent to the user as response.
6. In any step the validation fails, appropriate error message will be sent as response.
7. In case of any exceptions, appropriate exception message/stack will be sent as response.

## Motivation

This project is part of Target Recruitment process.

## Installation

JAVA Configuration
JAVA 8 is required for this app. Please refer references link on the same.

MongoDB Installation/configuration
MongoDB with the name "test" has to be created. By default, it must have host as 127.0.0.1 and port as 27017.
MongoDB version used in this project :- **3.2.9**
Run the following command in the console to create a table :- 
db.createCollection("ProductInventoryPrice");

To insert the data in the collection, please fire the following queries :-
db.ProductInventoryPrice.insert({
     "_id" : 22,
    "value" : 13.49,
    "currency_code" : "USD"
 });

 db.ProductInventoryPrice.insert({
     "_id" : 22,
    "value" : 13.49,
    "currency_code" : "USD"
 });

 db.ProductInventoryPrice.insert({
    "_id" : 13860428,
    "value" : 9.49,
    "currency_code" : "USD"
 });

 db.ProductInventoryPrice.insert({
    "_id" : 16483589,
    "value" : 49.49,
    "currency_code" : "USD"
 });

 db.ProductInventoryPrice.insert({
    "_id" : 16696652,
    "value" : 44,
    "currency_code" : "USD"
 });

 db.ProductInventoryPrice.insert({
    "_id" : 16752456,
    "value" : 44.0,
    "currency_code" : "USD"
 });

 db.ProductInventoryPrice.insert({
    "_id" : 15643793,
    "value" : 1099.99,
    "currency_code" : "USD"
 });

 db.ProductInventoryPrice.insert({
    "_id" : 15117729,
    "value" : 19.49,
    "currency_code" : "USD"
 });

 After following the above steps, we must have a collection with data for product pricing.

 Indexing the collection with id field:-
 db.ProductInventoryPrice.ensureIndex({"_id":1});

 Note:- Please use a client like Robomongo or MongoVUE to easily run these. Robomongo was used for this project.

Build tool used :- **MAVEN**

Project installation :-
1. Unzip or download the project.
Note :- Eclipse IDE build used in this project is :- **4.5.2.M20160212-1500**

Running the project:-
1. Select Base directory :- ${workspace_loc:/MyRetailProject}
2. Perform maven clean :- clean package
3. Perform maven build :- eclipse:eclipse
4. Server used : Jetty. Select the project and run using :- jetty:run
5. Please checck the ## API Reference and ## Tests to run the project

## API Reference

After the instalation is successful, please try accessing this page which will give you a brief idea of what the application does :-
http://<host>:<port>/MyRetail/

Controller  : com.myretail.web.controller.ProductController
1. Read product details API
Description : Reading the product details API to get the details of a product for the provided id.
Method 		: GET
Response 	: JSON representation of Product.java
API Call 	: http://<host>:<port>/MyRetail/products/{id}

Example :-
http://<host>:<port>/MyRetail/products/16752456

Result :-
{
  "id": 16752456,
  "name": "Lego® Super Heroes The Tumbler 76023",
  "current_price": {
    "value": 44.0,
    "currency_code": "USD"
  }
}
This api call can be accessed from a browser. **Google Chrome Version 53.0.2785.116 (64-bit)** was used in this case.

2. Update product price details API
Description : Updating the product price of a product for the provided id and return the updated response.
Method 		: PUT
Response 	: JSON representation of Product.java
API Call 	: http://<host>:<port>/MyRetail/products/{id}
Body        : {
				  "id": NUMBER,
				  "name": STRING,
				  "current_price": {
				    "value": NUMBER WITH DECIMAL,
				    "currency_code": STRING
				  }
				}
Example :-
http://<host>:<port>/MyRetail/products/16752456
Body        : {
				  "id": 16752456,
				  "name": "Lego® Super Heroes The Tumbler 76023",
				  "current_price": {
				    "value": 500.0,
				    "currency_code": "USD"
				  }
				}

Result 	:- 		{
				  "id": 16752456,
				  "name": "Lego® Super Heroes The Tumbler 76023",
				  "current_price": {
				    "value": 500.0,
				    "currency_code": "USD"
				  }
				}

This api call can be called from any browser/addon which provides the funtion to make a PUT call. 
Google Chrome Version **53.0.2785.116 (64-bit)** with **Insomnia** addon was used in this case.

## Tests

All the test class's names end with Test and reside in src/test/java within their respective packages.
Project name is MyRetailProject.

Integration testing of controllers 	:- com.myretail.controllertest.ProductControllerTest
This test is performed to check if the controllers are returning the desired response code.
Tests done for both GET and PUT request with different cases.

Unit testing of Service class 		:- com.myretail.servicetest.ProductServiceTest
This test is performed to check the sanctity of the service methods.
Serices get the request from controller, make a request to dao and send the response back to controllers.

Unit testing of util class 			:- com.myretail.utiltest.UtilTest
This test is performed to check the sanctity of the util methods.

After project setup here are the steps to run the tests.
1. Select Base directory :- ${workspace_loc:/MyRetailProject}
2. Run the following Maven command :- -Dtest=*Test clean test 
3. Check in console/logs to see if all the tests have passed.

## References

https://docs.mongodb.com/manual/reference/
http://mockito.org/
http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html
https://projects.spring.io/spring-framework/
https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

## License

For internal purpose only.
