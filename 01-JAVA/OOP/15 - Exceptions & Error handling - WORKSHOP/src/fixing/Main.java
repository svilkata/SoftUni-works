package fixing;

public class Main {
    public static void main(String[] args) {
        String[] weekdays = new String[5];

        weekdays[0] = "Monday";
        weekdays[1] = "Tuesday";
        weekdays[2] = "Wednesday";
        weekdays[3] = "Thursday";
        weekdays[4] = "Friday";

        String toPrint;
        for (int i = 0; i <= weekdays.length ; i++) {
            try {
                toPrint = weekdays[i];
                System.out.println(toPrint);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
