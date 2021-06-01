package Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import Model.Ride;
import Model.RideState;
import Model.Vehicle;

public class RideServiceImpl implements RideService {

	private Map<String, Ride> availableRides = new HashMap<>(); // UserVehicle and offeredRide mapping
	private Map<String, RideState> ridesStateMap = new HashMap<>(); // User and RideState mapping

	@Override
	public void offerRide(Ride newRide) {
		try {
			Vehicle vehicle = newRide.getVehicle();
			Ride existingRide = availableRides.get(vehicle.getOwner() + vehicle.getName());
			if (rideAlreadyExists(newRide, existingRide)) {
				System.out.println("Ride Already Offered for Vehicle: " + vehicle);
			} else {
				availableRides.put(vehicle.getOwner() + vehicle.getName(), newRide);
				updateRidesState(vehicle.getOwner(), "Offered");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage() + " occured while adding Ride: " + newRide);
		}
	}

	private boolean rideAlreadyExists(Ride newRide, Ride existingRide) {
		if (existingRide != null && existingRide.getVehicle().getRegistrationNum()
				.equalsIgnoreCase(newRide.getVehicle().getRegistrationNum())) {
			return true;
		}
		return false;
	}

	private void updateRidesState(String appUser, String requestType) {
		RideState rideState = ridesStateMap.get(appUser);
		if (requestType.equalsIgnoreCase("Offered")) {
			if (rideState == null) {
				RideState newRideOffered = new RideState(appUser, 0, 1);
				ridesStateMap.put(appUser, newRideOffered);
			} else {
				rideState.setRidesOffered(rideState.getRidesOffered() + 1);
			}
		} else if (requestType.equalsIgnoreCase("Requested")) {
			if (rideState == null) {
				RideState newRideRequest = new RideState(appUser, 1, 0);
				ridesStateMap.put(appUser, newRideRequest);
			} else {
				rideState.setRidesTaken(rideState.getRidesTaken() + 1);
			}
		}
	}

	@Override
	public void selectRide(String requester, String origin, String destination, Integer seatsRequired,
			String preference) {
		try {
			Ride selectedRide = null;
			Set<Ride> rides = availableRides.entrySet().stream().map(rideEntry -> rideEntry.getValue())
					.collect(Collectors.toSet());

			List<Ride> ridesMatchingOrigin = rides.stream().filter(
					ride -> ride.getOrigin().equalsIgnoreCase(origin) && ride.getSeatsAvailable() >= seatsRequired)
					.collect(Collectors.toList());

			List<Ride> ridesMatchingDestination = rides.stream()
					.filter(ride -> ride.getDestination().equalsIgnoreCase(destination)
							&& ride.getSeatsAvailable() >= seatsRequired)
					.collect(Collectors.toList());

			rides.clear();

			if (!ridesMatchingOrigin.isEmpty() && !ridesMatchingDestination.isEmpty()) {
				rides.addAll(ridesMatchingOrigin);
				rides.addAll(ridesMatchingDestination);
				selectedRide = getPreferredRides(rides, origin, destination, preference);
			}

			if (selectedRide != null) {
				System.out.println("For requester: " + requester + ", Available Ride: " + selectedRide);
				updateAvailableSeats(selectedRide, seatsRequired);
				updateRidesState(requester, "Requested");
			} else {
				List<Ride> connectedRide = checkForConnectedRide(ridesMatchingOrigin, ridesMatchingDestination);
				if(connectedRide.isEmpty())
				   System.out.println("No ride found for requester: " + requester);
				else {
					connectedRide.forEach(ride -> {
						System.out.println("");
						System.out.println("For requester: " + requester + ", Available Ride: " + ride);
						updateAvailableSeats(ride, seatsRequired);
					});
				}	
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage() + " occured while selecting Ride for : " + requester);
		}

	}

	private Ride getPreferredRides(Set<Ride> rides, String origin, String destination, String preference) {
		Ride selectedRide = null;
		List<Ride> directRides = rides.stream().filter(ride -> ride.getOrigin().equalsIgnoreCase(origin)
				&& ride.getDestination().equalsIgnoreCase(destination)).collect(Collectors.toList());

		directRides.sort(Comparator.comparing(Ride::getSeatsAvailable).reversed());

		if (!directRides.isEmpty() && preference != "Most Vacant") {
			selectedRide = directRides.stream().filter(ride -> ride.getVehicle().getName().equalsIgnoreCase(preference))
					.findFirst().orElse(null);
		} else if (!directRides.isEmpty()) {
			selectedRide = directRides.get(0);
		}
		return selectedRide;
	}

	private void updateAvailableSeats(Ride selectedRide, Integer requestedSeats) {
		Integer updatedSeatsAvailable = selectedRide.getSeatsAvailable() - requestedSeats;
		if (updatedSeatsAvailable == 0) {
			selectedRide.setSeatsAvailable(updatedSeatsAvailable);
			endRide(selectedRide); // if seats are no longer available, marking ride as completed.
		} else
			selectedRide.setSeatsAvailable(updatedSeatsAvailable);
	}

	
	private List<Ride> checkForConnectedRide(List<Ride> ridesMatchingOrigin, List<Ride> ridesMatchingDestination) {
		List<Ride> connectedRides = new LinkedList<>();
		for(Ride originRide : ridesMatchingOrigin) {
			Ride destination = ridesMatchingDestination.stream().filter(destinationRide -> 
			      originRide.getDestination().equalsIgnoreCase(destinationRide.getOrigin())).findFirst().orElse(null);
			if(destination != null) {
				connectedRides.add(originRide);
				connectedRides.add(destination);
			}
		}
		  return connectedRides; 
    }
	 
	@Override
	public void printRideState() {
		ridesStateMap.forEach((k, v) -> System.out
				.println(k + ": " + v.getRidesTaken() + " Taken, " + v.getRidesOffered() + " Offered"));
	}

	@Override
	public void endRide(Ride ride) {
		System.out.println("");
		System.out.println("Ride ended: " + ride);
		Vehicle vehicle = ride.getVehicle();
		availableRides.remove(vehicle.getOwner() + vehicle.getName());
	}
}
