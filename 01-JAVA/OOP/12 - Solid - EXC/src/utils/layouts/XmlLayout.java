package utils.layouts;

public class XmlLayout implements Layout{
    @Override
    public String format(String time, String reportLevel, String message) {
        return String.format("<log>%n" +
                "   <date>%s</date>\n" +
                "   <level>%s</level>%n" +
                "   <message>%s</message>%n" +
                "</log>%n", time, reportLevel, message);
    }
}
