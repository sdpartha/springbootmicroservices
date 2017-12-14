package com.infomover.poc;

import java.util.Properties;

import org.apache.commons.lang.SerializationUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerPoc {

	public static void main(String[] args) throws InterruptedException {
		int inc = 0;
		produceMessage();

	}

	private static void produceMessage() throws InterruptedException {

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("retries", 0);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

		Producer<String, byte[]> producer = new KafkaProducer<>(props);
		int inc = 0;
		while(true) {
			inc++;
			producer.send(new ProducerRecord<String, byte[]>("sometopic123", "key-"+inc, SerializationUtils.serialize(new DataObject().setMessage("Message"+inc))));
			Thread.sleep(2000);
		}
		

	}

}
