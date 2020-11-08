package design.cab_booking.strategy;

import design.cab_booking.models.Cab;
import design.cab_booking.models.Location;
import design.cab_booking.models.Rider;

public interface CabPricingStrategy {
    Double chargeTrip(final Cab cab, Location start, Location end, Rider rider);
}
