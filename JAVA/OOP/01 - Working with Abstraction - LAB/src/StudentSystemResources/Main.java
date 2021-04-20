package StudentSystemResources;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();

        String[] tokens = scanner.nextLine().split(" ");
        while (!"Exit".equals(tokens[0])) {
            studentSystem.executeCommand(tokens);

            tokens = scanner.nextLine().split(" ");
        }
    }
}
