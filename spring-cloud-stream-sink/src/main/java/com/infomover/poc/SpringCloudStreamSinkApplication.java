package com.infomover.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@SpringBootApplication
@EnableBinding(Sink.class)
public class SpringCloudStreamSinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamSinkApplication.class, args);
	}

	@StreamListener(Sink.INPUT)
	public void transform(Message<?> inbound) {
		
		System.out.println("Completed: "+inbound.getPayload());
	}
}
