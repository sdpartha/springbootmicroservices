package com.infomover.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
@EnableBinding(Processor.class)
public class SpringCloudStreamProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamProcessorApplication.class, args);
	}
	
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public String processMessage(Message<String> inbound) {
		String message = "processed " + inbound.getPayload();
		return message;
	}
}
