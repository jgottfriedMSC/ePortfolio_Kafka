package eportfolio.kafka.serializer;

import java.nio.ByteBuffer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import eportfolio.kafka.model.City;
import eportfolio.kafka.model.Coordinates;

public class CityDeserializer implements Deserializer<City> {

	private String encoding = "UTF8";
	
	@Override
	public City deserialize(String topic, byte[] data) {
		try {
			if (data == null) {
				System.out.println("Null received at deserialization");
				return null;
			}
			
			ByteBuffer buffer = ByteBuffer.wrap(data);
			
			int sizeOfCityId = buffer.getInt();
			byte[] cityIdBytes = new byte[sizeOfCityId];
			buffer.get(cityIdBytes);
			String deserializedId = new String(cityIdBytes, encoding);
			
			int sizeOfCityName = buffer.getInt();
			byte[] cityNameBytes = new byte[sizeOfCityName];
			buffer.get(cityNameBytes);
			String deserializedName = new String(cityNameBytes, encoding);
			
			int sizeOfCityState = buffer.getInt();
			byte[] cityStateBytes = new byte[sizeOfCityState];
			buffer.get(cityStateBytes);
			String deserializedState = new String(cityStateBytes, encoding);
			
			int sizeOfCityCountry = buffer.getInt();
			byte[] cityCountryBytes = new byte[sizeOfCityCountry];
			buffer.get(cityCountryBytes);
			String deserializedCountry = new String(cityCountryBytes, encoding);
			
			double lat = buffer.getDouble();
			double lon = buffer.getDouble();
			
			return new City(deserializedId, deserializedName, deserializedState, deserializedCountry, new Coordinates(lat,lon));
		} catch (Exception e) {
			throw new SerializationException("Error when deserialize byte[] to City");
		}
	}

}
