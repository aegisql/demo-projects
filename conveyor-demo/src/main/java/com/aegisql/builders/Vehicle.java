package com.aegisql.builders;

public abstract class Vehicle {
	
	private final String make;
	private final String model;
	private final int year;
	private final String vin;
	private final String plateNumber;
	private final String color;
	
	public Vehicle(String make, String model, int year, String vin, String plateNumber, String color) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.vin = vin;
		this.plateNumber = plateNumber;
		this.color = color;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public String getVin() {
		return vin;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public String getColor() {
		return color;
	}
	
	
}
