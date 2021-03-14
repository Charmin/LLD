package design.moviebooking;

import design.moviebooking.enums.BookingStatus;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.List;

public class Booking {
    private final String Id;
    private final Show show;
    private BookingStatus bookingStatus;
    private final String userId;
    private List<Seat> seatsBooked;

    public Booking(String id, Show show, String userId, List<Seat> seats) {
        Id = id;
        this.show = show;
        this.bookingStatus = BookingStatus.CREATED;
        this.userId = userId;
        this.seatsBooked = seats;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeatsBooked() {
        return seatsBooked;
    }

    public void confirmBooking() {
        if (bookingStatus != BookingStatus.CREATED) {
            throw new InvalidStateException("Not a valid state");
        }
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public void expireBooking() {
        if (bookingStatus != BookingStatus.CREATED) {
            throw new InvalidStateException("Not a valid state");
        }
        this.bookingStatus = BookingStatus.EXPIRED;
    }
}
