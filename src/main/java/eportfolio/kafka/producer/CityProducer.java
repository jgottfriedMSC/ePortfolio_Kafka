package eportfolio.kafka.producer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eportfolio.kafka.model.City;
import eportfolio.kafka.model.Coordinates;

public class CityProducer {

	public static void main(String[] args) throws Exception {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("cities.json");
		JSONParser parser = new JSONParser();
		
		String topicName = "cities";
		
		// Producer Configuration
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "eportfolio.kafka.serializer.CitySerializer");
		
		Producer<String, City> producer = new KafkaProducer<>(props);
		
		try {
			JSONArray cities = (JSONArray) parser.parse(new InputStreamReader(is));
			
			for (Object c : cities) {
				JSONObject city = (JSONObject) c;
				
				String id = city.get("id").toString();
				String name = (String) city.get("name");
				String state = (String) city.get("state");
				String country = (String) city.get("country");
				
				JSONObject coord = (JSONObject) city.get("coord");
				
				double lon = (double) coord.get("lon");
				double lat = (double) coord.get("lat");
				
				Coordinates coordObj = new Coordinates(lon, lat);
				
				City cityObj = new City(id, name, state, country, coordObj);
				
				producer.send(new ProducerRecord<>(topicName, "CITY", cityObj)).get();
			}
			System.out.println("CityProducer completed.");
			producer.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

}
