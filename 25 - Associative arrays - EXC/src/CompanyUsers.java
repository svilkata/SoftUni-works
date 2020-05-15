import java.util.*;

public class CompanyUsers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, List<String>> compUsers = new TreeMap<>();

        String input = sc.nextLine();
        while(!"End".equals(input)){
            String[] tokens = input.split(" -> ");
            String company = tokens[0];
            String userID = tokens[1];

            if (!compUsers.containsKey(company)) {
                compUsers.putIfAbsent(company, new ArrayList<>());
                compUsers.get(company).add(userID);
            } else { //имаме вече тази копмпания
                if (! (String.join(" ", compUsers.get(company))).contains(userID)) {//проверка за този служител
                    compUsers.get(company).add(userID);
                }
            }

            input = sc.nextLine();
        }

        compUsers
                .entrySet()
                .forEach(c -> {
                    System.out.println(c.getKey());

                    c.getValue()
                            .stream()
//                            .sorted((x1, x2) -> x1.compareTo(x2))
                            .forEach(s -> System.out.println(String.format("-- %s", s)));
                }
                );
    }
}
