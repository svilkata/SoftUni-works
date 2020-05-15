import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Dwarf> dwarves = new ArrayList<>();
        String input = sc.nextLine();
        while (!"Once upon a time".equals(input)) {
//            {dwarfName} <:> {dwarfHatColor} <:> {dwarfPhysics}
            String[] tokens = input.split(" <:> ");
            String name = tokens[0];
            String hatColour = tokens[1];
            int physics = Integer.parseInt(tokens[2]);

            Dwarf obj = new Dwarf(name, hatColour, physics);

            if (isDwarfNew(obj, dwarves)) {
                dwarves.add(obj);
            }

            input = sc.nextLine();
        }

        dwarves
                .stream()
                .sorted((x1, x2) -> {
                    int result = Integer.compare(x2.getPhysics(), x1.getPhysics());
                    if (x1.getPhysics() == x2.getPhysics()) {
                        int x1ColourHatCount = 0;
                        int x2ColourHatCount = 0;
                        for (Dwarf dwarf : dwarves) {
                            if (dwarf.getHatColour().equals(x1.getHatColour())) {
                                x1ColourHatCount++;
                            }
                            if (dwarf.getHatColour().equals(x2.getHatColour())) {
                                x2ColourHatCount++;
                            }
                        }
                        result = Integer.compare(x2ColourHatCount, x1ColourHatCount);
                    }
                    return result;
                })
                .forEach(s -> {
                    System.out.println(String.format("(%s) %s <-> %d", s.getHatColour(), s.getName(), s.getPhysics()));
                });
    }
    private static boolean isDwarfNew(Dwarf obj, List<Dwarf> dwarves) {
        boolean isNew = true;
        for (Dwarf dwarf : dwarves) {
            if (!dwarf.getName().equals(obj.getName())) { // не се съдържа името на Джуджето, значи е ново джудже
                return isNew;
            } else {
                if (!dwarf.getHatColour().equals(obj.getHatColour())) { //съдържа се името на джуджето, но цвет е различен, значи ново джудже
                    return isNew;
                } else { //името и цвета на джуджето съвпадат - презаписваме ако е с по-голяма физика;
                    if (dwarf.getPhysics() < obj.getPhysics()) {
                        dwarf.setPhysics(obj.getPhysics());
                    }
                    isNew = false;
                    return isNew;
                }
            }
        }
        return isNew;
    }
}

class Dwarf {
    private String name;
    private String hatColour;
    private int physics;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHatColour() {
        return hatColour;
    }

    public void setHatColour(String hatColour) {
        this.hatColour = hatColour;
    }

    public int getPhysics() {
        return physics;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public Dwarf(String name, String hatColour, int physics) {
        this.name = name;
        this.hatColour = hatColour;
        this.physics = physics;
    }
}








