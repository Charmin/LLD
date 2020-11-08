package design.cab_booking.services;

import design.cab_booking.models.Cab;
import design.cab_booking.models.CabState;
import design.cab_booking.models.Location;

import java.util.List;

/**
 * Admin side of the cab
 */
public interface CabService {
    void registerCab(final Cab newCab);
    List<Cab> showAvailableCabs(final Location location);
    void updateLocation(final Long cabId, final Location location);
    void updateAvailability(final Long cabId, final CabState cabState);
}
