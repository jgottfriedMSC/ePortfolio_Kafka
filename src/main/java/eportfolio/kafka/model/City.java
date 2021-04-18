package eportfolio.kafka.model;

public class City {

	private String id;
	private String name;
	private String state;
	private String country;
	private Coordinates coords;
	
	public City(String id, String name, String state, String country, Coordinates coords) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.country = country;
		this.coords = coords;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Coordinates getCoords() {
		return coords;
	}

	public void setCoords(Coordinates coords) {
		this.coords = coords;
	}
	
	public String toString() {
		return "{ ID: " + this.id + ", Name: " + this.name + ", State: " + this.state + ", Country: " + this.country + ", Coordinates [ " + this.coords.toString() + " ] }"; 
	}
}
