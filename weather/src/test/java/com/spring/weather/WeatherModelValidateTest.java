package com.spring.weather;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import com.spring.weather.model.Weather;

public class WeatherModelValidateTest {
	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	@Test
    public void nullZipCode() {

        Weather w = new Weather();

        Set<ConstraintViolation<Weather>> constraintViolations = validator
                .validate(w);

        assertEquals(1, constraintViolations.size());
        
        assertEquals("may not be null", constraintViolations
                .iterator().next().getMessage());
    }

	
	@Test
    public void zipCodeWith6Chars() {

        Weather w = new Weather();
        w.setZipCode(123456);
        Set<ConstraintViolation<Weather>> constraintViolations = validator
                .validate(w);

        assertEquals(1, constraintViolations.size());
        
        assertEquals("Zip Code must be numeric and should contain five digits", constraintViolations
                .iterator().next().getMessage());
    }
	
	@Test
    public void zipCodeWith5Chars() {

        Weather w = new Weather();
        w.setZipCode(12345);
        Set<ConstraintViolation<Weather>> constraintViolations = validator
                .validate(w);

        assertEquals(0, constraintViolations.size());
        
       
    }

   

}
