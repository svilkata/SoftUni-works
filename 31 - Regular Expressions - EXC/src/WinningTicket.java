import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinningTicket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tickets = sc.nextLine().split(",");
        for (int i = 0; i < tickets.length; i++) {
            tickets[i] = tickets[i].replaceAll(" ", "");
            printResult(tickets[i]);
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

        if (matcherLeft.find() && matcherRight.find()) {
            String biggerSix = "";
            String smallerSix = "";
            if (matcherLeft.group().length() > matcherRight.group().length()) {
                biggerSix = matcherLeft.group();
                smallerSix = matcherRight.group();
            } else {
                smallerSix = matcherLeft.group();
                biggerSix = matcherRight.group();
            }

            if (biggerSix.contains(smallerSix)) {
                if (biggerSix.length() == smallerSix.length()) {
                    if (biggerSix.length() == 10) {
                        System.out.println(String.format("ticket \"%s\" - %d%c Jackpot!",
                                ticket, biggerSix.length(), biggerSix.charAt(0)));
                    } else { //6-9
                        System.out.println(String.format("ticket \"%s\" - %d%c",
                                ticket, smallerSix.length(), smallerSix.charAt(0)));
                    }
                } else {
                    System.out.println(String.format("ticket \"%s\" - %d%c",
                            ticket, smallerSix.length(), smallerSix.charAt(0)));
                }
                return;
            } else {
                System.out.println(String.format("ticket \"%s\" - no match", ticket));
                return;
            }
        } else {
            System.out.println(String.format("ticket \"%s\" - no match", ticket));
            return;
        }


    }
}
