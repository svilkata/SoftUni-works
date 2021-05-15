import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Scanner;

public class PrinterQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> Commands = new ArrayDeque<>(); //queue
        String input = sc.nextLine();

        while (!"print".equals(input)) {

            if ("cancel".equals(input)) {

                if (Commands.size() < 1) {
                    System.out.println("Printer is on standby");

                } else {
                    //printCommands.pollFirst();
                    //System.out.println(printCommands.peek());
                    System.out.println("Canceled " + Commands.peek());
                    Commands.poll();
                    //System.out.println(Commands.size());

                }
            }

            else {
                Commands.offer(input);
                //System.out.println(printCommands);
            }


            input = sc.nextLine();
            if ("print".equals(input)) {
                for (String element : Commands) {
                    System.out.println(element);
                }

            }
        }
    }

}
