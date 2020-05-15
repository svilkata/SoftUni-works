import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChangeList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> numbers = Arrays.stream(sc.nextLine().split("\\s+")).collect(Collectors.toList());

        String input = sc.nextLine();
        while (!"end".equals(input)) {
            String[] tokens = input.split("\\s+");
            switch (tokens[0]){
                case "Delete": {
                    while (numbers.contains(tokens[1])) {
                        numbers.remove(tokens[1]);
                    }
                    break;
                }
                case "Insert": {
                    String element = tokens[1];
                    int index = Integer.parseInt(tokens[2]);
                    if (index < numbers.size()) {
                        numbers.add(index, element);
                    }

                    break;
                }
            }
            input = sc.nextLine();
        }

        System.out.println(String.join(" ", numbers));


    }
}
