import java.util.ArrayDeque;
import java.util.Scanner;

public class SimpleTextEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        ArrayDeque<String> textHistory = new ArrayDeque<>();
        StringBuilder text = new StringBuilder();


        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().split("\\s+");
            String command = tokens[0];
            String value = tokens.length > 1 ? tokens[1] : null;

            switch (command) {
                // appends value to the end of the text
                case "1":
                    textHistory.push(text.toString());
                    text.append(value);
                    break;

                // erases the last number(value) of elements from the text
                case "2":
                    textHistory.push(text.toString());
                    int toDelete = Integer.parseInt(value);
                    text.setLength(text.length() - Integer.parseInt(value));
                    break;

                // returns the element at position index(value) from the text
                case "3":
                    int index = Integer.parseInt(tokens[1]) - 1;
                    System.out.println(text.charAt(index));
                    break;

                // undoes the last not undone command of type 1 / 2
                // and returns the text to the state before that operation
                case "4":
                    text = new StringBuilder(textHistory.pop());
                    break;
            }
        }
    }


}
