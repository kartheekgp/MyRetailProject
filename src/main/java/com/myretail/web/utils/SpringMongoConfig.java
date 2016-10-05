package com.myretail.web.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;


@Configuration
@PropertySource("classpath:config.properties")
public class SpringMongoConfig {

	//Fetch MongoDB name from config file
	@Value("${mongo.db.name}")
	private String mongodbName;
	
	//Fetch host for MongoDB from config file
	@Value("${mongo.db.server}")
	private String mongoDBServer;
	
	//Return MongoTemplate
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient(mongoDBServer),mongodbName);
		return mongoTemplate;
	}
	
	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
