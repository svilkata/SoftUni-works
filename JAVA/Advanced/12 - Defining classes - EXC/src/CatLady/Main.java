package CatLady;

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
                    cat = new Cat(nameOfCat, typeCat, Double.parseDouble(tokens[2]));
                    break;
                case "Cymric":
                    cat = new Cat(nameOfCat, typeCat, Double.parseDouble(tokens[2]));
                    break;
                case "StreetExtraordinaire":
                    cat = new Cat(nameOfCat, typeCat, Double.parseDouble(tokens[2]));
                    break;
            }

            if (cat != null) {
                cats.add(cat);
            }
        }

        String catToSearch = reader.readLine();
        for (Cat cat : cats) {
            if (cat.getName().equals(catToSearch)) {
                System.out.printf("%s Maca 100.00", cat.getType(), cat);
            }

        }
        
        System.out.println();
    }
}
