package com.myretail.web.dao;

import org.springframework.stereotype.Component;

import com.myretail.web.beans.Product;
import com.myretail.web.beans.ProductInventoryPrice;

@Component
public interface ProductDetailsDAO {

	public ProductInventoryPrice getProductInventoryPrice(Long id);
	public Product updateProductPrice(Product product);
}
