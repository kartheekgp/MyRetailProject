package com.myretail.web.service;

import org.springframework.stereotype.Component;

import com.myretail.web.beans.Product;
import com.myretail.web.beans.ProductInventoryPrice;

@Component
public interface ProductService {

	public Product getProductDetails(Long id);
	public ProductInventoryPrice getProductInventoryPrice(Long id);
	public String getProductName(Long id);
	public Product updateProductPrice(Product product);
	public Product updatePrice(Product product, Long id);
}
