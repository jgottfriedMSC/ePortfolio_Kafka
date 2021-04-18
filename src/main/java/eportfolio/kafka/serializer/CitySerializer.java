package eportfolio.kafka.serializer;

import java.nio.ByteBuffer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import eportfolio.kafka.model.City;

public class CitySerializer implements Serializer<City> {

	private String encoding = "UTF8";

	@Override
	public byte[] serialize(String topic, City data) {

		int sizeOfCityId;
		int sizeOfCityName;
		int sizeOfCityState;
		int sizeOfCityCountry;
		byte[] serializedCityId;
		byte[] serializedCityName;
		byte[] serializedCityState;
		byte[] serializedCityCountry;

		try {

			if (data == null)
				return null;

			serializedCityId = data.getId().getBytes(encoding);
			sizeOfCityId = serializedCityId.length;

			serializedCityName = data.getName().getBytes(encoding);

			sizeOfCityName = serializedCityName.length;

			serializedCityState = data.getState().getBytes(encoding);
			sizeOfCityState = serializedCityState.length;

			serializedCityCountry = data.getCountry().getBytes(encoding);
			sizeOfCityCountry = serializedCityCountry.length;

			ByteBuffer buffer = ByteBuffer
					.allocate(4 + sizeOfCityId + 4 + sizeOfCityName + 4 + sizeOfCityState + 4 + sizeOfCityCountry+8+8);

			buffer.putInt(sizeOfCityId);
			buffer.put(serializedCityId);
			buffer.putInt(sizeOfCityName);
			buffer.put(serializedCityName);
			buffer.putInt(sizeOfCityState);
			buffer.put(serializedCityState);
			buffer.putInt(sizeOfCityCountry);
			buffer.put(serializedCityCountry);
			buffer.putDouble(data.getCoords().getLat());
			buffer.putDouble(data.getCoords().getLon());

			return buffer.array();

		} catch (Exception e) {
			throw new SerializationException("Error when serializing City to byte[]");
		}

	}

}
