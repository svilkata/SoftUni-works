import java.util.Scanner;

public class DayOfWeek {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] day = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        int n = Integer.parseInt(sc.nextLine());

        if (n > 7 || n < 1) {
            System.out.println("Invalid day!");
        } else {
            for (int i = 0; i < day.length; i++) {
                if (n == i + 1) {
                    System.out.println(day[i]);
                }
            }
        }


    }
}
