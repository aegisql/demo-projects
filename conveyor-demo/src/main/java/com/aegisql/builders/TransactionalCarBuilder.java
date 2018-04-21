package com.aegisql.builders;

public class TransactionalCarBuilder implements VehicleBuilder<Car> {

	private class $ {
		String make;
		String model;
		int year;
		String vin;
		String plateNumber;
		String color;
		int passengers;

		$() {
		}

		$($ c) {
			make = c.make;
			model = c.model;
			year = c.year;
			vin = c.vin;
			plateNumber = c.plateNumber;
			color = c.color;
			passengers = c.passengers;
		}

	}

	private $ $ = new $();
	private $ $$ = null;

	@Override
	public Car get() {
		if ($$ == null) {
			return null;
		} else {
			return new Car($$.make, $$.model, $$.year, $$.vin, $$.plateNumber, $$.color, $$.passengers);
		}
	}

	public TransactionalCarBuilder commit() {
		$$ = new $($);
		return this;
	}

	public TransactionalCarBuilder rollback() {
		if ($$ != null) {
			$ = new $($$);
		} else {
			$ = new $();
		}
		return this;
	}

	public TransactionalCarBuilder make(String make) {
		$.make = make;
		return this;
	}

	public TransactionalCarBuilder model(String model) {
		$.model = model;
		return this;
	}

	public TransactionalCarBuilder year(int year) {
		$.year = year;
		return this;
	}

	public TransactionalCarBuilder vin(String vin) {
		$.vin = vin;
		return this;
	}

	public TransactionalCarBuilder plateNumber(String plateNumber) {
		$.plateNumber = plateNumber;
		return this;
	}

	public TransactionalCarBuilder color(String color) {
		$.color = color;
		return this;
	}

	public TransactionalCarBuilder passengers(int passengers) {
		$.passengers = passengers;
		return this;
	}

}
