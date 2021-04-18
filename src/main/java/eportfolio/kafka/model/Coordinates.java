package eportfolio.kafka.model;

public class Coordinates {

	private double lon;
	private double lat;
	
	public Coordinates(double lon, double lat) {
		this.lon = lon;
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public String toString() {
		return "Latitude: " + this.lat + ", Longitude: " + this.lon;
	}
	
}
