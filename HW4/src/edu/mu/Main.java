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
	}
}
