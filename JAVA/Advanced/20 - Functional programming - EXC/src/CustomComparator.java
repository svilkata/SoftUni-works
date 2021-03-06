import java.util.Arrays;
import java.util.Scanner;

public class CustomComparator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .sorted((f, s) -> {
                    int result = Integer.compare(Math.abs(f % 2), Math.abs(s % 2));

                    if (result == 0) {
                        result = f - s;
                    }
                    
                    return result;
                } )
                .map(e -> e + " ")
                .forEach(System.out::print);
                
    }
}
