package com.spring.weather.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentObservation {
	
	
	@JsonProperty("temp_f")
    private String tempF;

    
    @JsonProperty("temp_c")
    private String tempC;

    @JsonProperty("display_location")
    private DisplayLocation displayLocation;

	public String getTempF() {
		return tempF;
	}


	public void setTempF(String tempF) {
		this.tempF = tempF;
	}


	public String getTempC() {
		return tempC;
	}


	public void setTempC(String tempC) {
		this.tempC = tempC;
	}


	public DisplayLocation getDisplayLocation() {
		return displayLocation;
	}


	public void setDisplayLocation(DisplayLocation displayLocation) {
		this.displayLocation = displayLocation;
	}

}
