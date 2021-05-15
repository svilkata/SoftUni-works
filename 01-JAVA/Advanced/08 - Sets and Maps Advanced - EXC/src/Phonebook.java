import java.util.HashMap;
        import java.util.Map;
        import java.util.Scanner;

public class Phonebook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, String> phonebook = new HashMap<>();
        String line = "";

        while (!(line = sc.nextLine()).equals("search")) {
            String[] tokens = line.split("-");
            phonebook.put(tokens[0], tokens[1]);
        }

        while (!(line = sc.nextLine()).equals("stop")) {
            if (phonebook.containsKey(line)) {
                System.out.printf("%s -> %s%n", line, phonebook.get(line));
            } else {
                System.out.printf("Contact %s does not exist.%n", line);
            }
        }
    }
}
