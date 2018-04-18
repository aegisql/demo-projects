package com.aegisql.builders;

public final class Truck extends Vehicle {

	private final int axles;
	private final int maxWeight;
	
	public Truck(String make, String model, int year, String vin, String plateNumber, String color, int axles, int maxWeight) {
		super(make, model, year, vin, plateNumber, color);
		this.axles = axles;
		this.maxWeight = maxWeight;
	}

	public int getAxles() {
		return axles;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

}
