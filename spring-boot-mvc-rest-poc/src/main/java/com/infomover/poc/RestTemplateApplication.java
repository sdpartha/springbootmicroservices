package com.infomover.poc;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@SpringBootApplication
public class RestTemplateApplication {

	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
		restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);
	}
	
	@RequestMapping("/restTemplateCheck")
	public Employee employee(){
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:9090/employee/24")
				.port(8080);
		
		
		Employee employee  = restTemplate.getForObject(builder.toUriString(), Employee.class);
		employee.setName(employee.getName()+" some value");
		return employee;
		
	}
	
	

}
