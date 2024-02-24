package edu.mu.vehicleManager;

public class SUV extends Vehicle {

	public SUV(String brand, String make, long modelYear, double price, VehicleColor color, FuelType fuelType,
			double mileage, double mass, int cylinders, double gasTankCapacity, StartMechanism startType) {
		super(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double calculateMaintenanceCost(double distance) {
		
		return maintenanceCost  = distance * mass * (currentYear-modelYear) * cylinders * 0.001;
	}

	@Override
	public double calculateFuelEfficiency(double distance, double fuelPrice) {
		
		return fuelEfficiency = cylinders * gasTankCapacity * fuelPrice / distance * 0.05;
	}
	
	@Override
	public void startEngine() {
		System.out.println("PUSHSTART");
	}
}
