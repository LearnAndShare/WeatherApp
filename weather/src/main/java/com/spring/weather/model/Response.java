package com.spring.weather.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonRootName("response")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Response {

	private String version;
	private String termsofService;
	@JsonProperty("current_observation")
	private CurrentObservation currentObservation;
	
	private Error error;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTermsofService() {
		return termsofService;
	}

	public void setTermsofService(String termsofService) {
		this.termsofService = termsofService;
	}

	public CurrentObservation getCurrentObservation() {
		return currentObservation;
	}

	public void setCurrentObservation(CurrentObservation currentObservation) {
		this.currentObservation = currentObservation;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}


}
