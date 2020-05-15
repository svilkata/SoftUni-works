package AdvMess;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] phrases = new String[]{"Excellent product.",
                "Such a great product.", "I always use that product.",
                "Best product of its category.", "Exceptional product.",
                "I can’t live without this product."};

        String[] events = new String[]{"Now I feel good.", "I have succeeded with this product.",
                "Makes miracles. I am happy of the results!", "I cannot believe but now I feel awesome.",
                "Try it yourself, I am very satisfied.", "I feel great!"};

        String[] authors = new String[]{"Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva"};
        String[] cities = new String[]{"Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};

        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String phrase = phrases[random.nextInt(phrases.length)];
            String event = events[random.nextInt(events.length)];
            String name = authors[random.nextInt(authors.length)];
            String city = cities[random.nextInt(cities.length)];
            String result = String.format("%s %s %s - %s",
                    phrase, event, name, city);
            System.out.println(result);
        }


    }
}
