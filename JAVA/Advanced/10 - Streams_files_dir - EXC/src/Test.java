import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        int[] numbers = new int[]{1, 2, 3, 4, 5};
        int sum = Arrays.stream(numbers).reduce(0, (val, num) -> val += num);

        String[] words = new String[]{"hello", "pesho", "abc", "worlddd"};
        String longestWord = Arrays.stream(words).reduce("", (val, w) -> {
            if (w.length() > val.length()) {
                val = w;
            }
            return val;
        });

        System.out.println(longestWord);

        System.out.println(sum);

    }
}
