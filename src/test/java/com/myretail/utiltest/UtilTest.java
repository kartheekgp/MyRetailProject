package com.myretail.utiltest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.myretail.web.beans.Product;
import com.myretail.web.beans.ProductInventoryPrice;
import com.myretail.web.beans.ProductResponse;
import com.myretail.web.utils.Util;

public class UtilTest {

	@InjectMocks
	private Util util;
	
	private String apiUrl = null;
	
	//Set the api call to be done
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	    apiUrl = "https://api.target.com/products/v3/--PRODUCTID--?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz";
	}
 
	//To check if the product is valid
    @Test
	public void isValidProductPassTest() throws Exception {
    	Product product = new Product();
    	ProductInventoryPrice current_price = new ProductInventoryPrice();
    	long id = 16752456;
    	current_price.setCurrency_code("USD");
    	current_price.setValue((double) 18800);
    	product.setId(id);
    	product.setName("Lego® Super Heroes The Tumbler 76023");
    	product.setCurrent_price(current_price);
    	assertTrue(util.isValidProduct(product));
	}
    
    //To check if a product is invalid or has no values in it
    @Test
	public void isValidProductNewProductTestOne() throws Exception {
    	Product product = new Product();
    	assertTrue(util.isValidProduct(product));
	}
    
    //To check if a product is null
    @Test
	public void isValidProductNewProductTestTwo() throws Exception {
    	Product product = null;
    	assertFalse(util.isValidProduct(product));
	}
    
    //To check if the product has correct structure
    @Test
	public void isCorrectProductStructureTestPass() throws Exception {
    	Product product = new Product();
    	ProductInventoryPrice current_price = new ProductInventoryPrice();
    	long id = 16752456;
    	current_price.setCurrency_code("USD");
    	current_price.setValue((double) 18800);
    	product.setId(id);
    	product.setName("Lego® Super Heroes The Tumbler 76023");
    	product.setCurrent_price(current_price);
    	assertTrue(util.isCorrectProductStructure(product));
	}
    
    //To check if the product has incorrect/does not have all required values
    @Test
	public void isCorrectProductStructureTestFail() throws Exception {
    	Product product = new Product();
    	ProductInventoryPrice current_price = new ProductInventoryPrice();
    	long id = 16752456;
    	current_price.setCurrency_code("USD");
    	current_price.setValue((double) 18800);
    	product.setId(id);
    	product.setCurrent_price(current_price);
    	assertFalse(util.isCorrectProductStructure(product));
	}
    
    //To check for the string wherein it is null 
    @Test
	public void isNullOrBlankTestOne() throws Exception {
    	assertTrue(util.isNullOrBlank(null));
	}
    
    //To check for the string wherein it is blank
    @Test
	public void isNullOrBlankTestTwo() throws Exception {
    	assertTrue(util.isNullOrBlank(""));
	}
    
    //To check for the string wherein it contains only spaces
    @Test
	public void isNullOrBlankTestThree() throws Exception {
    	assertTrue(util.isNullOrBlank("  "));
	}
    
    //To check for a valid string
    @Test
	public void isNullOrBlankTestFour() throws Exception {
    	assertFalse(util.isNullOrBlank("x"));
	}
    
    //To check for a string with spaces in the beginning
    @Test
	public void isNullOrBlankTestFive() throws Exception {
    	assertFalse(util.isNullOrBlank("  x"));
	}
    
    //To check for a string with spaces at the end
    @Test
	public void isNullOrBlankTestSix() throws Exception {
    	assertFalse(util.isNullOrBlank("x    "));
	}
    
    //To check if the api call returns valid response for a valid id
    @Test
	public void getProductNameTestOne() throws Exception {
    	long id = 16752456;//Provide valid id
    	boolean result = false;
    	ProductResponse productResponse = util.callHTTPGetRequestForProductWithAPIURL(id,apiUrl);
    	if(null != productResponse
	    		&& null != productResponse.getProductCompositeResponse()
	    		&& productResponse.getProductCompositeResponse().getItems().size() > 0
	    		&& null != productResponse.getProductCompositeResponse().getItems().get(0).getOnlineDescription()){
    		result = true;
	    }
    	assertTrue(result);
    }
    
    //To check if the api call returns invalid response for an invalid id
    @Test
	public void getProductNameTestTwo() throws Exception {
    	long id = 1;//Provide invalid id
    	boolean result = false;
    	ProductResponse productResponse = util.callHTTPGetRequestForProductWithAPIURL(id,apiUrl);
    	if(null != productResponse
	    		&& null != productResponse.getProductCompositeResponse()
	    		&& productResponse.getProductCompositeResponse().getItems().size() > 0
	    		&& null != productResponse.getProductCompositeResponse().getItems().get(0).getOnlineDescription()){
    		result = true;
	    }
    	assertFalse(result);
    }
}
