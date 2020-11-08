package design.logger;


import design.logger.enums.Level;
import design.logger.models.Console;
import design.logger.models.LogConfig;

import java.util.Scanner;

public class LoggerDriver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //LogConfig logConfig = new LogConfig("yyyy/MM/dd", Level.DEBUG, new File("/Users/chaitra.kr/learnings/Code-Playground/test.txt"));
        LogConfig logConfig = new LogConfig("yyyy/MM/dd", Level.DEBUG, new Console());
        LoggerClient.setLogConfig(logConfig);
        CustomLogger driveLogger = new CustomLogger(LoggerDriver.class);
        driveLogger.info(LoggerDriver.class.getSimpleName(), "Test Message");
    }
}
