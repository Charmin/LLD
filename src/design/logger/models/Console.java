package design.logger.models;

import design.logger.enums.Level;

public class Console extends Sink {

    public Level getLevel() {
        return level;
    }

    public String getType() {
        return "Console";
    }
}
