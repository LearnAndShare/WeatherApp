package com.spring.weather;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
public class WeatherControllerTest {
	
	@Autowired
	  private WebApplicationContext wac;

	  private MockMvc mockMvc;
	  
	  @Before
	  public void setup() {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	   // MockMvcBuilders.standaloneSetup(WeatherController).build();
	  }
	  @Test
	  public void testGet(){
		  try{
			  RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");

			  ResultActions resultActions = this.mockMvc.perform(requestBuilder);
			  resultActions .andExpect(MockMvcResultMatchers.status().isOk());
			  resultActions.andExpect(MockMvcResultMatchers.view().name("weather"));
			  System.out.println("result is good!!");
		  }catch(Exception ex){
			  ex.printStackTrace();
		  }
	  }
	  
	  @Test
	  public void testPostForValidZip(){
		  try{
			  RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/getWeatherReport").param("zipCode", "98052");
			  
			  ResultActions resultActions = this.mockMvc.perform(requestBuilder);
			  resultActions .andExpect(MockMvcResultMatchers.status().isOk());
			  resultActions.andExpect(MockMvcResultMatchers.view().name("weather"));
			  System.out.println("result is good!!");
		  }catch(Exception ex){
			  ex.printStackTrace();
		  }
	  }
	  
	  @Test
	  public void testPostForZipWithAlphabets(){
		  try{
			  RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/getWeatherReport").param("zipCode", "AAA");
			  
			  ResultActions resultActions = this.mockMvc.perform(requestBuilder);
			  resultActions.andExpect(MockMvcResultMatchers.model().errorCount(1));
			  System.out.println("result is good!!");
		  }catch(Exception ex){
			  ex.printStackTrace();
		  }
	  }
	  
	  @Test
	  public void testPostForEmptyZipCode(){
		  try{
			  RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/getWeatherReport").param("zipCode", "");
			  
			  ResultActions resultActions = this.mockMvc.perform(requestBuilder);
			  resultActions.andExpect(MockMvcResultMatchers.model().errorCount(1));
			  System.out.println("result is good!!");
		  }catch(Exception ex){
			  ex.printStackTrace();
		  }
	  }
	  
	  @Test
	  public void testPostForZipCodeWithMoreThan5Chars(){
		  try{
			  RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/getWeatherReport").param("zipCode", "12345678");
			  
			  ResultActions resultActions = this.mockMvc.perform(requestBuilder);
			  resultActions.andExpect(MockMvcResultMatchers.model().errorCount(1));
			  System.out.println("result is good!!");
		  }catch(Exception ex){
			  ex.printStackTrace();
		  }
	  }
	  
	  
	  

}
