import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class DragonArmy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        LinkedHashMap<String, TreeMap<String, int[]>> dragonsAll = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
//            {type} {name} {damage} {health} {armor}
            String[] tokens = sc.nextLine().split(" ");
            String type = tokens[0];
            String name = tokens[1];
            int damage, health, armor;
            if (tokens[2].equals("null")) {
                damage = 45;
            } else {
                damage = Integer.parseInt(tokens[2]);
            }

            if (tokens[3].equals("null")) {
                health = 250;
            } else {
                health = Integer.parseInt(tokens[3]);
            }

            if (tokens[4].equals("null")) {
                armor = 10;
            } else {
                armor = Integer.parseInt(tokens[4]);
            }

            dragonsAll.putIfAbsent(type, new TreeMap<>());
            dragonsAll.get(type).putIfAbsent(name, new int[3]);
//            if (dragonsAll.containsKey(type) && dragonsAll.get(type).containsKey(name)) { //сменяме данни за вече съществуващ дракон
//реално винаги въвеждаме
//            }
            dragonsAll.get(type).get(name)[0] = damage;
            dragonsAll.get(type).get(name)[1] = health;
            dragonsAll.get(type).get(name)[2] = armor;

        }

        dragonsAll
                .entrySet()
                .stream()
                .forEach(drgType -> {
                    double avrgDamage = drgType.getValue()
                            .entrySet()
                            .stream()
                            .mapToInt(x -> Integer.parseInt(x.getValue()[0] + ""))
                            .average()
                            .getAsDouble();

                    double avrgHealth = drgType.getValue()
                            .entrySet()
                            .stream()
                            .mapToInt(x -> Integer.parseInt(x.getValue()[1] + ""))
                            .average()
                            .getAsDouble();

                    double avrgArmor = drgType.getValue()
                            .entrySet()
                            .stream()
                            .mapToInt(x -> Integer.parseInt(x.getValue()[2] + ""))
                            .average()
                            .getAsDouble();

                    System.out.println(String.format("%s::(%.2f/%.2f/%.2f)",
                            drgType.getKey(), avrgDamage, avrgHealth, avrgArmor));

                    drgType.getValue()
                            .entrySet()
                            .stream()
                            .forEach(s -> {
                                System.out.println(String.format("-%s -> damage: %d, health: %d, armor: %d",
                                        s.getKey(), s.getValue()[0], s.getValue()[1], s.getValue()[2]));
                            });
                });
    }
}
