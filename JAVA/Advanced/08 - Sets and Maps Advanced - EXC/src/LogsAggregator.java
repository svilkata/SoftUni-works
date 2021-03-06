import java.util.*;

public class LogsAggregator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        TreeMap<String, Integer> durations = new TreeMap<>();
        HashMap<String, TreeSet<String>> ips = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().split("\\s+");
            String ip = tokens[0];
            String name = tokens[1];
            int duration = Integer.parseInt(tokens[2]);

            if (!ips.containsKey(name)) {
                ips.put(name, new TreeSet<>() {{add(ip);}});
                durations.put(name, duration);
            } else {
                Set<String> set = ips.get(name);
                if (set.contains(ip)) {
                    int updateDuration = durations.get(name) + duration;
                    durations.put(name, updateDuration);
                } else {
                    ips.get(name).add(ip);
                    int updateDuration = durations.get(name) + duration;
                    durations.put(name, updateDuration);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : durations.entrySet()) {
            String userName = entry.getKey();
            System.out.printf("%s: %d [%s]%n", userName, entry.getValue(),
                    String.join(", ", ips.get(userName)));
        }



    }
}
