package design.moviebooking.service;

import com.sun.istack.internal.NotNull;
import design.moviebooking.*;

import java.util.*;

public class TheatreAdminService {
    Map<String, Theatre> theatres;
    Map<String, Screen> screens;
    Map<String, Seat> seats;

    //This can be in a separate service
    Map<String, Show> shows;


    public TheatreAdminService() {
        theatres = new HashMap<>();
        screens = new HashMap<>();
        seats = new HashMap<>();
        shows = new HashMap<>();
    }

    public Seat getSeat(String seatId) {
        return seats.get(seatId);
    }

    public Theatre getTheatre(String theatreId) {
        return theatres.get(theatreId);
    }

    public Screen getScreen(String screenId) {
        return screens.get(screenId);
    }

    public Theatre createTheatre(@NotNull final String theatreName) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatres.put(theatreId, theatre);
        return theatre;
    }

    public Screen addScreen(String screenName, Theatre theatre) {
        Screen screen = createScreen(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

    //seatNo is the colNo
    public Seat addSeats(int row, int seatNo, Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, row, seatNo);
        screen.addSeat(seat);
        return seat;
    }

    //Can be moved to a show service too
    public Show createShow(Date startTime, String screenId, int duration) {
        Screen screen = screens.get(screenId);
        Movie movie = null; // to create one from movie service
        Show show = new Show(movie, screen, startTime, duration);
        shows.put(show.getId(), show);
        return show;
    }

    private Screen createScreen(String screenName, Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        return new Screen(screenId, screenName, theatre);
    }

}
