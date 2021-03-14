package design.moviebooking.service;

import design.moviebooking.Booking;
import design.moviebooking.LockProvider;

import java.util.Map;

/**
 * This is would be the code block accessed by a event queue that sends a failure event.
 * On recieving the event, based on the event type
 *                    PaymentFailure - if less than maxRetry,
 *                                          ignore
 *                                      else
 *                                          booking gets expired, lock is released
 *                    PaymentSuccess - booking gets confirmed, lock is released
 *
 *
 */
public class PaymentService {

    private LockProvider lockProvider;
    private Map<Booking, Integer> bookingFailures;
    private int retries;


    public void processPaymentFailure(final Booking booking, final String user) {

    }

    public void processPaymentSuccess() {

    }
}
