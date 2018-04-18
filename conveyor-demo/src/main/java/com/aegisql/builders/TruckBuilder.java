package com.aegisql.builders;

public class TruckBuilder implements VehicleBuilder<Truck> {

	private String make;
	private String model;
	private int year;
	private String vin;
	private String plateNumber;
	private String color;
	private int axles;
	private int maxWeight;

	@Override
	public Truck get() {
		return new Truck(make, model, year, vin, plateNumber, color, axles, maxWeight);
	}
	public TruckBuilder make(String make) {
		this.make = make;
		return this;
	}

	public TruckBuilder model(String model) {
		this.model = model;
		return this;
	}

	public TruckBuilder year(int year) {
		this.year = year;
		return this;
	}

	public TruckBuilder vin(String vin) {
		this.vin = vin;
		return this;
	}

	public TruckBuilder plateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
		return this;
	}

	public TruckBuilder color(String color) {
		this.color = color;
		return this;
	}
	
	public TruckBuilder axles(int axles) {
		this.axles = axles;
		return this;
	}

	public TruckBuilder maxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
		return this;
	}

}
