package design.cab_booking.services;

import design.cab_booking.models.Rider;

public interface RiderService {
    void createRider(final String customerId);
    Rider getRiderDetail(final String customerId);
}
