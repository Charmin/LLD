package design.moviebooking;

import design.moviebooking.exceptions.LockFailureException;

import java.util.List;

public interface LockProvider {
    void lock(Show show, List<Seat> seats, String user) throws LockFailureException;
    void unlock(Show show, List<Seat> seats, String user) throws LockFailureException;
    boolean validateLock(Show show, Seat seat, String user) throws LockFailureException;
}
