package com.myretail.web.daoimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import com.myretail.web.beans.Product;
import com.myretail.web.beans.ProductInventoryPrice;
import com.myretail.web.dao.ProductDetailsDAO;
import com.myretail.web.utils.MongoUtil;
import com.myretail.web.utils.Util;

@Component
public class ProductDetailsDAOImpl implements ProductDetailsDAO{

	@Autowired
	MongoUtil mongoUtil;
	
	@Autowired
	Util util;
	
	private static final Logger logger = Logger.getLogger(ProductDetailsDAOImpl.class);
	
	//This method returns the price details of a product
	@Override
	public ProductInventoryPrice getProductInventoryPrice(Long id){
		final String method_Name = "getProductInventoryPrice";
		logger.debug("In the following method :-"+method_Name+ " and the value of id is " + id);
		MongoTemplate mongoTemplate = util.returnMongoTemplate();
		Query searchQuery = new Query(Criteria.where("id").is(id));
		ProductInventoryPrice productInventoryPrice = mongoTemplate.findOne(searchQuery, ProductInventoryPrice.class);
		return productInventoryPrice;
	}
	
	//This method updates the price of the product for the product passed
	@Override
	public Product updateProductPrice(Product product){
		final String method_Name = "updateProductPrice";
		logger.debug("In the following method :-"+method_Name+ " and the value of id is " + product.getId());
		MongoTemplate mongoTemplate = util.returnMongoTemplate();
		Query query = new Query(Criteria.where("id").is(product.getId()));
		Update update=new Update();
		update.set("value",util.setDecimalPlaces(product.getCurrent_price().getValue()));
		mongoTemplate.updateMulti(query,update,ProductInventoryPrice.class);
		//Query again to get the latest details
		product.setCurrent_price(getProductInventoryPrice(product.getId()));
		return product;
	}
}
