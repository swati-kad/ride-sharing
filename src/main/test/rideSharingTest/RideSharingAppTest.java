package rideSharingTest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Ride;
import Model.User;
import Model.Vehicle;
import Service.RideService;
import Service.RideServiceImpl;
import Service.UserService;
import Service.UserServiceImpl;
import Service.VehicleService;
import Service.VehicleServiceImpl;

public class RideSharingAppTest {
	
	private VehicleService vehicleService;
	private UserService userService;
	private RideService rideService; 
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	String newLine = System.getProperty("line.separator");

	@Before
	public void setUp() throws Exception {
		vehicleService = new VehicleServiceImpl();
		userService = new UserServiceImpl();
		rideService = new RideServiceImpl();
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(standardOut);
	}

	@Test
	public void test() {
		String expectedOutput = "For requester: Nandini, Available Ride: Ride [vehicle=Vehicle [owner=Rohan, name=Swift, registrationNum=KA-01-12345], origin=Delhi, seatsAvailable=4, destination=Bangalore]"
	                             + newLine + newLine + 
	                             "For requester: Nandini, Available Ride: Ride [vehicle=Vehicle [owner=Shashank, name=Baleno, registrationNum=TS-05-62395], origin=Bangalore, seatsAvailable=2, destination=Mumbai]";
		
		User rohan = new User("Rohan","M",36);
		Vehicle rohanSwift = new Vehicle(rohan.getName(), "Swift", "KA-01-12345");
		userService.addUser(rohan);
		vehicleService.addVehicle(rohanSwift);
		
		User shashank = new User("Shashank","M",29);
		Vehicle shashankBaleno = new Vehicle(shashank.getName(), "Baleno", "TS-05-62395");
		userService.addUser(shashank);
		vehicleService.addVehicle(shashankBaleno);
		
		userService.addUser(new User("Nandini","F",29));

		rideService.offerRide(new Ride(rohanSwift, "Delhi", 4, "Bangalore"));
		rideService.offerRide(new Ride(shashankBaleno, "Bangalore", 2, "Mumbai"));
		
		rideService.selectRide("Nandini", "Delhi", "Mumbai", 1, "Most Vacant");
		
		assertEquals(expectedOutput, outputStreamCaptor.toString()
			      .trim());
		
	}

}
