import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _13_NetherRealms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = scanner.nextLine().split(",");

        String regDamage = "((?:-|\\+|)\\d+(?:\\.*?\\d+)*)";
        Pattern p = Pattern.compile(regDamage);
        Map<String, double[]> info = new TreeMap<>();

        for (int i = 0; i < names.length; i++) {

            double damage = 0;
            double health = 0;
            String currentName = names[i].trim();

            damage = getCalcDamage(p, damage, currentName);
            health = getCalcHealth(health, currentName);

            info.put(currentName, new double[]{health, damage});
        }

        for (Map.Entry<String, double[]> entry : info.entrySet()) {
            System.out.printf("%s - %d health, %.2f damage%n",
                    entry.getKey(), (int) entry.getValue()[0], entry.getValue()[1]);
        }
    }

    private static double getCalcDamage(Pattern p, double damage, String current) {
        Matcher m = p.matcher(current);
        while (m.find()) {
            damage += Double.parseDouble(m.group(0));
        }

        int multiplier = current.replaceAll("[^*]", "").length();
        int divisor = current.replaceAll("[^/]", "").length();
        for (int i = 0; i < Math.abs(multiplier - divisor); i++) {
            if (multiplier > divisor) {
                damage *= 2;
            } else {
                damage /= 2;
            }
        }

        return damage;
    }

    private static double getCalcHealth(double health, String current) {
        for (int i = 0; i < current.length(); i++) {
            char charAt = current.charAt(i);
            if (charAt != '+' && charAt != '-'
                    && charAt != '*' && charAt != '/'
                    && charAt != '.' && !Character.isDigit(charAt)) {

                health += charAt;
            }
        }
        return health;
    }
}