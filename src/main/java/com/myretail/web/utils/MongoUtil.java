package com.myretail.web.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class MongoUtil {
	
	//Return the MongoOperations reference with MongoTemplate properties
	public MongoOperations returnMongoOperations(){
		ApplicationContext appContext = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) appContext.getBean("mongoTemplate");
		return mongoOperation;
	}

}
