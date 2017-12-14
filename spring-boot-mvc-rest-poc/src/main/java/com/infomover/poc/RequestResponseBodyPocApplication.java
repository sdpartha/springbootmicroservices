package com.infomover.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Controller
@RequestMapping("/customer")
public class RequestResponseBodyPocApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(RequestResponseBodyPocApplication.class, args);
	}
	
	@RequestMapping(value="/getMyCity", method=RequestMethod.POST)
	public @ResponseBody OutputData getMyCity(@RequestBody InputData inputData) {
		OutputData outputData = new OutputData();
		outputData.setInputData(inputData);
		outputData.setCity("te");
		return outputData;
		
	}
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	
	    configurer.favorPathExtension(true).
	            favorParameter(true).
	            parameterName("mediaType").
	            ignoreAcceptHeader(false).
	            useJaf(false).
	            defaultContentType(MediaType.APPLICATION_JSON).
	            mediaType("xml", MediaType.APPLICATION_XML).
	            mediaType("json", MediaType.APPLICATION_JSON);
	    
	}

}

class InputData {
	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}

class OutputData {
	private InputData inputData;
	private String city;

	public InputData getInputData() {
		return inputData;
	}

	public void setInputData(InputData inputData) {
		this.inputData = inputData;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
