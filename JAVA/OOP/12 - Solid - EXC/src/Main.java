import utils.appenders.*;
import utils.enums.ReportLevel;
import utils.helpOutFile.FileHelper;
import utils.helpOutFile.FileStorage;
import utils.layouts.*;
import utils.loggers.*;

import java.util.Scanner;

public class Main {
//    public static void main(String[] args) {
//
//    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        Logger logger = new MessageLogger();

        while (n-- > 0) {
            String[] tokens = sc.nextLine().split("\\s+");
            String appenderType = tokens[0];
            Layout layout = tokens[1].equals("SimpleLayout")
                    ? new SimpleLayout()
                    : new XmlLayout();
            Appender appender;
            ReportLevel reportLevel = null;



            if (appenderType.equals("ConsoleAppender")) {
                appender = new ConsoleAppender(layout);
                if (tokens.length == 3) {
                    reportLevel = ReportLevel.valueOf(tokens[2]);
                    appender.setReportLevel(reportLevel);
                }
            } else if (appenderType.equals("FileAppender")) {
                appender = new FileAppender(layout, new FileHelper());
                if (tokens.length == 3) {
                    reportLevel = ReportLevel.valueOf(tokens[2]);
                    appender.setReportLevel(reportLevel);
                }
            } else { //"SocketAppender"
                appender = new SocketAppender(layout);
                if (tokens.length == 3) {
                    reportLevel = ReportLevel.valueOf(tokens[2]);
                    appender.setReportLevel(reportLevel);
                }
            }

            logger.addAppender(appender);
        }

        String input = sc.nextLine();
        while (!"END".equals(input)) {
            String[] tokens = input.split("\\|");
            ReportLevel reportLevel = ReportLevel.valueOf(tokens[0]);
            String time = tokens[1];
            String message = tokens[2];
            switch (reportLevel) {
                case INFO:
                    logger.logInfo(time, message);
                    break;
                case WARNING:
                    logger.logWarning(time, message);
                    break;
                case ERROR:
                    logger.logError(time, message);
                    break;
                case CRITICAL:
                    logger.logCritical(time, message);
                    break;
                case FATAL:
                    logger.logFatal(time, message);
                    break;
            }

            input = sc.nextLine();
        }

        System.out.println(logger.toString());
    }

}
