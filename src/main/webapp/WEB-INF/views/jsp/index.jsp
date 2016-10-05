<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyRetail application</title>
</head>
<body>
<h2>Welcome to MyRetail application</h2>
<br/>This application is used to fetch the relevant products information.
<br/>This project deals with the myRetail retailer product api. 
<br/>It has two apis exposed:- 
<br/>1. GET request to get the details of a product.
<br/>2. PUT request to update the details of price.
<br/>
<br/>Discussed in detail below :-
<br/><h3>Read product details API</h3>
<br/>Description : Reading the product details API to get the details of a product for the provided id.
<br/>Method 		: GET
<br/>Response 	: JSON representation of Product.java
<br/>API Call 	: http://<host>:<port>/MyRetail/products/{id}
<br/>
<br/>Example :-
<br/>http://<host>:<port>/MyRetail/products/16752456
<br/>
<br/>Result :-
<br/>&nbsp;&nbsp;&nbsp;{
<br/>&nbsp;&nbsp;&nbsp;&nbsp;  "id": 16752456,
<br/>&nbsp;&nbsp;&nbsp;&nbsp; "name": "Lego® Super Heroes The Tumbler 76023",
<br/>&nbsp;&nbsp;&nbsp;&nbsp;  "current_price": {
<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    "value": 44.0,
<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    "currency_code": "USD"
<br/>&nbsp;&nbsp;&nbsp;&nbsp;  }
<br/>&nbsp;&nbsp;&nbsp;}
<br/>This api call can be accessed from a browser. <b>Google Chrome Version 53.0.2785.116 (64-bit)</b> was used in this case.
<br/>
<br/><h3>Update product price details API</h3>
<br/>Description : Updating the product price of a product for the provided id and return the updated response.
<br/>Method 		: PUT
<br/>Response 	: JSON representation of Product.java
<br/>API Call 	: http://<host>:<port>/MyRetail/products/{id}
<br/>Body        : 
<br/>&nbsp;&nbsp;&nbsp;{
<br/>&nbsp;&nbsp;&nbsp;&nbsp;				  "id": NUMBER,
<br/>&nbsp;&nbsp;&nbsp;&nbsp;				  "name": STRING,
<br/>&nbsp;&nbsp;&nbsp;&nbsp;				  "current_price": {
<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				    "value": NUMBER WITH DECIMAL,
<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				    "currency_code": STRING
<br/>&nbsp;&nbsp;&nbsp;&nbsp;				  }
<br/>&nbsp;&nbsp;&nbsp;				}
<br/>
<br/>Example :-
<br/>http://<host>:<port>/MyRetail/products/16752456
<br/>Body        : 
<br/>&nbsp;&nbsp;&nbsp;{
<br/>&nbsp;&nbsp;&nbsp;&nbsp;				  "id": 16752456,
<br/>&nbsp;&nbsp;&nbsp;&nbsp;				  "name": "Lego® Super Heroes The Tumbler 76023",
<br/>&nbsp;&nbsp;&nbsp;&nbsp;				  "current_price": {
<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			    "value": 500.0,
<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				    "currency_code": "USD"
<br/>&nbsp;&nbsp;&nbsp;&nbsp;				  }
<br/>&nbsp;&nbsp;&nbsp;				}
<br/>
<br/>
<br/>Result 	:- 		
<br/>&nbsp;&nbsp;&nbsp;{
<br/>&nbsp;&nbsp;&nbsp;&nbsp;				  "id": 16752456,
<br/>&nbsp;&nbsp;&nbsp;&nbsp;				  "name": "Lego® Super Heroes The Tumbler 76023",
<br/>&nbsp;&nbsp;&nbsp;&nbsp;				  "current_price": {
<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				    "value": 500.0,
<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				    "currency_code": "USD"
<br/>&nbsp;&nbsp;&nbsp;&nbsp;				  }
<br/>&nbsp;&nbsp;&nbsp;				}
<br/>
This api call can be called from any browser/addon which provides the funtion to make a PUT call. 
Google Chrome Version <b>53.0.2785.116 (64-bit)</b> with <b>Insomnia</b> addon was used in this case.
</body>
</html>