import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tickets = sc.nextLine().split(",");
        for (int i = 0; i < tickets.length; i++) {
            tickets[i] = tickets[i].replaceAll(" ", "");

            printResult(tickets[i]);
            System.out.println();
        }


    }

    private static void printResult(String ticket) {
        if (ticket.length() != 20) {
            System.out.println("invalid ticket");
            return;
        }

        String leftTicket = ticket.substring(0, 10);
        String rightTicket = ticket.substring(10, 20);

        Pattern pattern = Pattern.compile("(\\${6,10}|@{6,10}|#{6,10}|\\^{6,10})");
        Matcher matcherLeft = pattern.matcher(leftTicket);
        Matcher matcherRight = pattern.matcher(rightTicket);

        System.out.println(matcherLeft.find());
        System.out.println(matcherLeft.group().toString());
        System.out.println(matcherLeft.group(1));


        String biggerSix = "";


    }
}


