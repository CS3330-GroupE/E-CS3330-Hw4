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
	
	public static double getDistance() {
		return distance;
	}

	public static double getFuelprice() {
		return fuelPrice;
	}

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
						
				//adds current vehicle instance to the vehicle array
				 vehicleList.add(vehicle);
				}
			
				//closes the file reader and prevent leaks
				fileIn.close();
				System.out.println("Reading the file has been successful.\n");
		}
		
		//file handling for if file could not be read and returns false
		catch(FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	//displays information of all cars in the 
	public void displayAllCarInformation() {
		//boolean for if a car is not found
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
	            System.out.println("Price: $" + String.format("%.2f", vehicle.getPrice()));
	            System.out.println("Color: " + vehicle.getColor());
	            System.out.println("Fuel Type: " + vehicle.getFuelType());
	            System.out.println("Mileage: " + vehicle.getMileage());
	            System.out.println("Mass: " + vehicle.getMass());
	            System.out.println("Cylinders: " + vehicle.getCylinders());
	            System.out.println("Maintenance Cost: $" + String.format("%.2f", vehicle.calculateMaintenanceCost(distance)));
	            System.out.println("Fuel Efficiency: " + String.format("%.6f", vehicle.calculateFuelEfficiency(distance, fuelPrice)));
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
	
	public void displayAllTruckInformation() {
		//boolean for if truck is not found
		boolean truckHasBeenFound = false;
		
		//for each loop to iterate through vehicle list
		for (Vehicle vehicle : vehicleList) {
			
			//checks if element is of subclass Truck
			if (vehicle instanceof Truck) {
				
				//Displays information regarding cars in list
	            System.out.println("Truck Information:");
	            System.out.println("Brand: " + vehicle.getBrand());
	            System.out.println("Make: " + vehicle.getMake());
	            System.out.println("Model Year: " + vehicle.getModelYear());
	            System.out.println("Price: $" + String.format("%.2f", vehicle.getPrice()));
	            System.out.println("Color: " + vehicle.getColor());
	            System.out.println("Fuel Type: " + vehicle.getFuelType());
	            System.out.println("Mileage: " + vehicle.getMileage());
	            System.out.println("Mass: " + vehicle.getMass());
	            System.out.println("Cylinders: " + vehicle.getCylinders());
	            System.out.println("Maintenance Cost: $" + String.format("%.2f", vehicle.calculateMaintenanceCost(distance)));
	            System.out.println("Fuel Efficiency: " + String.format("%.6f", vehicle.calculateFuelEfficiency(distance, fuelPrice)));
	            System.out.print("Start Mechanism: ");   
	            vehicle.startEngine();
				
	            System.out.println("");
				truckHasBeenFound = true;
			}
		}
		
		//makes sure to let user know there were no cars in the list
		//if no trucks were found then variable stays false
		if (truckHasBeenFound == false) {
			System.out.println("There appear to be no Trucks in the list.\n");
		};
	}
	
	
	public void displayAllSUVInformation() {
		//boolean for if SUV is not found
		boolean SUVHasBeenFound = false;
		
		//for each loop to iterate through vehicle list
		for (Vehicle vehicle : vehicleList) {
			
			//checks if element is of subclass SUV
			if (vehicle instanceof SUV) {
				
				//Displays information regarding cars in list
				System.out.println("SUV Information:");
				System.out.println("Brand: " + vehicle.getBrand());
				System.out.println("Make: " + vehicle.getMake());
				System.out.println("Model Year: " + vehicle.getModelYear());
				System.out.println("Price: $" + String.format("%.2f", vehicle.getPrice()));
				System.out.println("Color: " + vehicle.getColor());
				System.out.println("Fuel Type: " + vehicle.getFuelType());
				System.out.println("Mileage: " + vehicle.getMileage());
				System.out.println("Mass: " + vehicle.getMass());
				System.out.println("Cylinders: " + vehicle.getCylinders());
				System.out.println("Maintenance Cost: $" + String.format("%.2f", vehicle.calculateMaintenanceCost(distance)));
				System.out.println("Fuel Efficiency: " + String.format("%.6f", vehicle.calculateFuelEfficiency(distance, fuelPrice)));
				System.out.print("Start Mechanism: ");   
				vehicle.startEngine();
				
				System.out.println("");
				SUVHasBeenFound = true;
			}
		}
		
		//makes sure to let user know there were no SUVs in the list
		//if no Suv's were found then variable stays false
		if (SUVHasBeenFound == false) {
			System.out.println("There appear to be no SUV's in the list.\n");
		};
	}
	
	
	public void displayAllMotorBikeInformation() {
		//boolean for if motorBike is not found
		boolean motorBikeHasBeenFound = false;
		
		//for each loop to iterate through vehicle list
		for (Vehicle vehicle : vehicleList) {
			
			//checks if element is of subclass MotorBike
			if (vehicle instanceof MotorBike) {
				
				//Displays information regarding cars in list
				System.out.println("MotorBike Information:");
				System.out.println("Brand: " + vehicle.getBrand());
				System.out.println("Make: " + vehicle.getMake());
				System.out.println("Model Year: " + vehicle.getModelYear());
				System.out.println("Price: $" + String.format("%.2f", vehicle.getPrice()));
				System.out.println("Color: " + vehicle.getColor());
				System.out.println("Fuel Type: " + vehicle.getFuelType());
				System.out.println("Mileage: " + vehicle.getMileage());
				System.out.println("Mass: " + vehicle.getMass());
				System.out.println("Cylinders: " + vehicle.getCylinders());
				System.out.println("Maintenance Cost: $" + String.format("%.2f", vehicle.calculateMaintenanceCost(distance)));
				System.out.println("Fuel Efficiency: " + String.format("%.6f", vehicle.calculateFuelEfficiency(distance, fuelPrice)));
				System.out.print("Start Mechanism: ");   
				vehicle.startEngine();
				
				System.out.println("");
				motorBikeHasBeenFound = true;
			}
		}
		
		//makes sure to let user know there were no motorBikes in the list
		//if no motorBikes were found then variable stays false
		if (motorBikeHasBeenFound == false) {
			System.out.println("There appear to be no Motorbikes in the list.\n");
		};
	}
	
	public void displayAllVehicleInformation() {
		//boolean for if vehicle is not found
		boolean vehicleHasBeenFound = false;
		
		//for each loop to iterate through vehicle list
		for (Vehicle vehicle : vehicleList) {
			
			//checks if element is of subclass vehicle
			if (vehicle instanceof Vehicle) {
				
				//Displays information regarding cars in list
				System.out.println("Vehicle Information:");
				System.out.println("Brand: " + vehicle.getBrand());
				System.out.println("Make: " + vehicle.getMake());
				System.out.println("Model Year: " + vehicle.getModelYear());
				System.out.println("Price: $" + String.format("%.2f", vehicle.getPrice()));
				System.out.println("Color: " + vehicle.getColor());
				System.out.println("Fuel Type: " + vehicle.getFuelType());
				System.out.println("Mileage: " + vehicle.getMileage());
				System.out.println("Mass: " + vehicle.getMass());
				System.out.println("Cylinders: " + vehicle.getCylinders());
				System.out.println("Maintenance Cost: $" + String.format("%.2f", vehicle.calculateMaintenanceCost(distance)));
				System.out.println("Fuel Efficiency: " + String.format("%.6f", vehicle.calculateFuelEfficiency(distance, fuelPrice)));
				System.out.print("Start Mechanism: ");   
				vehicle.startEngine();
				
				System.out.println("");
				vehicleHasBeenFound = true;
			}
		}
		
		//makes sure to let user know there were no vehicles in the list
		//if no vehicles were found then variable stays false
		if (vehicleHasBeenFound == false) {
			System.out.println("There appear to be no Vehicles in the list.\n");
		};
	}
	
	
	public void displayVehicleInformation(Vehicle v) {
		//boolean for if vehicle is not found
		boolean vehicleHasBeenFound = false;
		
		//for each loop to iterate through vehicle list
		for (Vehicle vehicle : vehicleList) {
			
			//checks if element is of subclass vehicle
			if (vehicle.equals(v)) {
				
				//Displays information regarding cars in list
				System.out.println("Vehicle Information:");
				System.out.println("Brand: " + vehicle.getBrand());
				System.out.println("Make: " + vehicle.getMake());
				System.out.println("Model Year: " + vehicle.getModelYear());
				System.out.println("Price: $" + String.format("%.2f", vehicle.getPrice()));
				System.out.println("Color: " + vehicle.getColor());
				System.out.println("Fuel Type: " + vehicle.getFuelType());
				System.out.println("Mileage: " + vehicle.getMileage());
				System.out.println("Mass: " + vehicle.getMass());
				System.out.println("Cylinders: " + vehicle.getCylinders());
				System.out.println("Maintenance Cost: $" + String.format("%.2f", vehicle.calculateMaintenanceCost(distance)));
				System.out.println("Fuel Efficiency: " + String.format("%.6f", vehicle.calculateFuelEfficiency(distance, fuelPrice)));
				System.out.print("Start Mechanism: ");   
				vehicle.startEngine();
				
				System.out.println("");
				vehicleHasBeenFound = true;
			}
		}
		
		//makes sure to let user know there were no vehicles in the list
		//if no vehicles were found then variable stays false
		if (vehicleHasBeenFound == false) {
			System.out.println("There appear to be no Vehicles like the one specified in the list.\n");
		};
	}
	
	public boolean removeVehicle(Vehicle vehicle) {
	    return vehicleList.remove(vehicle);
	}

	public boolean addVehicle(Vehicle vehicle) {
	    return vehicleList.add(vehicle);
	}
	
	
	//For reference, here are the formulas used to calculate maintenance cost:
	//Truck: maintenanceCost=distance*mass*(currentYear-modelYear)*cylinders*0.002
	//Car: maintenanceCost=distance * mass	* (currentYear-modelYear) *	cylinders * 0.0005
	//SUV: maintenanceCost=distance * mass* (currentYear-modelYear) *cylinders * 0.001
	//Motorbike: maintenanceCost=distance * mass* (currentYear-modelYear) *cylinders * 0.0002
	
	public Vehicle getVehicleWithLowestMaintenanceCost(double distance) {
		//used an arbitrarily large number as the initial value for lowest
		//it is doubtful the maintenance of a vehicle will be this high
		double lowest = 100000.00;
		
		//will find the lowest maintenance cost
		for(Vehicle findLowest : vehicleList) {
			//compares the value of lowest with the current objects maintenance cost
			//if lower, lowest's value is set to that object's maintenance cost
			if(lowest > findLowest.calculateMaintenanceCost(distance)){
				lowest = findLowest.calculateMaintenanceCost(distance);
			}
			
		}
		//will find lowest maintenance cost
		for(Vehicle lowestVehicle : vehicleList) {
			//whenever the lowest maintenance cost is found again, this statement will return that object
			if(lowest == lowestVehicle.calculateMaintenanceCost(distance)) {
				return lowestVehicle;
			}
		}
		
		return null;
	}

	public Vehicle getVehicleWithHighestMaintenanceCost(double distance) {
		//set a test value of 0
		double testValue = 0.00;
		//iterates through list
		for(Vehicle vehicle : vehicleList) {
			//compares current test value to vehicle maintenance cost
			//higher values take testValue's place
			if(vehicle.calculateMaintenanceCost(distance) > testValue){
				testValue = vehicle.calculateMaintenanceCost(distance);
			}
		}
		//iterates through list
		for(Vehicle highestMCVehicle : vehicleList) {
			//returns object correlating to highest maintenance cost
			if(testValue == highestMCVehicle.calculateMaintenanceCost(distance)) {
				return highestMCVehicle;
			}
		}
		return null;
	}
	
	//for testing
	public void printVehicle(Vehicle vehicle) {
		
		System.out.println("Car Information:");
        System.out.println("Brand: " + vehicle.getBrand());
        System.out.println("Make: " + vehicle.getMake());
        System.out.println("Model Year: " + vehicle.getModelYear());
        System.out.println("Price: $" + String.format("%.2f", vehicle.getPrice()));
        System.out.println("Color: " + vehicle.getColor());
        System.out.println("Fuel Type: " + vehicle.getFuelType());
        System.out.println("Mileage: " + vehicle.getMileage());
        System.out.println("Mass: " + vehicle.getMass());
        System.out.println("Cylinders: " + vehicle.getCylinders());
        System.out.println("Maintenance Cost: $" + String.format("%.2f", vehicle.calculateMaintenanceCost(distance)));
        System.out.println("Fuel Efficiency: " + String.format("%.6f", vehicle.calculateFuelEfficiency(distance, fuelPrice)));
        System.out.print("Start Mechanism: ");   
        vehicle.startEngine();
		
	}
	
	//For Reference, here are the formulas used to calculate efficiency:
	//Truck: fuelEfficiency = cylinders*gasTankCapacity*fuelPrice/distance * 0.1
	//Car: fuelEfficiency = cylinders*gasTankCapacity*fuelPrice/distance*0.003
	//SUV: fuelEfficiency = cylinders*gasTankCapacity*fuelPrice/distance*0.05
	//Motorbike: fuelEfficiency = cylinders*gasTankCapacity*fuelPrice/distance*0.001
	
	
	public ArrayList<Vehicle>
	getVehicleWithHighestFuelEfficiency(double distance, double fuelPrice){
		ArrayList<Vehicle> highestList = new ArrayList<>();
		//set the value to the absolute floor of zero
		//highly doubtful any cars efficiency will have negative value..
		double highest = 0.00;
		
		//will iterate through vehicle list to find highest fuel efficiency
		//similar to lowest, but its inverse (detailed explanantion in lowest)
		for(Vehicle findHighest : vehicleList) {
			if(highest < findHighest.calculateFuelEfficiency(distance, fuelPrice)){
				highest = findHighest.calculateFuelEfficiency(distance, fuelPrice);
			}
		}
		
		//finds the highest fuel efficiency objects and adds them to a list
		for(Vehicle insertHighest : vehicleList) {
			if(highest == insertHighest.calculateFuelEfficiency(distance, fuelPrice)) {
				highestList.add(insertHighest);
			}
		}
		return highestList;
	}
	
	public ArrayList<Vehicle>
	getVehicleWithLowestFuelEfficiency(double distance, double fuelPrice){
		ArrayList<Vehicle> lowestList = new ArrayList<>();
		//used an arbitrarily large number as the initial value for lowest
		double lowest = 1000.00;
		
		//this will go through the list and set the value of lowest equal to the lowest fuel efficient in the list
		for(Vehicle findLowest : vehicleList) {
			//if lowest is larger than the current objects value, it will set lowest equal to the current objects fuel efficiency
			//it will continue to do this till it has ran through the whole list
			if(lowest > findLowest.calculateFuelEfficiency(distance, fuelPrice)){
				lowest = findLowest.calculateFuelEfficiency(distance, fuelPrice);
			}
			
		}
		
		//this loop then checks lowest's value against all the objects in the list and adds any in the list that have an equal fuel efficiency
		for(Vehicle insertLowest : vehicleList) {
			if(lowest == insertLowest.calculateFuelEfficiency(distance, fuelPrice)) {
				lowestList.add(insertLowest);
			}
		}
		
		
		return lowestList;
	}
	
	//added for testing if appropriate
	public boolean printList(ArrayList<Vehicle> vehicleList) {
		for(Vehicle vehicle : vehicleList) {
		 System.out.println("Car Information:");
         System.out.println("Brand: " + vehicle.getBrand());
         System.out.println("Make: " + vehicle.getMake());
         System.out.println("Model Year: " + vehicle.getModelYear());
         System.out.println("Price: $" + String.format("%.2f", vehicle.getPrice()));
         System.out.println("Color: " + vehicle.getColor());
         System.out.println("Fuel Type: " + vehicle.getFuelType());
         System.out.println("Mileage: " + vehicle.getMileage());
         System.out.println("Mass: " + vehicle.getMass());
         System.out.println("Cylinders: " + vehicle.getCylinders());
         System.out.println("Maintenance Cost: $" + String.format("%.2f", vehicle.calculateMaintenanceCost(distance)));
         System.out.println("Fuel Efficiency: " + String.format("%.6f", vehicle.calculateFuelEfficiency(distance, fuelPrice)));
         System.out.print("Start Mechanism: ");   
         vehicle.startEngine();
		}
		
		return true;
	}
	
	public double getAverageFuelEfficiencyOfSUVs(double distance, double fuelPrice) {
		//variables for calculating average
		double sum = 0.00;
		double counter = 0.00;
		double averageSUV;
		
		//will add the fuel efficiencies of all SUVs
		for(Vehicle vehicle : vehicleList) {
			if(vehicle instanceof SUV) {
				//used for testing
				//System.out.print("\n" + vehicle.calculateFuelEfficiency(distance, fuelPrice));
			
				sum = sum + vehicle.calculateFuelEfficiency(distance, fuelPrice);
				//keeps trakc of how many SUVs in the list
				counter = counter + 1;
			}
		}

	public boolean saveVehicleList() {
    	try (FileWriter writer = new FileWriter(vehicleFilePath)) {
            //write each product to the file in CSV format
            for (Vehicle vehicle : vehicleList) {
            	//writes objects in proper style
            	//checks type of vehicle and assigns proper type
            	if (vehicle instanceof Car) {
            		writer.write(String.format("%s,%s,%s,%d,%.2f,%s,%s,%.2f,%.2f,%d,%.2f,%s%n", "Car", vehicle.getBrand(), vehicle.getMake(), vehicle.getModelYear(), vehicle.getPrice(), vehicle.getColor(), vehicle.getFuelType(), vehicle.getMileage(), vehicle.getMass(), vehicle.getCylinders(), vehicle.getGasTankCapacity(), vehicle.getStartType()));
            	}
            	else if (vehicle instanceof Truck) {
                    writer.write(String.format("%s,%s,%s,%d,%.2f,%s,%s,%.2f,%.2f,%d,%.2f,%s%n", "Truck", vehicle.getBrand(), vehicle.getMake(), vehicle.getModelYear(), vehicle.getPrice(), vehicle.getColor(), vehicle.getFuelType(), vehicle.getMileage(), vehicle.getMass(), vehicle.getCylinders(), vehicle.getGasTankCapacity(), vehicle.getStartType()));
                }
            	else if (vehicle instanceof MotorBike) {
                    writer.write(String.format("%s,%s,%s,%d,%.2f,%s,%s,%.2f,%.2f,%d,%.2f,%s%n", "MotorBike", vehicle.getBrand(), vehicle.getMake(), vehicle.getModelYear(), vehicle.getPrice(), vehicle.getColor(), vehicle.getFuelType(), vehicle.getMileage(), vehicle.getMass(), vehicle.getCylinders(), vehicle.getGasTankCapacity(), vehicle.getStartType()));
                }
            	else if (vehicle instanceof SUV) {
                    writer.write(String.format("%s,%s,%s,%d,%.2f,%s,%s,%.2f,%.2f,%d,%.2f,%s%n", "SUV", vehicle.getBrand(), vehicle.getMake(), vehicle.getModelYear(), vehicle.getPrice(), vehicle.getColor(), vehicle.getFuelType(), vehicle.getMileage(), vehicle.getMass(), vehicle.getCylinders(), vehicle.getGasTankCapacity(), vehicle.getStartType()));
                }
            }
            System.out.println("Save successful!");
            return true;
        }
    	//error catching
    	catch(IOException error) {
    		System.out.println("Save unsuccessful");
            error.printStackTrace();
            return false;
    	}
	}
		//used for testing
		//System.out.print("\nSum =" + sum + "\n");
		//divides by the number of SUVs in the list
		averageSUV = sum/counter;
		
		if(averageSUV > 0.00) {
			return averageSUV;
		}
		else {
			return -1.0;
		}
	}
	
	
	
}
