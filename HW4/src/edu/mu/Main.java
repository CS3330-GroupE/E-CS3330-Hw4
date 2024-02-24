package edu.mu;
import edu.mu.vehicleManager.VehicleManager;

public class Main {

	public static void main(String[] args) {
		VehicleManager manager = new VehicleManager();
		manager.initializeStock();
		
		//test to display all cars
		System.out.println("-----Testing displayAllCarInformation method----- \n");
		manager.displayAllCarInformation();
		System.out.println("-----End of displayAllCarInformation test----- \n");
		
		System.out.println("-----Testing displayAllTruckInformation method----- \n");
		manager.displayAllTruckInformation();
		System.out.println("-----End of displayAllTruckInformation test----- \n");
		
		System.out.println("-----Testing displayAllSUVInformation method----- \n");
		manager.displayAllSUVInformation();
		System.out.println("-----End of displayAllSUVInformation test----- \n");
		
		System.out.println("-----Testing displayAllMotorBikeInformation method----- \n");
		manager.displayAllMotorBikeInformation();
		System.out.println("-----End of displayAllMotorBikeInformation test----- \n");
		
		System.out.println("-----Testing displayAllVehicleInformation method----- \n");
		manager.displayAllVehicleInformation();
		System.out.println("-----End of displayAllVehicleInformation test----- \n");
		
		
	}
}
