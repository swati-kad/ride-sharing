package Service;

import Model.Ride;

public interface RideService {
	public void offerRide(Ride ride);
	public void selectRide(String requester, String origin, String destination, Integer seatsRequired, String preference);
	public void printRideState();
	public void endRide(Ride ride);
}
