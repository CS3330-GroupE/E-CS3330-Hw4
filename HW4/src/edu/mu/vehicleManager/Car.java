package edu.mu.vehicleManager;

public class Car extends Vehicle {



	public Car(String brand, String make, long modelYear, double price, VehicleColor color, FuelType fuelType,
			double mileage, double mass, int cylinders, double gasTankCapacity, StartMechanism startType) {
		super(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
			
	}

	@Override
	public double calculateMaintenanceCost(double distance){
		
		return maintenanceCost = distance * mass * (currentYear-modelYear) * cylinders * 0.0005;
	}

	@Override
	public double calculateFuelEfficiency(double distance, double fuelPrice) {
		
		return fuelEfficiency = cylinders * gasTankCapacity * fuelPrice / distance * 0.003;
	}
	
	@Override
	public void startEngine() {
		System.out.println("PUSHSTART");
	}

}
