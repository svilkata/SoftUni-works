import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PopulationCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = "";
        LinkedHashMap<String, LinkedHashMap<String, Integer>> prepareData = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> countryTotalPopulation = new LinkedHashMap<>();
        LinkedHashMap<String, LinkedHashMap<String, Integer>> finalData = new LinkedHashMap<>();

        while (!"report".equals(line = sc.nextLine())) {
            String[] tokens = line.split("\\|");
            String country = tokens[1];
            String city = tokens[0];
            int peopleInCity = Integer.parseInt(tokens[2]);


            if (!prepareData.containsKey(country)) { //нова държава и нов град
                prepareData.put(country, new LinkedHashMap<>());
                prepareData.get(country).put(city, peopleInCity);

                countryTotalPopulation.put(country, peopleInCity);
            } else { //нов град в съществуваща държава
                if (!prepareData.get(country).containsKey(city)) {
                    prepareData.get(country).put(city, peopleInCity);

                    int updatedCountrypopulation = peopleInCity + countryTotalPopulation.get(country);
                    countryTotalPopulation.put(country, updatedCountrypopulation);
                }
            }
        }

//        LinkedHashMap<String, Integer> collect = countryTotalPopulation.entrySet().stream()
//                .sorted((f, s) -> {
//                    return Integer.compare(s.getValue(), f.getValue());
//                })
//                .collect(Collectors.toMap(e -> e.toString(), x -> Integer.valueOf(x)));

        for (Map.Entry<String, Integer> entry : countryTotalPopulation.entrySet()) {
            String country = entry.getKey();
            String countryComplex = entry.getKey() + " " + entry.getValue();
            LinkedHashMap<String, Integer> zzz = prepareData.get(country);
            finalData.put(countryComplex, zzz);
        }

        finalData.entrySet().stream()
                .sorted((f, s) -> {
                    int f1 = Integer.parseInt(f.getKey().split(" ")[1]);
                    int s1 = Integer.parseInt(s.getKey().split(" ")[1]);
                    return Integer.compare(s1, f1);
                })
                .forEach(a -> {

                });


        System.out.println();

    }
}
