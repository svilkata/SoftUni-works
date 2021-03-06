package GenericCountMethodString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        List<Box<Double>> boxes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boxes.add(new Box<>(Double.parseDouble(reader.readLine())));
        }

        Box<Double> compareWith = new Box<>(Double.parseDouble(reader.readLine()));

        System.out.println(genericCount(boxes, compareWith));
    }

    private static int genericCount(List<Box<Double>> list, Box<Double> target) {
        int count = 0;

        for (Box<Double> box : list) {
            if (box.compareTo(target.getData()) > 0) {
                count++;
            }
        }

        return count;
    }
}
