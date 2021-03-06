package CatLady2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        List<Cat> cats = new ArrayList<>();
        while (!(line = reader.readLine()).equals("End")) {
            String[] tokens = line.split("\\s+");
            String typeCat = tokens[0];
            String nameOfCat = tokens[1];

            Cat cat = null;
            switch (typeCat) {
                case "Siamese":
                    cat = new Cat(nameOfCat, typeCat);
                    Siam siam = new Siam(typeCat, Double.parseDouble(tokens[2]));
                    cat.setSiam(siam);
                    break;
                case "Cymric":
                    cat = new Cat(nameOfCat, typeCat);
                    Cymric cymric = new Cymric(typeCat, Double.parseDouble(tokens[2]));
                    cat.setCymric(cymric);
                    break;
                case "StreetExtraordinaire":
                    cat = new Cat(nameOfCat, typeCat);
                    StreetExtraordinaire streetExtraordinaire = new StreetExtraordinaire(typeCat, Double.parseDouble(tokens[2]));
                    cat.setStreetExtraordinaire(streetExtraordinaire);
                    break;
            }

            if (cat != null) {
                cats.add(cat);
            }
        }

        String catToSearch = reader.readLine();
        for (Cat cat : cats) {
            if (cat.getName().equals(catToSearch)) {
                if (cat.getType().equals("Siamese")) {
                    System.out.printf("%s %s %.2f%n", cat.getType(), cat.getName(),
                            cat.getSiam().getEarSize());
                } else if (cat.getType().equals("Cymric")) {
                    System.out.printf("%s %s %.2f%n", cat.getType(), cat.getName(),
                            cat.getCymric().getFurLength());
                } else if (cat.getType().equals("StreetExtraordinaire")) {
                    System.out.printf("%s %s %.2f%n", cat.getType(), cat.getName(),
                            cat.getStreetExtraordinaire().getDecibelsOfMeows());
                }
            }
        }

    }
}
