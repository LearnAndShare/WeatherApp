package com.spring.weather.service;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.weather.model.Response;
import com.spring.weather.model.Weather;
@Service("weatherServiceJSON")
public class WeatherInfoJSONServiceImpl implements WeatherInfoService{
	private static final Logger logger = LoggerFactory.getLogger(WeatherInfoJSONServiceImpl.class);
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Value("#{weatherservice['weatherserviceurl']}")
    private String weatherServiceLUrl;

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public Object getWeatherResponse(Object obj){
		Response resp = null;
		Weather w = null;
		if(obj instanceof Weather){
			try{
				w = (Weather)obj;
				Object[] wsArgs = {new Integer(w.getZipCode())};
				String url = MessageFormat.format(weatherServiceLUrl, wsArgs);
				logger.debug("URl after substiuting zipcode webservice url!!!" + weatherServiceLUrl);
				resp = restTemplate.getForObject(url, Response.class);
				if(resp == null){
	                 w.setErrorDescription("No record found for zipcode");
				}
			}catch(Exception ex) {
                w.setErrorDescription("Error calling Weather Service" + ex.getMessage());
			}
		} 
		return resp;
	}
	
	
	    
}
