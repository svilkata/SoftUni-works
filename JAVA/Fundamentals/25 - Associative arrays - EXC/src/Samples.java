import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Samples {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<Integer, Integer> map = new HashMap<>() {{
           put(2, 6);
        }};
        int result = map.remove(2);

        System.out.println(result);
    }
}
