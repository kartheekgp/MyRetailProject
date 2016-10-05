package com.myretail.web.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "Product")
public class Product {
		
		private long id;
		private String name;
		private ProductInventoryPrice current_price;
		private List<Message> errors;
		
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public ProductInventoryPrice getCurrent_price() {
			return current_price;
		}

		public void setCurrent_price(ProductInventoryPrice current_price) {
			this.current_price = current_price;
		}

		public List<Message> getErrors() {
			return errors;
		}

		public void setErrors(List<Message> errors) {
			this.errors = errors;
		}

	}

