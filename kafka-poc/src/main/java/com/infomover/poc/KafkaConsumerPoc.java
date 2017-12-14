package com.infomover.poc;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.util.SerializationUtils;

public class KafkaConsumerPoc {

	public static void main(String[] args) throws InterruptedException {
		consumeMessage();

	}
	
	private static void  consumeMessage() throws InterruptedException {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("zookeeper", "localhost:2181");
		props.put("group.id", "test");		
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
		KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("sometopic123"));
		while (true) {
			ConsumerRecords<String, byte[]> records = consumer.poll(100);
			for (ConsumerRecord<String, byte[]> record : records)
				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), ((DataObject)SerializationUtils.deserialize(record.value())).getMessage());
			Thread.sleep(5000);
		}
	    

	}

}
