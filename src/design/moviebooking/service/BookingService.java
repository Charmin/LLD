package design.moviebooking.service;

import design.moviebooking.*;
import design.moviebooking.enums.BookingStatus;
import design.moviebooking.exceptions.LockFailureException;

import java.util.*;
import java.util.stream.Collectors;

public class BookingService {

    //Expired bookings to be periodically cleaned up.
    Map<String, Map<String, Booking>> showIdToUserBookings = new HashMap<>();
    Map<String, Show> showMap = new HashMap<>();
    Map<String, Booking> bookingMap = new HashMap<>();

    LockProvider lockProvider;
    /*
    * How to avoid conflicting requests
    * 1. In case of a single server system, a simple synchronised around createBooking would do, this would
    *    not scale
    * 2. Have in memory lock (can be in Redis too),
    *    Reading: All or none, even if one seat is booked/reserved, booking fails
    *    Write: Take a lock on the seats with expiry, so that any other attempt to book the same seat fails
    *    Use optimistic locking. Pretend there is no contention. Use transaction isolation level, read committed.
    *
    *    The booking gets confirmed if the payment gets through before the lock expires.
    * */
    public BookingService(LockProvider lockProvider) {
        this.lockProvider = lockProvider;
    }

    public BookingStatus createBooking(final Show show,
                                       final List<Seat> seats,
                                       final String user) {
        if (seatAlreadyBooked(show.getId(), seats)) {
            throw new IllegalStateException("One or more Seat combination already booked");
        }
        try {
            lockProvider.lock(show, seats, user);
            Booking booking = new Booking(getId(), show, user, seats);
            showIdToUserBookings.getOrDefault(show.getId(), new HashMap<>()).put(user, booking);
        } catch (LockFailureException e) {
            //TODO: change exception type here
            throw new IllegalStateException("One or more Seat combination already booked");
        }
        return BookingStatus.CREATED;
    }

    private String getId() {
        return UUID.randomUUID().toString();
    }

    /*
    * This can get the booked seats from cache
    * */
    private boolean seatAlreadyBooked(String showId, List<Seat> seats) {
        Set<Seat> seatsNotAvail = getUnavailableSeats(showId);
        for (Seat s : seats) {
            if (seatsNotAvail.contains(s)) {
                return true;
            }
        }
        return false;
    }

    private Set<Seat> getUnavailableSeats(String showId) {
        //Get booked seats, get reserved seats
        Set<Seat> unavailableSeats = new HashSet<>();
        unavailableSeats.addAll(getBookedSeats(showId));
        unavailableSeats.addAll(getReservedSeats(showId));
        return unavailableSeats;
    }

    private List<Seat> getBookedSeats(final String showId) {
        return showIdToUserBookings.get(showId).entrySet().stream()
                .filter(s -> s.getValue().getBookingStatus() == BookingStatus.CONFIRMED)
                .flatMap(p -> p.getValue().getSeatsBooked().stream())
                .collect(Collectors.toList());
    }

    private List<Seat> getReservedSeats(final String showId) {
        return showIdToUserBookings.get(showId).entrySet().stream()
                .filter(s -> s.getValue().getBookingStatus() == BookingStatus.CREATED)
                .flatMap(p -> p.getValue().getSeatsBooked().stream())
                .collect(Collectors.toList());
    }

}
