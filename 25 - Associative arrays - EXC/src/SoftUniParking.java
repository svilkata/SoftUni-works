import java.util.LinkedHashMap;
import java.util.Scanner;

public class SoftUniParking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, String> parking = new LinkedHashMap<>();

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().split("\\s+");
            String command = tokens[0];
            String username = tokens[1];

            if ("register".equals(command)) {
                String number = tokens[2];
                if (parking.containsKey(username)) {
                    String id = parking.get(username);
                    System.out.println(String.format("ERROR: already registered with plate number %s", id));
                } else {
                    parking.put(username, number);
                    System.out.println(String.format("%s registered %s successfully", username,number));
                }
            } else {
                if (parking.containsKey(username)) {
                    parking.remove(username);
                    System.out.println(String.format("%s unregistered successfully", username));
                } else {
                    System.out.println(String.format("ERROR: user %s not found", username));
                }
            }
        }

        parking
                .entrySet()
                .forEach(u -> System.out.println(String.format("%s => %s", u.getKey(), u.getValue() )));

    }
}
