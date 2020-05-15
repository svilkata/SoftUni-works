import java.util.Scanner;

public class ZigZagArrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nLines = Integer.parseInt(sc.nextLine());

        String[] firstArr = new String[nLines];
        String[] secondArr = new String[nLines];

        for (int i = 0; i < nLines; i++) {
            String[] currentLine = sc.nextLine().split(" ");
            if (i % 2 == 0) {
                firstArr[i] = currentLine[0];
                secondArr[i] = currentLine[1];
            } else {
                firstArr[i] = currentLine[1];
                secondArr[i] = currentLine[0];
            }
        }

        for (String s : firstArr) {
            System.out.print(s + " ");
        }

        System.out.println();

        for (String s : secondArr) {
            System.out.print(s + " ");
        }


    }
}
