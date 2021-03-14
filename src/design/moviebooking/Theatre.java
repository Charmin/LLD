package design.moviebooking;

import java.util.List;

public class Theatre {
    private List<Screen> screens;
    private String name;
    private String theatreId;

    public Theatre(String id, String name) {
        this.theatreId = id;
        this.name = name;
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }
}
