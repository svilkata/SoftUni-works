import java.util.*;

public class CitiesContinentCountry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Map<String, Map<String, List<String>>> allData = new LinkedHashMap<>();

        while (n-- > 0) {
            String[] tokens = sc.nextLine().split("\\s+");

            String continent = tokens[0];
            String country = tokens[1];
            String city = tokens[2];

            if (!allData.containsKey(continent)) {
                allData.put(continent, new LinkedHashMap<>());
                allData.get(continent).put(country, new ArrayList<>());
                allData.get(continent).get(country).add(city);
            } else {
                if (!allData.get(continent).containsKey(country)) {
                    allData.get(continent).put(country, new ArrayList<>());
                    allData.get(continent).get(country).add(city);
                } else {
                    allData.get(continent).get(country).add(city);
                }
            }
        }

        for (Map.Entry<String, Map<String, List<String>>> entry : allData.entrySet()) {
            System.out.println(entry.getKey() + ":");

            for (Map.Entry<String, List<String>> innerEntry : entry.getValue().entrySet()) {
                System.out.println("  " + innerEntry.getKey() + " -> " + String.join(", ", innerEntry.getValue()));
            }
        }
    }
}
