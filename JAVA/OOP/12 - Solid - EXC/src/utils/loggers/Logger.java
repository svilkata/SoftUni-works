package utils.loggers;

import utils.appenders.Appender;
import utils.enums.ReportLevel;

public interface Logger {
    void logError(String time, String message);
    void logInfo(String time, String message);
    void logWarning(String time, String message);
    void logCritical(String time, String message);
    void logFatal(String time, String message);

    void addAppender(Appender appender);
}
