import java.util.Scanner;

public class LongerLine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x1 = Double.parseDouble(sc.nextLine());
        double y1 = Double.parseDouble(sc.nextLine());
        double x2 = Double.parseDouble(sc.nextLine());
        double y2 = Double.parseDouble(sc.nextLine());
        double x3 = Double.parseDouble(sc.nextLine());
        double y3 = Double.parseDouble(sc.nextLine());
        double x4 = Double.parseDouble(sc.nextLine());
        double y4 = Double.parseDouble(sc.nextLine());

        double[] firstPairSecondPairCoordinates = new double[4];

        double line1Length = lineLength(x1, y1, x2, y2);
        double line2Length = lineLength(x3, y3, x4, y4);

        if (line1Length >= line2Length) {
            firstPairSecondPairCoordinates[0] = x1;
            firstPairSecondPairCoordinates[1] = y1;
            firstPairSecondPairCoordinates[2] = x2;
            firstPairSecondPairCoordinates[3] = y2;
            adjustFirstSecondPairOfLine(firstPairSecondPairCoordinates);
            printLongerLine(firstPairSecondPairCoordinates);
        } else {
            firstPairSecondPairCoordinates[0] = x3;
            firstPairSecondPairCoordinates[1] = y3;
            firstPairSecondPairCoordinates[2] = x4;
            firstPairSecondPairCoordinates[3] = y4;
            adjustFirstSecondPairOfLine(firstPairSecondPairCoordinates);
            printLongerLine(firstPairSecondPairCoordinates);
        }

    }

    private static void adjustFirstSecondPairOfLine(double[] firstPairSecondPairCoordinates) {
        double x1 = firstPairSecondPairCoordinates[0];
        double y1 = firstPairSecondPairCoordinates[1];
        double x2 = firstPairSecondPairCoordinates[2];
        double y2 = firstPairSecondPairCoordinates[3];
        double distanceFromZeroZero1 = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
        double distanceFromZeroZero2 = Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2));

        if (distanceFromZeroZero2 < distanceFromZeroZero1) {
            firstPairSecondPairCoordinates[0] = x2;
            firstPairSecondPairCoordinates[1] = y2;
            firstPairSecondPairCoordinates[2] = x1;
            firstPairSecondPairCoordinates[3] = y1;
        }
    }

    private static void printLongerLine(double[] arrLineCoordinates) {
        System.out.printf("(%.0f, %.0f)(%.0f, %.0f)", arrLineCoordinates[0], arrLineCoordinates[1],
                arrLineCoordinates[2], arrLineCoordinates[3]);
    }

    private static double lineLength(double x1, double y1, double x2, double y2) {
//        double distanceFromZeroZero1 = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
//        double distanceFromZeroZero2 = Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2));

        double line = Math.sqrt(Math.pow(Math.abs(x1-x2), 2) + Math.pow(Math.abs(y1-y2), 2));

        return line;
    }


}
