import Model.Ride;
import Model.User;
import Model.Vehicle;
import Service.RideService;
import Service.RideServiceImpl;
import Service.UserService;
import Service.UserServiceImpl;
import Service.VehicleService;
import Service.VehicleServiceImpl;

public class RideSharingRunner {
	
	private static VehicleService vehicleService = new VehicleServiceImpl();
	private static UserService userService = new UserServiceImpl();
	private static RideService rideService = new RideServiceImpl();

	public static void main(String[] args) {
		User rohan = new User("Rohan","M",36);
		Vehicle rohanSwift = new Vehicle(rohan.getName(), "Swift", "KA-01-12345");
		userService.addUser(rohan);
		vehicleService.addVehicle(rohanSwift);
		
		User rohan1 = new User("Rohan","M",36);
		Vehicle rohanSwift1 = new Vehicle(rohan1.getName(), "Swift", "KA-01-12345");
		userService.addUser(rohan1);
		vehicleService.addVehicle(rohanSwift1);
		
		User rohan2 = new User("Rohan","M",36);
		Vehicle rohanSwift2 = new Vehicle(rohan2.getName(), "Swift", "KA-01-12345");
		userService.addUser(rohan2);
		vehicleService.addVehicle(rohanSwift2);
		
		User shashank = new User("Shashank","M",29);
		Vehicle shashankBaleno = new Vehicle(shashank.getName(), "Baleno", "TS-05-62395");
		userService.addUser(shashank);
		vehicleService.addVehicle(shashankBaleno);
				
		userService.addUser(new User("Nandini","F",29));
		
		User rahul = new User("Rahul","M",35);
		Vehicle rahulXUV = new Vehicle(rahul.getName(), "XUV", "KA-05-1234");
		userService.addUser(rahul);
		vehicleService.addVehicle(rahulXUV);
		
		User shipra = new User("Shipra","F",27);
		Vehicle shipraPolo = new Vehicle(shipra.getName(), "Polo", "KA-05-41491");
		Vehicle shipraActiva = new Vehicle(shipra.getName(), "Activa", "KA-12-12332");
		userService.addUser(shipra);
		vehicleService.addVehicle(shipraPolo);
		vehicleService.addVehicle(shipraActiva);
		
		userService.addUser(new User("Gaurav","M",29));
		
		
		
		rideService.offerRide(new Ride(rohanSwift, "Hyderabad", 1, "Bangalore"));
		rideService.offerRide(new Ride(shipraActiva, "Bangalore", 1, "Mysore"));
		rideService.offerRide(new Ride(rahulXUV, "Hyderabad", 5, "Bangalore"));
		rideService.offerRide(new Ride(shipraPolo, "Bangalore", 2, "Mysore"));
		rideService.offerRide(new Ride(shashankBaleno, "Hyderabad", 2, "Bangalore"));
		rideService.offerRide(new Ride(rohanSwift, "Bangalore", 1, "Pune")); //should fail
		
		System.out.println("");
		rideService.selectRide("Nandini", "Bangalore", "Mysore", 1, "Most Vacant");
		System.out.println("");
		rideService.selectRide("Gaurav", "Bangalore", "Mysore", 1, "Activa");
		System.out.println("");
		rideService.selectRide("Shashank", "Mumbai", "Bangalore", 1, "Most Vacant");
		System.out.println("");
		rideService.selectRide("Rohan", "Hyderabad", "Bangalore", 1, "Baleno");
		System.out.println("");
		rideService.selectRide("Shashank", "Hyderabad", "Bangalore", 1, "Polo");
		
		System.out.println("");
		rideService.printRideState();
	}
}
