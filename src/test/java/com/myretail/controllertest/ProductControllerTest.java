package com.myretail.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.myretail.web.beans.Product;
import com.myretail.web.beans.ProductInventoryPrice;
import com.myretail.web.controller.ProductController;
import com.myretail.web.service.ProductService;

public class ProductControllerTest {

	@Mock
    private ProductService productService;
 
    @InjectMocks
    private ProductController productController;
 
    private MockMvc mockMvc;
    
    private Product productToTest = new Product();
    
    private String productjson;
    
    @Before
    public void setup() {
    	
        // Process mock annotations
        MockitoAnnotations.initMocks(this);
 
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        
    }
    
    //Create the product to be tested
    @Before
    public void createProductToJsonString(){
        productToTest = new Product();
     	ProductInventoryPrice current_price = new ProductInventoryPrice();
     	long id = 16752456;
     	current_price.setCurrency_code("USD");
     	current_price.setValue((double) 188.00);
     	current_price.setId(id);
     	productToTest.setId(id);
     	productToTest.setName("Lego® Super Heroes The Tumbler 76023");
     	productToTest.setCurrent_price(current_price);
     	Gson gson = new Gson();
        productjson = gson.toJson(productToTest);
    }
    
    //Test cases for GET requests
    //For a valid numeric request
    @Test
	public void forGETWithNumber() throws Exception {
		this.mockMvc.perform(get("/products/123")).andExpect(status().isOk());
	}
    
    //For a request without id
    @Test
	public void forGETWithNoId() throws Exception {
		this.mockMvc.perform(get("/products/")).andExpect(status().isNotFound());
	}
    
    //For a blank/empty request    
    @Test
	public void forGETWithNothing() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isNotFound());
	}
    
    //For a request wherein a string is sent instead of a number
    @Test
   	public void forGETWithAlphabet() throws Exception {
   		this.mockMvc.perform(get("/products/abc")).andExpect(status().isBadRequest());
   	}
    
    //Test cases for PUT requests - without a body
    //For a valid numeric request
    @Test
	public void forPUTWithNumber() throws Exception {
		this.mockMvc.perform(put("/products/123")).andExpect(status().isOk());
	}
    
    //For a request without id
    @Test
	public void forPUTWithNoId() throws Exception {
		this.mockMvc.perform(put("/products/")).andExpect(status().isNotFound());
	}
    
    //For a blank/empty request
    @Test
	public void forPUTWithNothing() throws Exception {
		this.mockMvc.perform(put("/")).andExpect(status().isNotFound());
	}

    //For a request wherein a string is sent instead of a number
    @Test
   	public void forPUTWithAlphabet() throws Exception {
   		this.mockMvc.perform(put("/products/abc")).andExpect(status().isBadRequest());
   	}
    
    //PUT request with body
    //For a valid numeric request
    @Test
	public void forPUTBodyWithNumber() throws Exception {
		this.mockMvc.perform(put("/products/16752456").contentType(MediaType.APPLICATION_JSON).content(productjson)).andExpect(status().isOk());
	}

    //For a request without id
    @Test
	public void forPUTBodyWithNoId() throws Exception {
		this.mockMvc.perform(put("/products/").contentType(MediaType.APPLICATION_JSON).content(productjson)).andExpect(status().isNotFound());
	}

    //For a blank/empty request
    @Test
	public void forPUTBodyWithNothing() throws Exception {
		this.mockMvc.perform(put("/").contentType(MediaType.APPLICATION_JSON).content(productjson)).andExpect(status().isNotFound());
	}

    //For a request wherein a string is sent instead of a number
    @Test
   	public void forPUTBodyWithAlphabet() throws Exception {
   		this.mockMvc.perform(put("/products/abc").contentType(MediaType.APPLICATION_JSON).content(productjson)).andExpect(status().isBadRequest());
   	}
    
}
