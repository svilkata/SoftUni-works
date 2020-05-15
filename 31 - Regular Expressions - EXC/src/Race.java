import javax.imageio.ImageTranscoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Race {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, Integer> results = new LinkedHashMap<>();

        String[] data = sc.nextLine().split(", ");
        ArrayList<String> racers = new ArrayList<>(Arrays.asList(data));

        Pattern letterPattern = Pattern.compile("[A-Z]|[a-z]");
        Pattern digitPattern = Pattern.compile("\\d");

        String input = sc.nextLine();
        while (!"end of race".equals(input)) {
            Matcher nameMatcher = letterPattern.matcher(input);
            StringBuilder name = new StringBuilder();
            while (nameMatcher.find()) {
                name.append(nameMatcher.group());
            }

            if (racers.contains(name.toString())) {
                results.putIfAbsent(name.toString(), 0);
                int oldKm = results.get(name.toString());
                int newKm = 0;
                Matcher digitMatcher = digitPattern.matcher(input);
                while (digitMatcher.find()) {
                    newKm += Integer.parseInt(digitMatcher.group());
                }

                results.put(name.toString(), oldKm + newKm);
            }

            input = sc.nextLine();
        }

        int[] number = {1};

        results
                .entrySet()
                .stream()
                .sorted((r1, r2) -> Integer.compare(r2.getValue(), r1.getValue()))
                .limit(3)
                .forEach(r -> {
                    switch (number[0]) {
                        case 1:{
                            System.out.println("1st place: " + r.getKey());
                            break;
                        }
                        case 2: {
                            System.out.println("2nd place: " + r.getKey());
                            break;
                        }
                        case 3: {
                            System.out.println("3rd place: " + r.getKey());
                            break;
                        }
                    }
                    number[0]++;
                });

    }
}
