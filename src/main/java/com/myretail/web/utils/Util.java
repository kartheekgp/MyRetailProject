package com.myretail.web.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.myretail.web.beans.Message;
import com.myretail.web.beans.Product;
import com.myretail.web.beans.ProductResponse;
import com.myretail.web.constants.ProductConstants;

@Component
@PropertySource("classpath:config.properties")
public class Util {

	@Value("${product.details.url}")
	private String productURL;
	
	@Autowired
	SpringMongoConfig springMongoConfig;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	private static final Logger logger = Logger.getLogger(Util.class);
	private final String className = this.getClass().getName();
	
	//This method makes the call to the API to get the information of the product based on id
	public ProductResponse callHTTPGetRequestForProduct(Long id) throws IOException {
		logger.debug("Inside callHTTPGetRequestForProduct method in "+className+" where id is "+id);
		String productURLLocal = productURL;
		productURLLocal = productURLLocal.replace(ProductConstants.PRODUCT_ID_TO_REPLACE, id.toString());
	    //String result = restTemplate.getForObject(productURL, String.class);
	    ProductResponse productResponse = restTemplate().getForObject(productURLLocal, ProductResponse.class);
	    return productResponse;
	}
	
	//This method makes the call to the API to get the information of the product based on id and api url
	public ProductResponse callHTTPGetRequestForProductWithAPIURL(Long id,String productURLLocal) throws IOException {
		logger.debug("Inside callHTTPGetRequestForProductWithAPIURL method in "+className+" where id is "+id);
		productURLLocal = productURLLocal.replace(ProductConstants.PRODUCT_ID_TO_REPLACE, id.toString());
	    //String result = restTemplate.getForObject(productURL, String.class);
	    ProductResponse productResponse = restTemplate().getForObject(productURLLocal, ProductResponse.class);
	    return productResponse;
	}
	
	//This method does the following :-
	//1. Accepts product id
	//2. Calls callHTTPGetRequestForProduct to get the product information
	//3. Validates if a valid result is obtained
	//4. Returns product name if it is a valid result
	public String getProductName(Long id){
		logger.debug("Inside getProductName method in "+className+" where id is "+id);
		String productName = null;
		try {
			ProductResponse productResponse = callHTTPGetRequestForProduct(id);
			if(null != productResponse
		    		&& null != productResponse.getProductCompositeResponse()
		    		&& productResponse.getProductCompositeResponse().getItems().size() > 0
		    		&& null != productResponse.getProductCompositeResponse().getItems().get(0).getOnlineDescription()){
				productName = productResponse.getProductCompositeResponse().getItems().get(0).getOnlineDescription().getValue();
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productName;
	}
	
	//Checks if a string is null or blank
	public boolean isNullOrBlank(String s){
		logger.debug("Inside isNullOrBlank method in "+className+" where value of s is "+s);
	  return (s==null || s.trim().equals(ProductConstants.BLANK_STRING));
	}
	
	//This method does the following:-
	//1. Validates if it is a valid product by comparing name of the product in request with 
	//name of the product received after making the api call
	public boolean isValidProductWithId(Product product, Long id){
		logger.debug("Inside isValidProductWithId method in "+className+" where id is "+id);
		if(null != product){
			if(product.getName().equals(HtmlUtils.htmlUnescape(getProductName(id)))){
				return true;
			}
		}
		return false;
	}
	
	//Checks of the product is null or not
	public boolean isValidProduct(Product product){
		logger.debug("Inside isValidProduct method in "+className);
		if(null != product){
				return true;
		}
		return false;
	}
	
	//Checks the structure of the product request
	public boolean isCorrectProductStructure(Product product){
		logger.debug("Inside isCorrectProductStructure method in "+className);
		if(null != product.getName() 
				&& 00.00 != product.getCurrent_price().getValue()
				&& null != product.getCurrent_price().getCurrency_code()){
			return true;
		}
		return false;
	}
	
	//Used to set the number format like :- XX.XX
	public double setDecimalPlaces(double price){
		return Double.parseDouble(new DecimalFormat("#.##").format(price));
	}
	
	//Returns product structure with the error populated
	public Product returnInvalidProduct(String errorMessage){
		logger.debug("Inside returnInvalidProduct method in "+className+" and errorMessage is "+errorMessage);
		List<Message> message = new ArrayList<Message>();
		Product product = new Product();
		Message messageInfo = new Message();
		messageInfo.setMessage(errorMessage);
		message.add(messageInfo);
		product.setErrors(message);
		return product;
	}
	
	//Used to get the exception details
	public String getStackTrace(final Throwable throwable) {
		logger.error("Exception occurred.getStackTrace method in "+className+" is called");
	     StringWriter sw = new StringWriter();
	     PrintWriter pw = new PrintWriter(sw, true);//autoflush set to true
	     throwable.printStackTrace(pw);
	     return sw.getBuffer().toString();
	}
	
	//Returns the exception in error value of the product
	public Product returnExceptionModel(Exception ex) {
		logger.error(getStackTrace(ex));
		return returnInvalidProduct(ex.getMessage());
	}
	
	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	//Returns mongoTemplate
	public MongoTemplate returnMongoTemplate(){
		MongoTemplate montoTemplate = null;
		try {
			montoTemplate = springMongoConfig.mongoTemplate();
		} catch (Exception e) {
			logger.error("Exception occurred while calling returnMongoTemplate in "+className);
			logger.error(getStackTrace(e));
		}
		return montoTemplate;
	}
	
}
