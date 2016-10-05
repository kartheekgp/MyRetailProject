package com.myretail.web.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;
import com.myretail.web.beans.Message;
import com.myretail.web.beans.Product;
import com.myretail.web.beans.ProductInventoryPrice;
import com.myretail.web.constants.ProductConstants;
import com.myretail.web.dao.ProductDetailsDAO;
import com.myretail.web.service.ProductService;
import com.myretail.web.utils.Util;

@Component
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDetailsDAO productDetailsDAO;
	
	@Autowired
	Util util;

	public ProductServiceImpl() {
	}
	
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
	private final String className = this.getClass().getName();

	/*This method does the following :-
	 * 1. Make the call for fetching the name of the product
	 * 2. If it is a valid product, fetch the price from MONGODB
	 * 3. If price exists, populate and return the result
	 * 4. If any details are missing, populate respective error message in product and return
	 * */
	@Override
	public Product getProductDetails(Long id) {
		logger.debug("Inside getProductDetails method in "+className+" where id is "+id);
		Product product = new Product();//productDetailsDAO.getProductDetailsBean(id);
		//To get product name from the target api call as of now
		String productName = getProductName(id);
		List<Message> message = new ArrayList<Message>();
		ProductInventoryPrice current_price = null;
		//Call the pricing details only for valid products
		if(!util.isNullOrBlank(productName)){
			current_price = getProductInventoryPrice(id);
			if(null == current_price){
				//Product price are missing
				product = util.returnInvalidProduct(ProductConstants.PRICE_DETALS_MISSING);
			}else{
				product.setId(id);
				//Unescape special characters before sending the response.
				product.setName(HtmlUtils.htmlUnescape(productName));
				product.setCurrent_price(current_price);
			}
		}else{
			//Product id is invalid
			product = util.returnInvalidProduct(ProductConstants.INVALID_PRODUCT_ID);
		}
		//If the messages are present, then add them to the bean
		if(message.size() > 0){
			product.setErrors(message);
		}
		return product;
	}
	
	/*
	 * Gets the product price for the id supplied
	 * */
	@Override
	public ProductInventoryPrice getProductInventoryPrice(Long id){
		logger.debug("Inside getProductInventoryPrice method in "+className+" where id is "+id);
		return productDetailsDAO.getProductInventoryPrice(id);
	}
	
	/*
	 * Gets the product name for the id supplied
	 * */
	@Override
	public String getProductName(Long id){
		logger.debug("Inside getProductName method in "+className+" where id is "+id);
			return util.getProductName(id);
	}
	
	/*
	 * Updates the product price for the id supplied
	 * */
	@Override
	public Product updateProductPrice(Product product){
		logger.debug("Inside updateProductPrice method in "+className);
		return productDetailsDAO.updateProductPrice(product);
	}
	
	/* Accepts the request from the controller
	 * If a valid request is supplied, a valid Product is returned.
	 * */
	@Override
	public Product updatePrice(Product product, Long id){
		logger.debug("Inside updatePrice method in "+className+" where id is "+id);
		//Check if it is a valid entry
		if(util.isValidProduct(product)){
			if(util.isCorrectProductStructure(product)){
				if(util.isValidProductWithId(product, id)){
					//This is a valid product, hence update and send back the correct request
					return updateProductPrice(product);
				}else{
					//Product request name and id dont match
					return util.returnInvalidProduct(ProductConstants.INCORRECT_PRODUCT_NAME_OR_ID);
				}
			}else{
				//Product request structure is not correct
				return util.returnInvalidProduct(ProductConstants.INCORRECT_PRODUCT_STRUCTURE);
			}
		}else{
			//If the request is null, it means it is a get request or a PUT without a body, just display the correct product
			return getProductDetails(id);
		}
	}
	
}
