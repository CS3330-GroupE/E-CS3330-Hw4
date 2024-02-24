package edu.mu.vehicleManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//reference for Team E:
//Original file contains 11 vehicles. 2 trucks/ 2 Motorbikes / 3 Cars / 4 SUV's 

//Announcement From TA's:
//For homework 4, please include the following in the VehicleManager class, 
//and pass them as inputs to calculateMaintenaceCost and calculateFuelEfficiency within the logic of the display functions 
//e.g.  displayAllMotorBikeInformation(), etc.

public class VehicleManager {

	private final static double distance = 300;
	private final static double fuelPrice = 3.25;
	
	private String vehicleFilePath = "HW4/src/files/vehicleList.csv";
	
	//array list for storing vehicles when the csv file is read
	private ArrayList<Vehicle> vehicleList = new ArrayList<>();
	
	//Reads the data from a CSV file located at vehicleFilePath. Initialize each of the Vehicle objects and adds them to the vehicle list
	//returns true if the file is read and initialization is successful. 
	//returns false if the file cannot be read or found. 
	public boolean initializeStock() {
		
		System.out.println("Attemping to read vehicle list file.\n");
		try {
			File file = new File(vehicleFilePath);
			if(!file.exists()) {
				System.out.println("Vehicle list file does not exist.\n");
				return false;
			}
			
			System.out.println("Vehicle list file exists!\n");
			
			Scanner fileIn = new Scanner(new FileInputStream(vehicleFilePath));
			
			//skipping first line of file (no real data)
			if(fileIn.hasNextLine() ) {
				fileIn.nextLine();
			}
			//checks if the the file is empty and returns false
			if (!fileIn.hasNextLine()){
	            System.out.println("The file is empty.\n");
	            fileIn.close();
	            return false;
			}
			
			//reads file, splits lines by commas and creates string tokens
			//tokens are assinged to variables matching vehicle attributes
			while(fileIn.hasNextLine()) {
				String vehicleFileLine = fileIn.nextLine();
				String[] vehicleData = vehicleFileLine.split(",");
				
				String type = vehicleData[0];
				String brand = vehicleData[1];
				String make = vehicleData[2];
				long modelYear = Long.parseLong(vehicleData[3]);
				double price = Double.parseDouble(vehicleData[4]);
				VehicleColor color = VehicleColor.valueOf(vehicleData[5]);
				FuelType fuelType = FuelType.valueOf(vehicleData[6]);
				Double mileage = Double.parseDouble(vehicleData[7]);
				Double mass = Double.parseDouble(vehicleData[8]);
				int cylinders = Integer.parseInt(vehicleData[9]);
				Double gasTankCapacity = Double.parseDouble(vehicleData[10]);
				StartMechanism startType = StartMechanism.valueOf(vehicleData[11]);
				
				//Car car = new Car(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);		
				//Truck truck = new Truck(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
				//MotorBike motorbike = new MotorBike(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
				
				
				//checks for the type of vehicle to determine which vehicle class to initialize
				Vehicle vehicle;
				
				switch(type) {
				case "Car":
					vehicle = new Car(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
					break;
					
				case "Truck":
					vehicle = new Truck(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
					break;
					
				case "MotorBike":
					vehicle = new MotorBike(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
					break;
					
				case "SUV":
					vehicle = new SUV(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
					break;
					
				default:
					
					//checks if type fails to match with the cases
					//error -> unknown vehicle type
					throw new IllegalArgumentException("Unknown vehicle type: " + type);
				}
				
				//displays vehicle information 
				//this can be removed before submission
				System.out.println(type + " " + vehicle);
				
				//adds current vehicle instance to the vehicle array
				 vehicleList.add(vehicle);
				}
			
				//closes the file reader and prevent leaks
				fileIn.close();
				System.out.println("\n");
		}
		
		//file handling for if file could not be read and returns false
		catch(FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	//dummy commment
	
	//displays information of all cars in the 
	public void displayAllCarInformation() {
		//boolean for if vehicle is not found
		boolean carHasBeenFound = false;
		
		//for each loop to iterate through vehicle list
		for (Vehicle vehicle : vehicleList) {
			
			//checks if element is of subclass Car
			if (vehicle instanceof Car) {
				
				//Displays information regarding cars in list
	            System.out.println("Car Information:");
	            System.out.println("Brand: " + vehicle.getBrand());
	            System.out.println("Make: " + vehicle.getMake());
	            System.out.println("Model Year: " + vehicle.getModelYear());
				System.out.println("Maintenance Cost: $" + vehicle.calculateMaintenanceCost(distance));
	            System.out.println("Fuel Efficiency: " + vehicle.calculateFuelEfficiency(distance, fuelPrice) + " mpg");
	            System.out.print("Start Mechanism: ");   
	            vehicle.startEngine();
				
	            System.out.println("");
				carHasBeenFound = true;
			}
		}
		
		//makes sure to let user know there were no cars in the list
		//if no cars were found then variable stays false
		if (carHasBeenFound == false) {
			System.out.println("There appear to be no Cars in the list.\n");
		};
	}
}