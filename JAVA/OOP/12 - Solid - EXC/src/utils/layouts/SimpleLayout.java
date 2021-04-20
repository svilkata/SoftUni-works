package utils.layouts;

public class SimpleLayout implements Layout {

    @Override
    public String format(String time, String reportLevel, String message) {
//        "3/31/2015 5:33:07 PM - ERROR - Error parsing request"
        return String.format("%s - %s - %s", time, reportLevel, message);
    }
}
