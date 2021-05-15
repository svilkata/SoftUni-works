import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> inputNumbers = new ArrayList<Integer>();

        for (int i = 0; i < 8; i++) {
            inputNumbers.add(i);
        }

        inputNumbers.set(4, 0);
        inputNumbers.set(5, 0);
        inputNumbers.set(6, 0);

//        inputNumbers.removeAll(new ArrayList<Integer>() {{add(0);}});
        inputNumbers.removeAll(new ArrayList<Integer>(Arrays.asList(0)));
        System.out.println(inputNumbers);
        System.out.println(inputNumbers.toString().replaceAll("[\\[\\],]", ""));
    }
}
