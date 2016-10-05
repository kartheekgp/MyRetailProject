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
<br/>1. User provides the id in the GET request for which the product details are to be fetched.
<br/>2. A REST call is made to get the product name.
<br/>3. If product name exists, then a call is made to MongDB to get the details of price for the product.
<br/>4. Result is displayed to the user in the form of a JSON.
<br/>5. In any step the validation fails, appropriate error message will be sent as response.
<br/>6. In case of any exceptions, appropriate exception message/stack will be sent as response.

<br/>For PUT request to update the details of price.
<br/>1. User provides the id in the API request for which the product details are to be fetched.
<br/>2. Provides the product body in PUT request for the product to be updated. - It is assumed that the id in api and body are same.
<br/>3. If product name matches with the name from the REST call, it is assumed that it is the right product.
<br/>4. If the structure of the request is correct, the product price is updated.
<br/>5. Updated product is sent to the user as response.
<br/>6. In any step the validation fails, appropriate error message will be sent as response.
<br/>7. In case of any exceptions, appropriate exception message/stack will be sent as response.

## Motivation

<br/>This project is part of Target Recruitment process.

## Installation

<br/>JAVA Configuration
<br/>JAVA 8 is required for this app. Please refer references link on the same.

<br/>MongoDB Installation/configuration
<br/>MongoDB with the name "test" has to be created. By default, it must have host as 127.0.0.1 and port as 27017.
<br/>MongoDB version used in this project :- **3.2.9**
<br/>Run the following command in the console to create a table :- 
<br/>db.createCollection("ProductInventoryPrice");

<br/>To insert the data in the collection, please fire the following queries :-
<br/>db.ProductInventoryPrice.insert({
<br/>     "_id" : 22,
<br/>    "value" : 13.49,
<br/>    "currency_code" : "USD"
<br/> });

 <br/>db.ProductInventoryPrice.insert({
 <br/>    "_id" : 22,
 <br/>   "value" : 13.49,
 <br/>   "currency_code" : "USD"
 <br/>});
<br/>
 <br/>db.ProductInventoryPrice.insert({
 <br/>   "_id" : 13860428,
 <br/>  "value" : 9.49,
 <br/>   "currency_code" : "USD"
 <br/>});
<br/>
 <br/>db.ProductInventoryPrice.insert({
 <br/>   "_id" : 16483589,
 <br/>   "value" : 49.49,
 <br/>   "currency_code" : "USD"
 <br/>});
<br/>
 <br/>db.ProductInventoryPrice.insert({
 <br/>   "_id" : 16696652,
 <br/>   "value" : 44,
 <br/>   "currency_code" : "USD"
 <br/>});
<br/>
<br/>db.ProductInventoryPrice.insert({
<br/>    "_id" : 16752456,
<br/>    "value" : 44.0,
<br/>    "currency_code" : "USD"
<br/> });

<br/> db.ProductInventoryPrice.insert({
<br/>    "_id" : 15643793,
<br/>    "value" : 1099.99,
<br/>    "currency_code" : "USD"
<br/> });

<br/> db.ProductInventoryPrice.insert({
<br/>    "_id" : 15117729,
<br/>    "value" : 19.49,
<br/>    "currency_code" : "USD"
<br/> });

<br/> After following the above steps, we must have a collection with data for product pricing.
<br/>
<br/> Indexing the collection with id field:-
<br/> db.ProductInventoryPrice.ensureIndex({"_id":1});
<br/>
<br/> Note:- Please use a client like Robomongo or MongoVUE to easily run these. Robomongo was used for this project.
<br/>
<br/>Build tool used :- **MAVEN**
<br/>
<br/>Project installation :-
<br/>1. Unzip or download the project.
<br/>Note :- Eclipse IDE build used in this project is :- **4.5.2.M20160212-1500**
<br/>
<br/>Running the project:-
<br/>1. Select Base directory :- ${workspace_loc:/MyRetailProject}
<br/>2. Perform maven clean :- clean package
<br/>3. Perform maven build :- eclipse:eclipse
<br/>4. Server used : Jetty. Select the project and run using :- jetty:run
<br/>5. Please checck the ## API Reference and ## Tests to run the project

