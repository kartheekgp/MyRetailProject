package com.myretail.web.beans;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"product_composite_response"
})
public class ProductResponse {

	@JsonProperty("product_composite_response")
	private ProductCompositeResponse productCompositeResponse = new ProductCompositeResponse();

	@JsonProperty("product_composite_response")
	public ProductCompositeResponse getProductCompositeResponse() {
		return productCompositeResponse;
	}

	@JsonProperty("product_composite_response")
	public void setProductCompositeResponse(ProductCompositeResponse productCompositeResponse) {
		this.productCompositeResponse = productCompositeResponse;
	}

}
