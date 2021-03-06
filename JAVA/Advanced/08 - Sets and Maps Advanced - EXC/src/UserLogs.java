import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class UserLogs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = "";

        TreeMap<String, LinkedHashMap<String, Integer>> usersLogs = new TreeMap<>();

        while (!(line = sc.nextLine()).equals("end")) {
            String[] tokens = line.split("\\s+");
            String ip = tokens[0].split("=")[1];
            String username = tokens[2].split("=")[1];

            if (!usersLogs.containsKey(username)) {
                usersLogs.put(username, new LinkedHashMap<>());
                usersLogs.get(username).put(ip, 1);
            } else {
                if (!usersLogs.get(username).containsKey(ip)) {
                    usersLogs.get(username).put(ip, 1);
                } else {
                    int count = usersLogs.get(username).get(ip) + 1;
                    usersLogs.get(username).put(ip, count);
                }
            }
        }

        usersLogs.forEach((key, value) -> {
            System.out.println(key + ":");
            StringBuilder sb = new StringBuilder();
            value.forEach((innerKey, innerValue) -> {
//                System.out.printf("%s => %d%n", innerKey, innerValue);
                sb.append(String.format("%s => %d, ", innerKey, innerValue));
            });
            System.out.println(sb.substring(0, sb.length()-2) + ".");
        });

    }
}
