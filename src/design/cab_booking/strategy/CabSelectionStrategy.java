package design.cab_booking.strategy;


import design.cab_booking.models.Cab;
import design.cab_booking.models.Location;

import java.util.List;
import java.util.Optional;

public interface CabSelectionStrategy {
    Optional<Cab> selectCab(List<Cab> candidateCabs, Location start, Location end);
}
