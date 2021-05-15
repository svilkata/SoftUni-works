package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Class clazz = RichSoilLand.class;
        Field[] declaredFields = clazz.getDeclaredFields();

        String input = sc.nextLine();
        while (!input.equals("HARVEST")) {
            for (Field declaredField : declaredFields) {
                String modifier = Modifier.toString(declaredField.getModifiers());
                if (modifier.equals(input) || input.equals("all")) {
                    String typeOfData = declaredField.getType().getSimpleName();
                    String name = declaredField.getName();
                    System.out.printf("%s %s %s%n", modifier, typeOfData, name);
                }
            }

            input = sc.nextLine();
        }

    }
}
