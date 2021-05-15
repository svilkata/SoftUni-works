package PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] coordinates = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Rectangle rectangle = new Rectangle(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
        int numberOfPoints = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numberOfPoints; i++) {
            int[] ints = Arrays.stream(sc.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int pointX = ints[0];
            int pointY = ints[1];

            Point check = new Point(pointX, pointY);

            System.out.println(rectangle.contains(check));
        }


    }
}
