import java.util.Scanner;

public class StringMatrixRotation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rotations = Integer.parseInt(sc.nextLine().split("[\\(\\)]")[1]);

        String line = "";

        String input = "";
        int maxLength = 0;
        while (!(input = sc.nextLine()).equals("END")) {
            line += input + "\n";

            if (input.length() > maxLength) {
                maxLength = input.length();
            }
        }

        String[] matrix = line.split("\n");

        rotations %= 360;

        String output = "";

        switch (rotations) {
            case 0:
//                System.out.println(line);
                System.out.println(String.join("\n", matrix));
                break;
            case 90:
                for (int i = 0; i < maxLength; i++) { //cols
                    for (int j = matrix.length - 1; j >= 0; j--) {  //rows

                        try {
                            output += matrix[j].charAt(i);
                        } catch (Exception e) {
                            output += " ";
                        }
                    }
                    output += "\n";
                }

                System.out.println(output);
                break;
            case 180:
                System.out.println(new StringBuilder(line).reverse());
                break;
            case 270:
                for (int i = 0; i < maxLength; i++) { //cols
                    for (int j = matrix.length - 1; j >= 0; j--) {  //rows
                        try {
                            output += matrix[j].charAt(i);
                        } catch (Exception e) {
                            output += " ";
                        }
                    }
                    output += "\n";
                }

                System.out.println(new StringBuilder(output).reverse());
                break;
        }


    }
}
