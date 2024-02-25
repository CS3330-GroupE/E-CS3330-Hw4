package edu.mu;
import java.util.ArrayList;

import edu.mu.vehicleManager.Vehicle;
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
		
		//for testing methods
		ArrayList<Vehicle> list = new ArrayList<>();
		//variables being passed in for test
		double distance = VehicleManager.getDistance();
		double fuelPrice = VehicleManager.getFuelprice();
		
		System.out.println("-----Testing getVehicleWithLowestFuelEfficiency method----- \n");
		list = manager.getVehicleWithLowestFuelEfficiency(distance, fuelPrice);
		manager.printList(list);
		System.out.println("\n-----End of getVehicleWithLowestFuelEfficiency test----- \n");
		
		System.out.println("-----Testing getVehicleWithHighestFuelEfficiency method----- \n");
		list = manager.getVehicleWithHighestFuelEfficiency(distance, fuelPrice);
		manager.printList(list);
		System.out.println("\n-----End of getVehicleWithHighestFuelEfficiency test----- \n");
		
		//for testing
		double averageSUV;
		System.out.println("-----Testing getAverageFuelEfficiencyOfSUVs method----- \n");
		averageSUV = manager.getAverageFuelEfficiencyOfSUVs(distance, fuelPrice);
		System.out.print(averageSUV  + "\n\n");
		System.out.println("-----Testing getAverageFuelEfficiencyOfSUVs method----- \n");
		
		Vehicle testVehicle;
	
		System.out.println("-----Testing getVehicleWithLowestMaintenanceCost method----- \n");
		testVehicle = manager.getVehicleWithLowestMaintenanceCost(distance);
		manager.printVehicle(testVehicle);
		System.out.println("\n-----Testing getVehicleWithLowestMaintenanceCost method----- \n");
		
	}
}
