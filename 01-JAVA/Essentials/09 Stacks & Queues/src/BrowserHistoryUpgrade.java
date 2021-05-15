import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistoryUpgrade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        ArrayDeque<String> historyBackStack = new ArrayDeque<>(); // stack
        ArrayDeque<String> historyForwardQueue = new ArrayDeque<>(); //queue

        boolean backStrike = false;
        String temp = "";
        while (!input.equals("Home")) {


            switch (input) {
                case "back": {
                    if (historyBackStack.size() < 2) {
                        System.out.println("no previous URLs");

                    } else {
                        historyForwardQueue.addFirst(historyBackStack.peek());
                        historyBackStack.pop();
                        System.out.println(historyBackStack.peek());
                        //historyBackStack.push(temp); - слагаме го в раздела на Forward, само тогава трябва да се изпълнява
                    }
                    break;
                }

                case "forward": {
                    if (historyForwardQueue.size() < 1) {
                        System.out.println("no next URLs");
                    } else {
                        System.out.println(historyForwardQueue.peek());
                        historyBackStack.push(historyForwardQueue.peek()); //добавяме в Back на стека
                        historyForwardQueue.remove();
                    }
                    break;
                }

                default: {
                    historyBackStack.push(input);
                    System.out.println(historyBackStack.peek());

                    historyForwardQueue.clear(); //занулавяме Forward

                    break;
                }
            }

            input = sc.nextLine();
        }

    }
}
