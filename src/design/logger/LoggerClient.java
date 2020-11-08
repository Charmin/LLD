package design.logger;

import design.logger.appenders.Appender;
import design.logger.appenders.ConsoleAppender;
import design.logger.appenders.FileAppender;
import design.logger.enums.Level;
import design.logger.models.Console;
import design.logger.models.File;
import design.logger.models.LogConfig;
import design.logger.models.LogMessage;

import java.io.IOException;

public class LoggerClient {
    private Appender appender;
    private Level level;
    private static LogConfig config;
    private final String timeFormat;

    private static LoggerClient loggerClient;

    private LoggerClient() {
        this.appender = getAppender(config);
        this.level = config.getLogLevel();
        this.timeFormat = config.getTimeFormat();
    }

    public static void setLogConfig(LogConfig logConfig) {
        config = logConfig;
    }

    public static LoggerClient getInstance() {
        if (loggerClient == null) {
            loggerClient = new LoggerClient();
        }
        return loggerClient;
    }

    //if no appender is specified, return the console appender
    private Appender getAppender(LogConfig config) {
        switch (config.getSink().getType()) {
            case "File":
                File file = (File) config.getSink();
                appender = new FileAppender(file);
            case "Console":
                Console console = (Console) config.getSink();
                return new ConsoleAppender(console);
            default:
                System.out.println("Unknown sink type");
        }
        return new ConsoleAppender((Console) config.getSink());
    }

    public void log(String cname, String message, Level level) {
        try {
            if (level == null) {
                appender.publish(new LogMessage(cname, message, timeFormat, this.level));
            }
            else {
                appender.publish(new LogMessage(cname, message, timeFormat, level));
            }
        } catch (IOException e) {
            System.out.println("Error while publishing log");
        }
    }
}
