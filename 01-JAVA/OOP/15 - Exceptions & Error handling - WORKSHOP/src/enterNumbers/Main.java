package enterNumbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tokens = sc.nextLine().split("\\s+");
        int start;
        int end;
        boolean normalInitialData = false;
        while (!normalInitialData) {
            try {
                try {
                    start = Integer.parseInt(tokens[0]);
                    end = Integer.parseInt(tokens[1]);
                } catch (NumberFormatException ezz) {
                    normalInitialData = false;
                    throw new NotIntegerNum();
                }

                if (start < 1 || start > 100 || end < 1 || end > 100) {
                    normalInitialData = false;
                    throw new NotInRangeInegerNum();
                }
                if (start > end) {
                    normalInitialData = false;
                    throw new StartBiggerThanEnd();
                }
                printNumbers(start, end);
                normalInitialData = true;
            } catch (StartBiggerThanEnd exl) {
                System.out.println(exl.getMessage());
            } catch (NotInRangeInegerNum ex) {
                System.out.println(ex.getMessage());
            } catch (NotIntegerNum e) {
                System.out.println(e.getMessage());
            }

            if (!normalInitialData) {
                tokens = sc.nextLine().split("\\s+");
            }
        }

    }

    private static void printNumbers(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i);
        }
    }
}
