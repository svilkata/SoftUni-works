package borderControl;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Identifiable> storage = new HashMap<>();
//        List<String> allIDs = new ArrayList<>();

        String line = sc.nextLine();
        while (!"End".equals(line)) {
            String[] parts = line.split("\\s+");

            if (parts.length == 2) { //Robot
//                allIDs.add(parts[1]);
                storage.put(parts[1], new Robot(parts[0], parts[1]));
            } else { //= 3  Citizen
//                allIDs.add(parts[2]);
                storage.put(parts[2], new Citizen(parts[0], Integer.parseInt(parts[1]), parts[2]));
            }

            line = sc.nextLine();
        }

        String fakeIDendingOn = sc.nextLine();

//        allIDs.stream()
//                .filter( s -> s.endsWith(fakeIDendingOn))
//                .forEach(System.out::println);

        storage.keySet().stream()
                .filter( s -> s.endsWith(fakeIDendingOn))
                .forEach(System.out::println);
    }
}
