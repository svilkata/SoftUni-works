import java.util.*;

public class ForceBook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TreeMap<String, ArrayList<String>> forceBook = new TreeMap<>();
        String input = sc.nextLine();
        while (!"Lumpawaroo".equals(input)) {//            {forceSide} | {forceUser}
            String[] tokens;
            if (input.contains("|")) {
                tokens = input.split(" \\| ");
                String forceSide = tokens[0];
                String forceUser = tokens[1];
                boolean isUserIn = false;

                for (Map.Entry<String, ArrayList<String>> elem : forceBook.entrySet()) { //има ли го вече User-а
                    if (elem.getValue().contains(forceUser)) {
                        isUserIn = true;
                        break;
                    }
                }

                if (!isUserIn) { //със сигурност User-а е нов!!!
                    if (!forceBook.containsKey(forceSide)) {
                        forceBook.put(forceSide, new ArrayList<>());
                        forceBook.get(forceSide).add(forceUser);
                    } else if (forceBook.containsKey(forceSide) && !forceBook.get(forceSide).contains(forceUser)){
                        forceBook.get(forceSide).add(forceUser);
                    }
                }

//                if (!String.join("", forceBook.get(forceSide)).contains(forceUser)) {
//                    forceBook.get(forceSide).add(forceUser);
//                }
            } else if (input.contains(" -> ")) {
                //            {forceUser} -> {forceSide}
                tokens = input.split(" -> ");
                String forceSide = tokens[1];
                String forceUser = tokens[0];

                for (Map.Entry<String, ArrayList<String>> elem : forceBook.entrySet()) {
                    if (elem.getValue().contains(forceUser)) {
                        elem.getValue().remove(forceUser);
                        break;
                    }
                }

                if (!forceBook.containsKey(forceSide)) {
                    forceBook.put(forceSide, new ArrayList<>());
                    forceBook.get(forceSide).add(forceUser);
                    System.out.println(String.format("%s joins the %s side!", forceUser, forceSide));
                } else if (forceBook.containsKey(forceSide) && !forceBook.get(forceSide).contains(forceUser)) {
                    forceBook.get(forceSide).add(forceUser);
                    System.out.println(String.format("%s joins the %s side!", forceUser, forceSide));
                }
            }
            input = sc.nextLine();
        }

        forceBook
                .entrySet()
                .stream()
                .filter(users -> users.getValue().size() > 0)
                .sorted((v1, v2) -> Integer.compare(v2.getValue().size(), v1.getValue().size()))
                .forEach(z -> {
                    System.out.println(String.format("Side: %s, Members: %d", z.getKey(), z.getValue().size()));

                    z.getValue()
                            .stream()
                            .sorted((s1, s2) -> s1.compareTo(s2))
                            .forEach(s -> System.out.println(String.format("! %s", s)));
                });
    }
}
