package Model;
/**
 * @author Swati
 *
 */
public class Ride {
	
	private Vehicle vehicle;// 
	private String origin;
	private Integer seatsAvailable;
	private String destination;
	

	public Ride() {
	}


	public Ride(Vehicle vehicle, String origin, Integer seatsAvailable, String destination) {
		super();
		this.vehicle = vehicle;
		this.origin = origin;
		this.seatsAvailable = seatsAvailable;
		this.destination = destination;
	}


	public Vehicle getVehicle() {
		return vehicle;
	}


	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public Integer getSeatsAvailable() {
		return seatsAvailable;
	}


	public void setSeatsAvailable(Integer seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	@Override
	public String toString() {
		return "Ride [vehicle=" + vehicle + ", origin=" + origin + ", seatsAvailable=" + seatsAvailable
				+ ", destination=" + destination + "]";
	}
	
}
