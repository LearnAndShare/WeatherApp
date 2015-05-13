package com.spring.weather;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.spring.weather.model.CurrentObservation;
import com.spring.weather.model.DisplayLocation;
import com.spring.weather.model.Response;
import com.spring.weather.model.Weather;
import com.spring.weather.service.WeatherInfoService;

/**
 * Handles requests for the weather Data application 
 * 
 *  */
@Controller
public class WeatherController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
	@Autowired
	WeatherInfoService weatherServiceJSON;
	
	
	/**
	 * Returns empty Weather object if zipcode is invalid
	 * If zipcode is valid it calls weather webservice to return data
	 * @param weather
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "getWeatherReport", method = RequestMethod.POST)
	public String getWeatherReport(@Valid Weather weather, BindingResult result){
		if (result.hasErrors()) {
			logger.debug("Errors in binding result"+ result.toString());
			return "weather";
		} else{
			
			logger.debug("Calling webservice for getting data related to zip code");
			Object wsresp = weatherServiceJSON.getWeatherResponse(weather);
			if(wsresp != null){
				Response resp = (Response) wsresp;
				if(resp != null) {
					populateWeatherFieldsFromResponse(weather,resp);
				}
			} 
			
			if(weather.getErrorDescription() !=null){
				result.rejectValue("zipCode", "zipcode.notfound");

			}
			return "weather";
		}
	}
	
	
	/**
	 * Populate weather object from Webservice response
	 * @param weather
	 * @param wsresp
	 */
	private void populateWeatherFieldsFromResponse(Weather weather,Response wsresp) {
		CurrentObservation cb = wsresp.getCurrentObservation();
		if(cb != null) {
			DisplayLocation dispLoc = cb.getDisplayLocation();
			if(dispLoc != null) {
				weather.setCityName(dispLoc.getCity());
				weather.setStateName(dispLoc.getState());
			}
			weather.setTempFarheneit(cb.getTempF());
		} else {
			weather.setErrorDescription("no data found for zip code");
		}
		
	}


	public void getDataFromWebservice(Weather weather){
		try{
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://api.wunderground.com/api/ed044d75b91fb500/conditions/q/";
			String cityCode = weather.getZipCode().toString()+".json";
			url = url+cityCode;
			Response r = restTemplate.getForObject(url, Response.class);
			System.out.println(r.getCurrentObservation().getDisplayLocation().getState());

			weather.setCityName(r.getCurrentObservation().getDisplayLocation().getCity());
			weather.setStateName(r.getCurrentObservation().getDisplayLocation().getState());
			weather.setTempFarheneit(r.getCurrentObservation().getTempF());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * Load the weather page for get request
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String loadWeatherPage(ModelMap m) {
        m.addAttribute("weather", new Weather());
       return "weather";
	}
	
	
	
}
