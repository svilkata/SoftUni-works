import java.util.Random;
import java.util.Scanner;

public class RandomizeWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] words = sc.nextLine().split(" ");

        Random rnd = new Random();
        rnd.nextInt(5);  //[0, 4]

        for (int i = 0; i < words.length ; i++) {
            int swapIndex = rnd.nextInt(words.length);

            String temp = words[i];
            words[i] = words[swapIndex];
            words[swapIndex] = temp;
        }

        for (String word : words) {
            System.out.println(word);
        }
    }
}
