import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SamplesLambda {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int max;
        max = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(n -> Integer.parseInt(n))
                .max()
                .getAsInt();


    }
}
