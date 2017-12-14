package com.infomover.poc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
@EnableBinding(Source.class)
public class SpringCloudStreamSourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamSourcesApplication.class, args);
	}

	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT)
	public MessageSource<String> timerMessageSource() {
		return new MessageSource<String>() {

			private int messageNo = 1;
			@Override
			public Message<String> receive() {
				return new GenericMessage<>("Event - "+ messageNo++);
			}
		};
	}
}
