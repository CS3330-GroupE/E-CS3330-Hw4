package edu.mu.vehicleManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VehicleManager {

	private String filePath = "HW4/src/files/vehicleList.csv";
	
	public boolean initializeStock() {
		
		System.out.println("Attemping to read vehicle list file.\n");
		try {
			File file = new File(filePath);
			if(!file.exists()) {
				System.out.println("Vehicle list file does not exist.\n");
				return false;
			}
			
			System.out.println("Vehicle list file exists!\n");
			
			Scanner fileIn = new Scanner(new FileInputStream(filePath));
			
			//skipping first line of file (no real data)
			if(fileIn.hasNextLine() ) {
				fileIn.nextLine();
			}
			
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
					//error -> unknown vehicle type
					throw new IllegalArgumentException("Unknown vehicle type: " + type);
				}
				
				System.out.println(type + " " + vehicle);
				
			}
			

		}
		
		catch(FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	//dummy commment
	
}
