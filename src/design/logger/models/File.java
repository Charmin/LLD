package design.logger.models;

import design.logger.enums.Level;

public class File extends Sink {
    String path;

    public String getPath() {
        return path;
    }

    public File(String path) {
        this.path = path;
    }

    public File(Level level, String type) {
        this.type = type;
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }
}
