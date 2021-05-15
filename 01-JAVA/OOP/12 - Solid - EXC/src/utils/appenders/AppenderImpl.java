package utils.appenders;

import utils.enums.ReportLevel;
import utils.layouts.Layout;

public abstract class AppenderImpl implements Appender{
    private Layout layout;
    private ReportLevel reportLevel;
    protected int numberOfAppendedMessages;

    protected AppenderImpl(Layout layout) {
        this.layout = layout;
        this.reportLevel = ReportLevel.INFO;
        this.numberOfAppendedMessages = 0;
    }

    protected Layout getLayout(){
        return this.layout;
    }

    protected void incrementMessagesCount() {
        this.numberOfAppendedMessages++;
    }

    @Override
    public void setReportLevel(ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
    }

    @Override
    public ReportLevel getReportLevel() {
        return this.reportLevel;
    }

    @Override
    public abstract void append(String time, String reportLevel, String message);


    //    Appender type: ConsoleAppender, Layout type: SimpleLayout, Report level: CRITICAL, Messages appended: 2
    @Override
    public String toString() {
        return String.format("Appender type: %s, Layout type: %s, Report level: %s, Messages appended: %d",
                this.getClass().getSimpleName(),
                this.layout.toString(),////this.layout.getClass().getSimpleName()
                this.reportLevel.toString(),
                this.numberOfAppendedMessages
                );
    }
}
