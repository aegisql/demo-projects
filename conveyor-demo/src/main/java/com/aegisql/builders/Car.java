package com.aegisql.builders;

public final class Car extends Vehicle {

	private final int passengers;
	
	public Car(String make, String model, int year, String vin, String plateNumber, String color,int passengers) {
		super(make, model, year, vin, plateNumber, color);
		this.passengers = passengers;
	}

	public int getPassengers() {
		return passengers;
	}
	
	
}
