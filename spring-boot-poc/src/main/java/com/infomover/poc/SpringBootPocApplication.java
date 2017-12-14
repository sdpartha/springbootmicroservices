package com.infomover.poc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(FooProperties.class)
public class SpringBootPocApplication {
	
	@Autowired
	private FooProperties fooProperties;
	
	@Value("${sample.property}")
	private  String sampleProperty;
	
	@Autowired
	private DataSource dataSource;
	

	public static void main(String[] args) {		
		SpringApplication app = new SpringApplication(SpringBootPocApplication.class);
        ConfigurableApplicationContext ctx = app.run(args);
        SpringBootPocApplication springBootPocApplication= ctx.getBean(SpringBootPocApplication.class);
        springBootPocApplication.printExternalizeConfig();
//        springBootPocApplication.pringTypeSafeProperty();
//        springBootPocApplication.printDataSource();
		
	}
	
	private void printExternalizeConfig(){
		System.out.println(sampleProperty);
	}
	
	private void pringTypeSafeProperty(){
		System.out.println(fooProperties.getValue());
	}
	
	
	private void printDataSource(){
		System.out.println(dataSource);
	}	
	
	
}

@ConfigurationProperties("foo")
class FooProperties {
	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	
}


