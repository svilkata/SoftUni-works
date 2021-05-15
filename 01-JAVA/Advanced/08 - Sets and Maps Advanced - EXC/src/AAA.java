import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AAA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = "";
        LinkedHashMap<String, Long> countriesPopulation = new LinkedHashMap<>();
        LinkedHashMap<String, LinkedHashMap<String, Long>> countriesCitiesPopulation = new LinkedHashMap<>();

        while (!"report".equals(line = sc.nextLine())) {
            String[] tokens = line.split("\\|");
            String country = tokens[1];
            String city = tokens[0];
            long peopleInCity = Long.parseLong(tokens[2]);

            if (!countriesCitiesPopulation.containsKey(country)) {
                countriesPopulation.put(country, peopleInCity);

                countriesCitiesPopulation.put(country, new LinkedHashMap<>() {{
                    put(city, peopleInCity);
                }});
            } else {
                if (!countriesCitiesPopulation.get(country).containsKey(city)) {
                    long updatedCountryPopulation = countriesPopulation.get(country) + peopleInCity;
                    countriesPopulation.put(country, updatedCountryPopulation);

                    countriesCitiesPopulation.get(country).put(city, peopleInCity);
                }
            }
        }

        List<Map.Entry<String, Long>> orderedCountriesPopulation = countriesPopulation.entrySet().stream()
                .sorted((f, s) -> {
                    return s.getValue().compareTo(f.getValue());
                })
                .collect(Collectors.toList());

        for (Map.Entry<String, Long> entry : orderedCountriesPopulation) {
            String country = entry.getKey();
            System.out.printf("%s (total population: %d)%n", country, entry.getValue());
            LinkedHashMap<String, Long> innerEntry = countriesCitiesPopulation.get(country);
            innerEntry.entrySet().stream()
                    .sorted((f, s) -> {
                        return Long.compare(s.getValue(), f.getValue());
                    })
                    .forEach(x -> {
                        System.out.printf("=>%s: %d%n", x.getKey(), x.getValue());
                    });
        }
    }
}
