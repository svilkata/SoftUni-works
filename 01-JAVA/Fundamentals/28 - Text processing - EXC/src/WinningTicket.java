import java.util.Scanner;

public class WinningTicket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tickets = sc.nextLine().split("\\s*(,|\\s)\\s*");

        for (String ticket : tickets) {
            printResult(ticket);
        }


    }

    private static void printResult(String ticket) {
        if (ticket.length() != 20) {
            System.out.println("invalid ticket");
            return;
        }

        String leftTicket = ticket.substring(0, 10);
        String rightTicket = ticket.substring(10, 20);

        String sixLeft = sixConsecutive(leftTicket);
        String sixRight = sixConsecutive(rightTicket);

        if (sixLeft.equals("noMatch") || sixRight.equals("noMatch")) {
            System.out.println(String.format("ticket \"%s\" - no match", ticket));
            return;
        }

        String biggerSix = "";
        String smallerSix = "";
        if (sixLeft.length() > sixRight.length()) {
            biggerSix = sixLeft;
            smallerSix = sixRight;
        } else {
            biggerSix = sixRight;
            smallerSix = sixLeft;
        }

        if (biggerSix.contains(smallerSix)) {
            if (biggerSix.length() == smallerSix.length()) {
                if (sixLeft.length() == 10) {
                    System.out.println(String.format("ticket \"%s\" - %d%c Jackpot!",
                            ticket, sixLeft.length(), sixLeft.charAt(0)));
                } else {
                    System.out.println(String.format("ticket \"%s\" - %d%c",
                            ticket, sixLeft.length(), sixLeft.charAt(0)));
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


               //  return;
    }

    private static String sixConsecutive(String leftTicket) {
        StringBuilder sixOK = new StringBuilder();
        String match = "noMatch";
        for (int i = 0; i < leftTicket.length() - 1; i++) {
            sixOK.append(leftTicket.charAt(i));

            for (int j = i + 1; j < leftTicket.length(); j++) {
                if (leftTicket.charAt(i) != leftTicket.charAt(j)) {
                    sixOK.setLength(0);
                    i = j - 1;
                    break;
                } else {
                    sixOK.append(leftTicket.charAt(j));
                    i = j - 1;
                }

                if (sixOK.length() >= 6) {
                    match = sixOK.toString();
                }
            }


        }

        if (match.contains("@") || match.contains("#") || match.contains("$") || match.contains("^")) {
            return match;
        }

        return "noMatch";

    }

}
