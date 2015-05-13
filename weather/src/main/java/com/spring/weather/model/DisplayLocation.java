package com.spring.weather.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisplayLocation {
	
	
    private String city;
    @JsonProperty("state_name")
    private String state;
    
    
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
