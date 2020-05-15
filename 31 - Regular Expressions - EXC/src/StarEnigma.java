import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StarEnigma {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        ArrayList<String> attackedPlanets = new ArrayList<>();
        ArrayList<String> destroyedPlanets = new ArrayList<>();

        Pattern pattern = Pattern.compile(
                "@(?<planetName>[A-Z][a-z]*)([^@\\-!:>]*):(\\d+)([^@\\-!:>]*)!(?<attackType>[AD])!([^@\\-!:>]*)->(\\d+)");

        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            int keyPower = 0;
            for (int j = 0; j < input.length(); j++)
                if ((input.charAt(j) + "").toLowerCase().equals("s") || (input.charAt(j) + "").toLowerCase().equals("t")
                        || (input.charAt(j) + "").toLowerCase().equals("a") || (input.charAt(j) + "").toLowerCase().equals("r")) {
                    keyPower++;
                }
            StringBuilder currDecryptedMessage = new StringBuilder();
            for (int j = 0; j < input.length(); j++) {
                currDecryptedMessage.append((char)((int)input.charAt(j)-keyPower));
            }

            Matcher matchMessage = pattern.matcher(currDecryptedMessage.toString());
            if (matchMessage.find()) {
                String attackType = matchMessage.group("attackType");
                String planetName = matchMessage.group("planetName");
                if (attackType.equals("A")) {
                    attackedPlanets.add(planetName);
                } else {
                    destroyedPlanets.add(planetName);
                }
            }
        }

        System.out.println(String.format("Attacked planets: %d", attackedPlanets.size()));
        //Collections.sort(attackedPlanets);
        attackedPlanets
                .stream()
                .sorted((k1, k2) -> k1.compareTo(k2))
                .forEach(x -> System.out.println(String.format("-> %s", x)));

        System.out.println(String.format("Destroyed planets: %d", destroyedPlanets.size()));
        //Collections.sort(attackedPlanets);
        destroyedPlanets
                .stream()
                .sorted((k1, k2) -> k1.compareTo(k2))
                .forEach(x -> System.out.println(String.format("-> %s", x)));


    }
}
