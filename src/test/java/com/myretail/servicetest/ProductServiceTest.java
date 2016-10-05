package com.myretail.servicetest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.myretail.web.beans.Product;
import com.myretail.web.beans.ProductInventoryPrice;
import com.myretail.web.daoimpl.ProductDetailsDAOImpl;
import com.myretail.web.serviceimpl.ProductServiceImpl;
import com.myretail.web.utils.Util;

public class ProductServiceTest {

	@Mock
	private ProductDetailsDAOImpl productDetailsDAO;
	
	@Mock
	Util util;
 
    @InjectMocks
    private ProductServiceImpl productService = new ProductServiceImpl();
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    //Tests if the correct inventory is fetched
    @Test
	public void getPriceInventoryTestOne() throws Exception {
    	ProductInventoryPrice current_price = new ProductInventoryPrice();
    	long id = 16752456;
    	current_price.setCurrency_code("USD");
    	current_price.setValue((double) 188.00);
    	current_price.setId(id);
    	when(productDetailsDAO.getProductInventoryPrice(id)).thenReturn(current_price);
    	ProductInventoryPrice current_priceTest = productService.getProductInventoryPrice(id);
        verify(productDetailsDAO, times(1)).getProductInventoryPrice(id);
        assertEquals(current_priceTest, current_price);
    }
    
    //Tests if the correct inventory is fetched
    @Test
	public void getPriceInventoryTestTwo() throws Exception {
    	ProductInventoryPrice current_price = new ProductInventoryPrice();
    	long id = 1;
    	current_price.setCurrency_code("USD");
    	current_price.setValue((double) 200.00);
    	current_price.setId(id);
    	when(productDetailsDAO.getProductInventoryPrice(id)).thenReturn(current_price);
    	ProductInventoryPrice current_priceTest = productService.getProductInventoryPrice(id);
        verify(productDetailsDAO, times(1)).getProductInventoryPrice(id);
        assertEquals(current_priceTest, current_price);
    }
    
    //Tests if the correct product name is fetched
    @Test
	public void getProductNameTestOne() throws Exception {
    	long id = 16752456;
    	String productName = "Lego® Super Heroes The Tumbler 76023";
    	when(util.getProductName((long) 16752456)).thenReturn(productName);
    	String ProuctNameTest = productService.getProductName(id);
        verify(util, times(1)).getProductName(id);
        assertEquals(ProuctNameTest, productName);
    }
    
    //Tests if the incorrect product name is fetched for an invalid id
    @Test
	public void getProductNameTestTwo() throws Exception {
    	long id = 1;
    	String productName = "Lego® Super Heroes The Tumbler 76023";
    	String productNameTest = productService.getProductName(id);
    	when(util.getProductName(id)).thenReturn(productName);
        verify(util, times(1)).getProductName(id);
        assertNotEquals(productNameTest, productName);
    }

}
