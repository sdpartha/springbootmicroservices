package com.infomover.poc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@RestController
public class XmlMessageConverterApplication extends WebMvcConfigurerAdapter {
	@Autowired
	MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter;

	public static void main(String[] args) {
		SpringApplication.run(XmlMessageConverterApplication.class, args);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		converters.add(mappingJackson2XmlHttpMessageConverter);
		super.configureMessageConverters(converters);
	}

	@RequestMapping("/getEmployee")
	public Employee getEmployee() {
		return new Employee().setName("Jaydatt").setLastName("Desai");
	}
}

class Employee {
	private String name;
	private String lastName;

	public String getName() {
		return name;
	}

	public Employee setName(String name) {
		this.name = name;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Employee setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
}
