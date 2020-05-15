package SnowDwarfs;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Snowwhite {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, LinkedHashMap<String, Integer>> dwarfsAll = new LinkedHashMap<>();


        String input = sc.nextLine();
        while (!"Once upon a time".equals(input)) {
//            {dwarfName} <:> {dwarfHatColor} <:> {dwarfPhysics}
            String[] tokens = input.split(" <:> ");
            String hatColour = tokens[1];
            String name = tokens[0];
            int physics = Integer.parseInt(tokens[2]);

            dwarfsAll.putIfAbsent(name, new LinkedHashMap<>());
            dwarfsAll.get(name).putIfAbsent(hatColour, -1);
            if (dwarfsAll.get(name).get(hatColour) < physics) {
                dwarfsAll.get(name).put(hatColour, physics);
            }

            input = sc.nextLine();
        }

        dwarfsAll
                .entrySet()
                .stream()
                .sorted((dw1, dw2) -> {
                    int physiscSum1 = dw1.getValue()
                            .entrySet()
                            .stream()
                            .mapToInt(x -> Integer.parseInt(x.getValue() + ""))
                            .sum();

                    int physiscSum2 = dw2.getValue()
                            .entrySet()
                            .stream()
                            .mapToInt(x -> Integer.parseInt(x.getValue() + ""))
                            .sum();

                    int result = Integer.compare(physiscSum2, physiscSum1);
                    if (physiscSum1 == physiscSum2) {
                        LinkedHashMap<String, Integer> temp = new LinkedHashMap<>();

                            for (Map.Entry<String, Integer> el : dw1.getValue().entrySet()) {
                                String hatColourTemp = el.getKey();
                                temp.putIfAbsent(hatColourTemp, 0);
                                temp.put(hatColourTemp, temp.get(hatColourTemp) + 1);
                            }


                        int tmp1 = temp
                                .entrySet()
                                .stream()
                                .mapToInt(p -> Integer.parseInt(p.getValue()+""))
                                .max()
                                .getAsInt();

                        int tmp2 = temp
                                .entrySet()
                                .stream()
                                .mapToInt(p -> Integer.parseInt(p.getValue()+""))
                                .max()
                                .getAsInt();

                        result = Integer.compare(tmp2, tmp1);
//                        temp
//                                .entrySet()
//                                .stream()
//                                .sorted((z1, z2) -> {
//                                    return Integer.compare(z2.getValue().size(), z1.getValue().size());
//                                });
                    }
                    return result;
                })
                .forEach(z -> {
                    z.getValue()
                            .entrySet()
                            .stream()
                            .forEach(x -> {
                                System.out.println(String.format("(%s) %s <-> %d", x.getKey(), z.getKey(), x.getValue()));
                            });
                });
    }
}
