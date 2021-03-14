package design.moviebooking;

import design.moviebooking.exceptions.LockFailureException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryLockProvider implements LockProvider {
    Map<String, Map<String, String>> showIdToSeatReserved = new HashMap<>();
    //need to expire locks

    @Override
    public void lock(Show show, List<Seat> seats, String user) throws LockFailureException  {
        if (showIdToSeatReserved.containsKey(show.getId())) {
            for (Seat s : seats) {
                if (showIdToSeatReserved.get(show.getId()).containsKey(s.getId())) {
                    throw new LockFailureException("Seat is already reserved");
                }
                showIdToSeatReserved.get(show.getId()).put(s.getId(), user);
            }
        }
    }

    //If you want to explicitly unlock seat, before expiry
    @Override
    public void unlock(Show show, List<Seat> seats, String user) throws LockFailureException {

    }

    @Override
    public boolean validateLock(Show show, Seat seat, String user) throws LockFailureException {
        return false;
    }
}
