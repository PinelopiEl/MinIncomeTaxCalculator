package model;

public class Address {
	private final String country;
	private final String city;
	private final String street;
	private final String number;

	public Address(String country, String city, String street, String number) {
		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getNumber() {
		return number;
	}

	public String toString() {
		return (country + " " + city + " " + street + " " + number);
	}
}
