package design.logger.models;

import design.logger.enums.Level;

public class LogConfig {
    String timeFormat;
    Level logLevel;
    Sink sink;

    public LogConfig(String timeFormat, Level logLevel, Sink sink) {
        this.timeFormat = timeFormat;
        this.logLevel = logLevel;
        this.sink = sink;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public Level getLogLevel() {
        return logLevel;
    }

    public Sink getSink() {
        return sink;
    }
}
