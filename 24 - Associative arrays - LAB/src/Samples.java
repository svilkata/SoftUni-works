import java.util.*;

public class Samples {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = sc.nextLine().split("\\s+");
        int[] points = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Map<String, Integer> map = new TreeMap<>();

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int point = points[i];

            if (!map.containsKey(name)) {
                map.put(name, point);
            }
        }

//        for (Map.Entry<String, Integer> keyValuePair : map.entrySet()) {
//            String key = keyValuePair.getKey();
//            int value = keyValuePair.getValue();
//            System.out.printf("Name: %s - Age : %d%n", keyValuePair.getKey(), keyValuePair.getValue());
//        }

        for (String s : map.keySet()) {
            System.out.printf("Name - %s%n", s.toUpperCase());
            System.out.printf("Age - %d!", map.get(s));
        }

//        for (Integer value : map.values()) {
//            System.out.printf("Age - %d%n", value);
//        }

    }

}
