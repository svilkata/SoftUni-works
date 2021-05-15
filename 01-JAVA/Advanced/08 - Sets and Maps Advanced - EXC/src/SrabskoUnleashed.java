import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SrabskoUnleashed {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = "";
        String pattern = "(?<singer>.*[^@]) (?<venue>@.*[^\\d]) (?<ticketPrice>[0-9]+) (?<ticketsCount>[0-9]+)";
        Pattern regex = Pattern.compile(pattern);

        LinkedHashMap<String, LinkedHashMap<String, Integer>> venuePerSingers = new LinkedHashMap<>();

        while (!"End".equals(line = sc.nextLine())) {
            Matcher matcher = regex.matcher(line);
            if (matcher.find()) {
                String singer = matcher.group("singer");
                String venue = matcher.group("venue").substring(1);
                int ticketPrice = Integer.parseInt(matcher.group("ticketPrice"));
                int ticketsCount = Integer.parseInt(matcher.group("ticketsCount"));

                if (!venuePerSingers.containsKey(venue)) {
                    venuePerSingers.put(venue, new LinkedHashMap<>());
                    venuePerSingers.get(venue).put(singer, ticketPrice * ticketsCount);
                } else {
                    if (!venuePerSingers.get(venue).containsKey(singer)) {
                        venuePerSingers.get(venue).put(singer, ticketPrice * ticketsCount);
                    } else {
                        int updatedBGNAmount = venuePerSingers.get(venue).get(singer) + ticketPrice * ticketsCount;
                        venuePerSingers.get(venue).put(singer, updatedBGNAmount);
                    }
                }
            }
        }

        venuePerSingers.entrySet().stream()
                .forEach(el -> {
                    System.out.println(el.getKey());

                    el.getValue().entrySet().stream()
                            .sorted((f, s) -> {
                                return Integer.compare(s.getValue(), f.getValue());
                            })
                            .forEach(innerEl -> {
                                System.out.printf("#  %s -> %d%n", innerEl.getKey(), innerEl.getValue());
                            });

                });

    }
}
