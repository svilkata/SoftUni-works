package utils.layouts;

public class MyFancyLayout implements Layout {
    @Override
    public String format(String time, String reportLevel, String message) {
        return time + "\r\n" + reportLevel + "\r\n" + message;
    }
}
