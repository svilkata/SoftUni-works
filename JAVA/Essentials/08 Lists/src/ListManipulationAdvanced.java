import java.util.*;
import java.util.stream.Collectors;

public class ListManipulationAdvanced {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());

        while (true) {
            String line = sc.nextLine();

            if ("end".equals(line)) {
                break;
            }

            String[] token = line.split(" ");

            switch (token[0]) {
                case "Contains": {
                    if (numbers.contains(Integer.valueOf(token[1]))) {
                        System.out.println("Yes");
                    } else {
                        System.out.println("No such number");
                    }

                    break;
                }
                case "Print": { //printEvenOrOdd
                    if ("even".equals(token[1])) {
                        printEvenOrOdd(numbers, "even");
                    } else {
                        printEvenOrOdd(numbers, "odd");
                    }

                    break;
                }
                case "Get": { // Get sum
                    getSumElements(numbers);
                    break;
                }
                case "Filter": {
                    String condition = token[1];
                    int num = Integer.parseInt(token[2]);
                    printWithCondition(condition, num, numbers);
                    break;
                }
            }
        }
        //System.out.println(numbers.toString().replaceAll("[\\[\\],]", ""));
    }

    private static void printWithCondition(String condition, int num, List<Integer> items) {
        List<Integer> newList = new ArrayList<>();

        switch (condition) {
            case ">=": {
                for (Integer element : items) {
                    if (element >= num) {
                        newList.add(element);
                    }
                }
                break;
            }
            case ">": {
                for (Integer element : items) {
                    if (element > num) {
                        newList.add(element);
                    }
                }
                break;
            }
            case "<=": {
                for (Integer element : items) {
                    if (element <= num) {
                        newList.add(element);
                    }
                }
                break;
            }
            case "<": {
                for (Integer element : items) {
                    if (element < num) {
                        newList.add(element);
                    }
                }
                break;
            }
        }
        System.out.println(newList.toString().replaceAll("[\\[\\],]", ""));
    }

    private static void getSumElements(List<Integer> items) {
        int sum = 0;
        for (Integer element : items) {
            sum += element;
        }
        System.out.println(sum);

    }


    private static void printEvenOrOdd(List<Integer> items, String evenOrodd) {
        int sumEven = 0;
        int sumOdd = 0;
        List<Integer> newEven = new ArrayList<>();
        List<Integer> newOdd = new ArrayList<>();

        if ("even".equals(evenOrodd)) {
            for (Integer element : items) {
                if (element % 2 == 0) {
                    sumEven += element;
                    newEven.add(element);
                }
            }
            System.out.println(newEven.toString().replaceAll("[\\[\\],]", ""));
        } else {
            for (Integer element : items) {
                if (element % 2 != 0) {
                    sumOdd += element;
                    newOdd.add(element);
                }
            }
            System.out.println(newOdd.toString().replaceAll("[\\[\\],]", ""));
        }

    }


}

