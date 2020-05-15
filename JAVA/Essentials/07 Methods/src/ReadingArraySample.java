import java.util.Scanner;


public class ReadingArraySample {

    static int[] readNextArray(Scanner sc) {
        int length = sc.nextInt();
        int[] numbers = new int[length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = sc.nextInt();
        }

        return numbers;
    }

    static void printArray(int[] numbers){
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbers = readNextArray(sc);

        printArray(numbers);



    }
}
