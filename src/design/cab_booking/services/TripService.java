package design.cab_booking.services;


import design.cab_booking.models.Location;
import design.cab_booking.models.Trip;

import java.util.List;

public interface TripService {
    public List<Trip> getAllTrips(final String riderId);
    public Trip createTrip(final String riderId, Location start, Location end);
    public void startTrip(String tripId);
    public void endTrip(String tripId);
}
