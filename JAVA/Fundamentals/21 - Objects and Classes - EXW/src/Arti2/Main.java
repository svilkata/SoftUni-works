package Arti2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Articles> articles = new ArrayList<>();


        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().split(", ");
            String title = input[0];
            String content = input[1];
            String author = input[2];
            Articles articles1 = new Articles(title, content, author);
            articles.add(articles1);
        }

        String command = sc.nextLine();
        printResult(command, articles);

        //System.out.println(article);
    }

    private static void printResult(String command, List<Articles> articles) {
        switch (command) {
            case "title": {
                articles
                        .stream()
                        .sorted((a1, a2) -> a1.getTitle().compareTo(a2.getTitle()))
                        .forEach(a -> System.out.println(a.toString()));
                break;
            }
            case "content": {
                articles
                        .stream()
                        .sorted((a1, a2) -> a1.getContent().compareTo(a2.getContent()))
                        .forEach(a -> System.out.println(a.toString()));
                break;
            }
            case "author": {
                articles
                        .stream()
                        .sorted((a1, a2) -> a1.getAuthor().compareTo(a2.getAuthor()))
                        .forEach(a -> System.out.println(a.toString()));
                break;
            }
        }

    }
}
