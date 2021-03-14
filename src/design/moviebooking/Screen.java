package design.moviebooking;

import java.util.List;

public class Screen {
    private String id;
    private String name;
    private Theatre theatre;
    private List<Seat> seats;

    public Screen(String id, String name, Theatre theatre) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
    }
    //admin method
    public void addSeat(Seat seat) {
        seats.add(seat);
    }
}
