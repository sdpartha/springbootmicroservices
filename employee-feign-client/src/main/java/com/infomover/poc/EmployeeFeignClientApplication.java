package com.infomover.poc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
@EnableCircuitBreaker
public class EmployeeFeignClientApplication {
	
	private static final Log log = LogFactory.getLog(EmployeeFeignClientApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeFeignClientApplication.class, args);
	}

	@Autowired
	HelloClient client;
	
	@Autowired
	UserClient userClient;
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(@RequestBody Object userEntity) {
		return userClient.createUser(userEntity);
	}
	
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public Object getAllUsers() {
		System.out.println("raesteatad");
		return userClient.getAllUser();
	}

	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "userNotFound")
	public Object getUsers(@PathVariable("id") String id) {
		return userClient.getUser(id);
	}
	
	@RequestMapping(value = "/updateUser/{id}")
	public Object updateUser(@PathVariable("id") String id, @RequestBody Object userEntity ) {
		System.out.print("sadjkfhksjd");
		return userClient.updateUser(id, userEntity);
	}

	
	@RequestMapping("/hello")
	@HystrixCommand(fallbackMethod = "defaultGreeting")
	public Object hello() {
		log.info("Employee Feign request");
		return client.hello();
	}
	
	@RequestMapping("/hello/{name}")
	@HystrixCommand(fallbackMethod = "defaultGreeting")
	public String hello(@PathVariable String name) {
		log.info("Employee Feign request");
		return client.hello(name);
	}
	
	public String defaultGreeting() {
		return "Default greeting";
	}
	
	public String defaultGreeting(String name) {
		return "Default greeting to "+name;
	}
	
	public String userNotFound(String id) {
		return "User with id="+id+" not found";
	}
	
	
	@FeignClient(name = "employee-eureka-client")
	interface UserClient {
		
		@RequestMapping("/createUser")
		String createUser(@RequestBody Object userEntity);
		
		@RequestMapping("/getAllUsers")
		Object getAllUser();
		
		@RequestMapping("/getUser/{id}")
		Object getUser(@PathVariable("id") String id);
		
		@RequestMapping("/updateUser/{id}")
		Object updateUser(@PathVariable("id") String id, @RequestBody Object userEntity);
	}

	@FeignClient(name = "employee-eureka-client")
	interface HelloClient {
		
		@RequestMapping("/hello")
		String hello();

		@RequestMapping("/hello-again/{name}")
		String hello(@PathVariable("name") String name);
	}

}
