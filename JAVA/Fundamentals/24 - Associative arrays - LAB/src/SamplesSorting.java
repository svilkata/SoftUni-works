import java.util.*;

public class SamplesSorting {
    public static void main(String[] args) {
        Map<String, List<Integer>> teams = new HashMap<>();
        teams.put("Sanow", Arrays.asList(1, 23, 45));
        teams.put("Sbnow", Arrays.asList(19, 39, 29));
        teams.put("Acb", Arrays.asList(45, 23, 12));

        teams.entrySet()
                .stream()
                .sorted((e1, e2) -> {
                            if (e1.getKey().compareTo(e2.getKey()) == 0) {
                                int sum1 = e1.getValue().stream().mapToInt(x -> Integer.parseInt(x+"")).sum();
                                int sum2 = e1.getValue().stream().mapToInt(x -> Integer.parseInt(x+"")).sum();

                                return sum1 - sum2; //връща 1 0 или -1
                            }
                            return e2.getKey().compareTo(e1.getKey());
                        }
                )
                .forEach(e -> {
                    System.out.println("Key :" + e.getKey());
                    System.out.println("Values -> ");
                    e.getValue().sort(Integer::compare);
                    for (Integer age : e.getValue()) {
                        System.out.printf("---%d%n", age);
                    }
                });

        System.out.println();
    }
}
