package OrderByAge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<OrderByAge> orderByAgeList = new ArrayList<>();
        String input = sc.nextLine();
        while (!"End".equals(input)) {
            String[] token = input.split(" ");

            OrderByAge newPerson = new OrderByAge(token[0], token[1], Integer.parseInt(token[2]));
            orderByAgeList.add(newPerson);

            input = sc.nextLine();
        }

        orderByAgeList
                .stream()
                .sorted(Comparator.comparingInt(p -> p.getAge()))
                .forEach(p -> System.out.println(p));

    }
}
