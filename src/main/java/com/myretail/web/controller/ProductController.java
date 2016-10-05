package com.myretail.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.myretail.web.beans.Product;
import com.myretail.web.service.ProductService;
import com.myretail.web.utils.Util;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	Util util;

	private static final Logger logger = Logger.getLogger(ProductController.class);
	private final String className = this.getClass().getName();
	
	/*
	 * This controller accepts both GET and POST requests
	 * Is the entry point of the application.
	 * If a valid request is supplied, a valid Product in the form of a JSON is returned
	 * */
	@RequestMapping(value = "/products/{id}", method = { RequestMethod.GET, RequestMethod.PUT }, produces = "application/json")
	public Product getProductDetails(@PathVariable("id") Long id, @RequestBody(required = false) Product product) {
		logger.debug("Call made :- /products/{id} and the value of id is "+id);
		//Send request to update price which will act on the request accordingly
		return productService.updatePrice(product,id);
	}
	
	//To handle Exception exception in this controller
	@ExceptionHandler({Exception.class})
	public Product handleException(Exception ex) {
		logger.error("EXCEPTION OCCURRED in "+className);
		return util.returnExceptionModel(ex);
	}

	//To handle ArithmeticException exception in this controller
	@ExceptionHandler({ArithmeticException.class})
	public Product handleArithmeticException(Exception ex) {
		logger.error("Arithmetic EXCEPTION OCCURRED in "+className);
		return util.returnExceptionModel(ex);
	}

	//To handle MethodArgumentTypeMismatchException exception in this controller
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public Product handleMethodArgumentTypeMismatchException(Exception ex) {
		logger.error("MethodArgumentTypeMismatchException OCCURRED in "+className);
		return util.returnExceptionModel(ex);
	}

	//To handle InvalidFormatException exception in this controller
	@ExceptionHandler({InvalidFormatException.class})
	public Product handleInvalidFormatException(Exception ex) {
		logger.error("MethodArgumentTypeMismatchException OCCURRED in "+className);
		return util.returnExceptionModel(ex);
	}
}