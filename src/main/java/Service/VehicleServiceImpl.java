package Service;

import java.util.ArrayList;
import java.util.List;

import Model.Vehicle;

public class VehicleServiceImpl implements VehicleService {
	
	private List<Vehicle> allVehicles = new ArrayList<>();

	@Override
	public void addVehicle(Vehicle vehicle) {
		try {
			allVehicles.add(vehicle);
		}catch(Exception e) {
			System.out.println("Exception: " + e.getMessage() + " occured while adding Vehicle: " + vehicle);
		}
		
	}
}
