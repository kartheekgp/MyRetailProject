package com.myretail.web.beans;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"type",
"value",
"type_description"
})
public class AlternateDescription {

@JsonProperty("type")
private String type;
@JsonProperty("value")
private String value;
@JsonProperty("type_description")
private String typeDescription;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The type
*/
@JsonProperty("type")
public String getType() {
return type;
}

/**
* 
* @param type
* The type
*/
@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

/**
* 
* @return
* The value
*/
@JsonProperty("value")
public String getValue() {
return value;
}

/**
* 
* @param value
* The value
*/
@JsonProperty("value")
public void setValue(String value) {
this.value = value;
}

/**
* 
* @return
* The typeDescription
*/
@JsonProperty("type_description")
public String getTypeDescription() {
return typeDescription;
}

/**
* 
* @param typeDescription
* The type_description
*/
@JsonProperty("type_description")
public void setTypeDescription(String typeDescription) {
this.typeDescription = typeDescription;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
