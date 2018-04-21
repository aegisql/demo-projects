package com.aegisql.builders;

public class VehicleShop {

	public static void main(String[] args) {

		CarBuilder carBuilder = new CarBuilder()
				.make("Volvo")
				.model("s90")
				.color("black")
				.vin("YV1MS382652049843")
				.passengers(4)
				.plateNumber("ABC-123")
				.year(2016);
		
		Car car = carBuilder.get();
		System.out.println(car);
		
		TransactionalCarBuilder rCarBuilder = new TransactionalCarBuilder()
				.make("Volvo")
				.model("s90")
				.color("black")
				.vin("YV1MS382652049843")
				.passengers(4)
				.plateNumber("ABC-123")
				.year(2016);
		
		System.out.println("Data not applied until commit called: "+rCarBuilder.get());
		rCarBuilder.commit();
		System.out.println("Now data available: "+rCarBuilder.get());
	
		rCarBuilder
				.make("Toyota")
				.model("Camry")
				.color("black")
				.vin("TV1M23846556749843")
				.passengers(4)
				.plateNumber("J4K-928")
				.year(2017);
		System.out.println("Changes not applied: "+rCarBuilder.get());
		rCarBuilder.commit();
		System.out.println("Now changes available: "+rCarBuilder.get());
		
		rCarBuilder
				.make("Volvo")
				.model("s90")
				.color("black")
				.vin("YV1MS382652049843")
				.passengers(4)
				.plateNumber("ABC-123")
				.year(2016);		
		rCarBuilder.rollback();
		rCarBuilder
				.color("white").commit();
		System.out.println("Only last change matters: "+rCarBuilder.get());
		
		
	}

}
