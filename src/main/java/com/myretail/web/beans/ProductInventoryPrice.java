package com.myretail.web.beans;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myretail.web.utils.Util;

@Component
@XmlRootElement
@Document(collection = "ProductInventoryPrice")
public class ProductInventoryPrice {
	
	@Autowired
	Util util;
	
	private double value;
	private String currency_code;
	private Long id;
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

    @JsonIgnore
    @JsonProperty(value = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