<br/>## API Reference

<br/>After the instalation is successful, please try accessing this page which will give you a brief idea of what the application does :-
<br/>http://<host>:<port>/MyRetail/

<br/>Controller  : com.myretail.web.controller.ProductController
<br/>1. Read product details API
<br/>Description : Reading the product details API to get the details of a product for the provided id.
<br/>Method 		: GET
<br/>Response 	: JSON representation of Product.java
<br/>API Call 	: http://<host>:<port>/MyRetail/products/{id}
<br/>
<br/>Example :-
<br/>http://<host>:<port>/MyRetail/products/16752456
<br/>
<br/>Result :-
<br/>{
<br/>  "id": 16752456,
<br/>  "name": "Lego® Super Heroes The Tumbler 76023",
<br/>  "current_price": {
<br/>    "value": 44.0,
<br/>    "currency_code": "USD"
<br/>  }
<br/>}
<br/>This api call can be accessed from a browser. **Google Chrome Version 53.0.2785.116 (64-bit)** was used in this case.
<br/>
<br/>2. Update product price details API
<br/>Description : Updating the product price of a product for the provided id and return the updated response.
<br/>Method 		: PUT
<br/>Response 	: JSON representation of Product.java
<br/>API Call 	: http://<host>:<port>/MyRetail/products/{id}
<br/>Body        : {
<br/>				  "id": NUMBER,
<br/>				  "name": STRING,
<br/>				  "current_price": {
<br/>				    "value": NUMBER WITH DECIMAL,
<br/>				    "currency_code": STRING
<br/>				  }
<br/>				}
<br/>Example :-
<br/>http://<host>:<port>/MyRetail/products/16752456
<br/>Body        : {
<br/>				  "id": 16752456,
<br/>				  "name": "Lego® Super Heroes The Tumbler 76023",
<br/>				  "current_price": {
<br/>				    "value": 500.0,
<br/>				    "currency_code": "USD"
<br/>				  }
<br/>				}
<br/>
<br/>Result 	:- 		{
<br/>				  "id": 16752456,
<br/>				  "name": "Lego® Super Heroes The Tumbler 76023",
<br/>				  "current_price": {
<br/>				    "value": 500.0,
<br/>				    "currency_code": "USD"
<br/>				  }
<br/>				}
<br/>
<br/>This api call can be called from any browser/addon which provides the funtion to make a PUT call. 
<br/>Google Chrome Version **53.0.2785.116 (64-bit)** with **Insomnia** addon was used in this case.
<br/>
## Tests
<br/>
<br/>All the test class's names end with Test and reside in src/test/java within their respective packages.
<br/>Project name is MyRetailProject.

<br/>Integration testing                        :- com.myretail.controllertest.ProductControllerTest
<br/>This test is performed to check if the controllers are returning the desired response code.
<br/>Tests done for both GET and PUT request with different cases.

<br/>Unit testing of Service class 		:- com.myretail.servicetest.ProductServiceTest
<br/>This test is performed to check the sanctity of the service methods.
<br/>Serices get the request from controller, make a request to dao and send the response back to controllers.

<br/>Unit testing of util class 	        :- com.myretail.utiltest.UtilTest
<br/>This test is performed to check the sanctity of the util methods.

<br/>After project setup here are the steps to run the tests.
<br/>1. Select Base directory :- ${workspace_loc:/MyRetailProject}
<br/>2. Run the following Maven command :- -Dtest=*Test clean test 
<br/>3. Check in console/logs to see if all the tests have passed.
<br/>
## References
<br/>
https://docs.mongodb.com/manual/reference/
http://mockito.org/
http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html
https://projects.spring.io/spring-framework/
https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
<br/>
## License
<br/>
For internal purpose only.
