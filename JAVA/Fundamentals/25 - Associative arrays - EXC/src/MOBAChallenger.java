import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Scanner;

public class MOBAChallenger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashMap<String, HashMap<String, Integer>> allPlayers = new HashMap<>();

        String input = sc.nextLine();
        while (!"Season end".equals(input)) {

            if (input.contains(" -> ")) {
                String[] tokens = input.split(" -> ");
                String playerName = tokens[0];
                String positionSkill = tokens[1];
                int skillPoints = Integer.parseInt(tokens[2]);

                allPlayers.putIfAbsent(playerName, new HashMap<>());
                allPlayers.get(playerName).putIfAbsent(positionSkill, -1);
                if (skillPoints > allPlayers.get(playerName).get(positionSkill)) {
                    allPlayers.get(playerName).put(positionSkill, skillPoints);
                }
            } else if (input.contains(" vs ")) {
                String[] tok = input.split(" vs ");
                String pl1 = tok[0];
                String pl2 = tok[1];
                if (allPlayers.containsKey(pl1) && allPlayers.containsKey(pl2)) {
                    int sumPlayer1 = 0;
                    int sumPlayer2 = 0;
                    boolean positionsInCommon = false;
                    for (Map.Entry<String, Integer> posSkill : allPlayers.get(pl1).entrySet()) {
                        if (allPlayers.get(pl2).containsKey(posSkill.getKey())) { //имаме дуел
                            sumPlayer1 += posSkill.getValue();
                            sumPlayer2 += allPlayers.get(pl2).get(posSkill.getKey());
                            if (!positionsInCommon) {
                                positionsInCommon = true;
                            }
                        }
                    }

                    if (positionsInCommon && (sumPlayer1 != sumPlayer2)) {
                        if (sumPlayer1 > sumPlayer2) {
                            allPlayers.remove(pl2);
                        } else { //sumPlayer1 < sumPlayer2
                            allPlayers.remove(pl1);
                        }
                    }


                }
            }


            input = sc.nextLine();
        }


        allPlayers
                .entrySet()
                .stream()
                .sorted((el1, el2) -> {
                    int sum1 = el1.getValue()
                            .entrySet()
                            .stream()
                            .mapToInt(x -> (x.getValue()))
                            .sum();

                    int sum2 = el2.getValue()
                            .entrySet()
                            .stream()
                            .mapToInt(x -> (x.getValue()))
                            .sum();
                    int result = sum2 - sum1;
                    if (sum1 == sum2) {
                        result = el1.getKey().compareTo(el2.getKey());
                    }
                    return result;
                })
                .forEach(s -> {
                    System.out.println(String.format("%s: %d skill", s.getKey(),
                            s.getValue()
                                    .entrySet()
                                    .stream()
                                    .mapToInt(x -> x.getValue())
                                    .sum()));

                    s.getValue()
                            .entrySet()
                            .stream()
                            .sorted((z1, z2) -> {
                                int res = Integer.compare(z2.getValue(), z1.getValue());
                                if (z1.getValue() == z2.getValue()) {
                                    res = z1.getKey().compareTo(z2.getKey());
                                }
                                return res;
                            })
                            .forEach(z -> {
                                System.out.println(String.format("- %s <::> %d", z.getKey(), z.getValue()));
                            });
                });
    }
}
