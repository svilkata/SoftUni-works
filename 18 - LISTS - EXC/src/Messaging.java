import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Messaging {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> numbers = Arrays.stream(sc.nextLine().split(" ")).collect(Collectors.toList());
        List<String> message = Arrays.stream(sc.nextLine().split("")).collect(Collectors.toList());

        int[] numbersSums = new int[numbers.size()];
        for (int l = 0; l < numbers.size(); l++) {
            int curSum = 0;
            for (int k = 0; k < numbers.get(l).length(); k++) {
                int curDigit = Integer.parseInt(numbers.get(l).charAt(k) + "");
                curSum += curDigit;
            }
            numbersSums[l] = curSum;
        }


        int i = -1;
        String outputString = "";
        while (message.size() != numbersSums.length) {
            i++;
            if (i == numbersSums.length) {
                break;
            }
            int curSumIndex = numbersSums[i];

            if (curSumIndex >= message.size()) {
                curSumIndex = curSumIndex % message.size();
            }

            outputString += message.get(curSumIndex);
            message.remove(curSumIndex);

            //System.out.println(message);

        }

        System.out.println(outputString);
    }
}
