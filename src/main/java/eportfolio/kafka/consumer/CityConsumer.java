package eportfolio.kafka.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import eportfolio.kafka.model.City;

public class CityConsumer {

	public static void main(String[] args) throws Exception {
		String topicName = "cities";
		String groupName = "cityGroup";

		// Consumer Configuration
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", groupName);
		props.put("auto.offset.reset", "earliest");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "eportfolio.kafka.serializer.CityDeserializer");

		KafkaConsumer<String, City> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(topicName));

		while (true) {
			ConsumerRecords<String, City> records = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, City> record : records) {
				System.out.println("{ City ID: " + record.value().getId() + ", City Name: " + record.value().getName()
						+ ", City State: " + record.value().getState() + ", City Country: "
						+ record.value().getCountry() + ", City Coordinates: [ Lat: "
						+ record.value().getCoords().getLat() + ", Lon: " + record.value().getCoords().getLon()
						+ " ] }");

			}
		}
	}

}
