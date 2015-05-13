package com.spring.weather.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
public class Weather {
    @Digits(integer = 5, fraction = 0, message = "Zip Code must be numeric and should contain five digits")
    @Min(5)
    @NotNull
	private Integer zipCode;

    private String cityName;
    private String stateName;
    private String tempFarheneit;
    public String getTempFarheneit() {
		return tempFarheneit;
	}
	public void setTempFarheneit(String tempFarheneit) {
		this.tempFarheneit = tempFarheneit;
	}

	private String errorDescription;
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	public String toString(){
		return "hello weather";
	}
}
