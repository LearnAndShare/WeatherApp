package com.spring.weather;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.spring.weather.model.Response;
import com.spring.weather.model.Weather;
import com.spring.weather.service.WeatherInfoJSONServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
public class WebserviceJSONTest {
	private MockRestServiceServer mockWeatherRestServer;
	@Autowired
    WeatherInfoJSONServiceImpl weatherServiceJSON;
	
	@Before
    public void setUp() {
		mockWeatherRestServer = MockRestServiceServer.createServer(
                (RestTemplate) weatherServiceJSON.getRestTemplate());
    }
	
	@Test
    public void testReturnCorrectResponse() throws Exception {

        final String responseJSON = new Scanner(new File("src/test/resources/samplews.json")).next();
        
        		//FileUtils.readFileToString(new File("src/test/resources/SampleXMLResponse.xml"));
        Weather request = new Weather();
        request.setZipCode(98052);

        mockWeatherRestServer
                .expect(requestTo("http://api.wunderground.com/api/ed044d75b91fb500/conditions/q/98052.json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseJSON, MediaType.APPLICATION_JSON));
       weatherServiceJSON.getWeatherResponse(request);
       
      
    }
	
	@After
    public void tearDown() {
		mockWeatherRestServer = null;
		weatherServiceJSON = null;
    }


}
