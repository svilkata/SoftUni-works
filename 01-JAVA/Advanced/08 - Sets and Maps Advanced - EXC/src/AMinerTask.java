import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AMinerTask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Integer> miner = new LinkedHashMap<>();
        String line = "";
        String key = "";
        int countLines = 0;

        while (!"stop".equals(line = sc.nextLine())) {
            countLines++;
            if (countLines % 2 == 0) { //even
                int currSum = miner.get(key) + Integer.parseInt(line);
                miner.put(key, currSum);
            } else { //odd
                key = line;
                if (!miner.containsKey(key)) {
                    miner.put(key, 0);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : miner.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }


    }
}
