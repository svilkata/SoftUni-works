import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HouseParty {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> guestList = new ArrayList<>();
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= n; i++) {
            String[] tokens = sc.nextLine().split("\\s+", 3);

            String name = tokens[0];

            if (tokens[2].equals("going!")) {
                if (guestList.contains(name)) {
                    System.out.println(String.format("%s is already in the list!", name));
                } else {
                    guestList.add(tokens[0]);
                }
            } else {
                if (guestList.contains(name)) {
                    guestList.remove(name);
                } else {
                    System.out.println(String.format("%s is not in the list!", name));
                }
            }
        }

        //System.out.println(String.join(" ", guestList));
        printGuestList(guestList);

    }

    private static void printGuestList(List<String> guestList) {
        for (String s : guestList) {
            System.out.println(s);

        }
    }
}
