package utils.appenders;

import utils.layouts.Layout;

public class ConsoleAppender extends AppenderImpl {
    public ConsoleAppender(Layout layout) {
        super(layout);
    }

    @Override
    public void append(String time, String reportLevel, String message) {
        this.incrementMessagesCount();
        System.out.println(this.getLayout().format(time, reportLevel, message));
    }
//
//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
