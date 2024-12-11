package PageTests.Wrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LoggerWrapper {
    Logger log;
    StringBuilder consoleLog;
    private final String timeFormat = "HH:mm:ss.SSSS";

    public LoggerWrapper(WebDriver driver) {
        log = LogManager.getLogger(this);
        consoleLog = new StringBuilder();
    }

    public void info(String msg) {
        log.info(msg);
        this.addToConsoleLog(msg);
    }

    public void info(Object msg) {
        log.info(msg);
        this.addToConsoleLog(msg);
    }

    public void addToConsoleLog(String msg) {
        consoleLog.append("[").append(LocalTime.now()
                .format(DateTimeFormatter.ofPattern(this.timeFormat))).append("] ");
        consoleLog.append(msg);
        consoleLog.append("\n");
    }

    public void addToConsoleLog(Object msg) {
        addToConsoleLog(msg.toString());
    }

    public StringBuilder getTestConsoleLog() {
        return consoleLog;
    }
}
