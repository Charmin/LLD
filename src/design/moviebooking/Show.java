package design.moviebooking;

import java.util.Date;
import java.util.UUID;

public class Show {
    private String id;
    private Movie movie;
    private Screen screen;
    private Date startTime;
    private Integer durationInSeconds;

    public Show(Movie movie, Screen screen, Date startTime, int durationInSeconds) {
        id = UUID.randomUUID().toString();
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.durationInSeconds = durationInSeconds;
    }

    public String getId() {
        return id;
    }
}
