import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetherRealms {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] namesDevils = sc.nextLine().split(",");
        for (int i = 0; i < namesDevils.length; i++) {
            namesDevils[i] = namesDevils[i].replaceAll(" ", "");
            namesDevils[i] = namesDevils[i].trim();
        }

        TreeMap<String, HashMap<Integer, Double>> participants = new TreeMap<>();

//        Pattern healthCalc = Pattern.compile("[^0-9*/+\\-.]");
//        "[^\\d\\-+*/.]"
        Pattern damageCalc = Pattern.compile("((?:-|\\+|)\\d+(?:\\.*?\\d+)*)");
//
//        "(-?\\d*\\.?\\d+)"
//        "(-?\d+.\d+)|(-?\d+)"

        for (int i = 0; i < namesDevils.length; i++) {
            String currDevil = namesDevils[i];
//            if (currDevil.isEmpty()) {
//                continue;
//            }
            int currDevilHealth = 0;
//            Matcher healthMatcher;
//            double multiplDamage = 1.0;
            int count = 0;
            for (int j = 0; j < currDevil.length(); j++) {
//                healthMatcher = healthCalc.matcher(currDevil.charAt(j) + "");
//                if (healthMatcher.find()) {
//                    currDevilHealth += (int) currDevil.charAt(j);
//                }
                char charAt = currDevil.charAt(j);
                if (charAt != '+' && charAt != '-'
                        && charAt != '*' && charAt != '/'
                        && charAt != '.' && !Character.isDigit(charAt)) {
                    currDevilHealth += charAt;
                }

                if (currDevil.charAt(j) == '*') {
                    count++;
                }

                if (currDevil.charAt(j) == '/') {
                    count--;
                }
            }

            Matcher damageMatcher = damageCalc.matcher(currDevil);
            double sumDamage = 0.0;
            while (damageMatcher.find()) {
                sumDamage += Double.parseDouble(damageMatcher.group(0));
            }
            sumDamage *= Math.pow(2, count);

            participants.put(currDevil, new HashMap<>());
            participants.get(currDevil).put(currDevilHealth, sumDamage);
        }

        participants
                .entrySet()
                .stream()
                .forEach(x -> {
                    System.out.print(String.format(x.getKey()) + " ");

                    x.getValue()
                            .entrySet()
                            .stream()
                            .forEach(z -> {
                                System.out.println(String.format("- %d health, %.2f damage",
                                        z.getKey(), z.getValue()));
                            });
                });
    }

}
