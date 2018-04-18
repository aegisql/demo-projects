package com.aegisql.builders;

public class CarBuilder implements VehicleBuilder<Car> {

	private String make;
	private String model;
	private int year;
	private String vin;
	private String plateNumber;
	private String color;
	private int passengers;
	
	@Override
	public Car get() {
		return new Car(make, model, year, vin, plateNumber, color, passengers);
	}

	public CarBuilder make(String make) {
		this.make = make;
		return this;
	}

	public CarBuilder model(String model) {
		this.model = model;
		return this;
	}

	public CarBuilder year(int year) {
		this.year = year;
		return this;
	}

	public CarBuilder vin(String vin) {
		this.vin = vin;
		return this;
	}

	public CarBuilder plateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
		return this;
	}

	public CarBuilder color(String color) {
		this.color = color;
		return this;
	}

	public CarBuilder passengers(int passengers) {
		this.passengers = passengers;
		return this;
	}

}
