package design.logger.appenders;

import design.logger.models.LogMessage;
import design.logger.models.Sink;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Appender<T extends Sink> {
    T sink;
    public Appender(T sink) {
        this.sink = sink;
    }

    public abstract void publish(LogMessage logMessage) throws IOException;

    protected StringBuilder getFormattedPrefix(String timeFormat) {
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        return new StringBuilder(format.format(new Date()));
    }
}
