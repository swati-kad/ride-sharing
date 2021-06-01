package Model;

public class RideState {
	
	private String user;
	private Integer ridesTaken;
	private Integer ridesOffered;

	public RideState() {
	}
	
	public RideState(String user, Integer ridesTaken, Integer ridesOffered) {
		super();
		this.user = user;
		this.ridesTaken = ridesTaken;
		this.ridesOffered = ridesOffered;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getRidesTaken() {
		return ridesTaken;
	}

	public void setRidesTaken(Integer ridesTaken) {
		this.ridesTaken = ridesTaken;
	}

	public Integer getRidesOffered() {
		return ridesOffered;
	}

	public void setRidesOffered(Integer ridesOffered) {
		this.ridesOffered = ridesOffered;
	}

	@Override
	public String toString() {
		return "RideState [user=" + user + ", ridesTaken=" + ridesTaken + ", ridesOffered=" + ridesOffered + "]";
	}
	
}
