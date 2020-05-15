import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class ArrayManipulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numberArr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();

        String input = sc.nextLine();

        while (!"end".equals(input)) {
            String[] currInput = input.split(" ");

            switch (currInput[0]) {
                case "exchange": {
                    if (Integer.parseInt(currInput[1]) < 0 || Integer.parseInt(currInput[1]) >= numberArr.length) {
                        System.out.println("Invalid index");
                    } else if (Integer.parseInt(currInput[1]) != numberArr.length - 1) {
                        numberArr = exchangeArr(numberArr, Integer.parseInt(currInput[1]));
                    }
//                    for (int s : numberArr) {
//                        System.out.print(s + " ");
//                    }
                    //System.out.println();
                    break;
                }
                case "max": {
                    if (currInput[1].equals("even")) {
                        int result = maxNumberIndex(numberArr, "even");
                        if (result == -1) {
                            System.out.println("No matches");
                        } else {
                            System.out.println(result);
                        }
                    } else if (currInput[1].equals("odd")) {
                        int result = maxNumberIndex(numberArr, "odd");
                        if (result == -1) {
                            System.out.println("No matches");
                        } else {
                            System.out.println(result);
                        }
                    }
                    break;
                }
                case "min": {
                    if (currInput[1].equals("even")) {
                        int result = minNumberIndex(numberArr, "even");
                        if (result == -1) {
                            System.out.println("No matches");
                        } else {
                            System.out.println(result);
                        }
                    } else if (currInput[1].equals("odd")) {
                        int result = minNumberIndex(numberArr, "odd");
                        if (result == -1) {
                            System.out.println("No matches");
                        } else {
                            System.out.println(result);
                        }
                    }
                    break;
                }
                case "first": {
                    if (Integer.parseInt(currInput[1]) > numberArr.length) {
                        System.out.println("Invalid count");
                    }
//                    else if (Integer.parseInt(currInput[1]) <= 0) {
//                        System.out.println("[]");
//                    }
                    else {
                        if (currInput[2].equals("even")) {
                            firstNElements(numberArr, Integer.parseInt(currInput[1]), "even");
                        } else if (currInput[2].equals("odd")) {
                            firstNElements(numberArr, Integer.parseInt(currInput[1]), "odd");
                        }
                    }
                    break;
                }
                case "last": {
                    if (Integer.parseInt(currInput[1]) > numberArr.length) {
                        System.out.println("Invalid count");
                    }
//                    else if (Integer.parseInt(currInput[1]) <= 0) {
//                        System.out.println("[]");
//                    }
                    else {
                        if (currInput[2].equals("even")) {
                            lastNElements(numberArr, Integer.parseInt(currInput[1]), "even");
                        } else if (currInput[2].equals("odd")) {
                            lastNElements(numberArr, Integer.parseInt(currInput[1]), "odd");
                        }
                    }

                    break;
                }
            }


            input = sc.nextLine();
        }

        printArr(numberArr);

    }

    private static void firstNElements(int[] numberArr, int count, String evenOrOdds) {
        int[] temp = new int[count]; //подсигурили сме проверка за count == 0 в главния метод

        int br = 0;

        if ("even".equals(evenOrOdds)) {
            for (int i = 0; i < numberArr.length; i++) {
                int currEl = numberArr[i];
                if (currEl % 2 == 0) {
                    temp[br] = currEl;
                    br++;
                    if (br >= count) {
                        break;
                    }
                }
            }
        } else if ("odd".equals(evenOrOdds)) {
            for (int i = 0; i < numberArr.length; i++) {
                int currEl = numberArr[i];
                if (currEl % 2 != 0) {
                    temp[br] = currEl;
                    br++;
                    if (br >= count) {
                        break;
                    }
                }
            }
        }

        printArr(temp, br); //принтирай масив Temp до br-я елемент
    }

    private static void lastNElements(int[] numberArr, int count, String evenOrOdds) {
        int[] temp = new int[count]; //подсигурили сме проверка за count == 0 в главния метод

        int br = 0;

        if ("even".equals(evenOrOdds)) {
            for (int i = numberArr.length - 1; i >= 0; i--) {
                int currEl = numberArr[i];
                if (currEl % 2 == 0) {
                    temp[br] = currEl;
                    br++;
                    if (br >= count) {
                        break;
                    }
                }
            }
        } else if ("odd".equals(evenOrOdds)) {
            for (int i = numberArr.length - 1; i >= 0; i--) {
                int currEl = numberArr[i];
                if (currEl % 2 != 0) {
                    temp[br] = currEl;
                    br++;
                    if (br >= count) {
                        break;
                    }
                }
            }
        }

        //printArr(temp, br);
        printArrReverse(temp, br); //принтирай масив Temp до br-я елемент
    }

    private static void printArrReverse(int[] temp, int br) { //принтиране до определен елемент в обратен ред
        if (br == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[" + temp[br - 1]);
            for (int i = br - 2; i >= 0; i--) {
                System.out.print(", " + temp[i]);
            }
            System.out.println("]");
        }
    }

    private static void printArr(int[] temp, int br) { //принтиране до определен елемент
        if (br == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < br - 1; i++) {
                System.out.print(temp[i] + ", ");
            }
            System.out.println(temp[br - 1] + "]");
        }

    }

    private static void printArr(int[] temp) { //принтиране на целия масив
        System.out.print("[");
        for (int i = 0; i < temp.length - 1; i++) {
            System.out.print(temp[i] + ", ");
        }
        System.out.println(temp[temp.length - 1] + "]");
        //System.out.println();
    }

    private static int minNumberIndex(int[] numberArr, String evenOrOdd) {
        int minNumber = Integer.MAX_VALUE;
        int minIndex = -1;
        if (evenOrOdd.equals("even")) {
            for (int i = 0; i < numberArr.length; i++) {
                if (numberArr[i] % 2 == 0) {
                    if (numberArr[i] <= minNumber) {
                        minNumber = numberArr[i];
                        minIndex = i;
                    }
                }
            }
        } else if (evenOrOdd.equals("odd")) {
            for (int i = 0; i < numberArr.length; i++) {
                if (numberArr[i] % 2 != 0) {
                    if (numberArr[i] <= minNumber) {
                        minNumber = numberArr[i];
                        minIndex = i;
                    }
                }
            }
        }

        return minIndex;
    }

    private static int maxNumberIndex(int[] numberArr, String evenOrOdd) {
        int maxNumber = Integer.MIN_VALUE;
        int maxIndex = -1;
        if (evenOrOdd.equals("even")) {
            for (int i = 0; i < numberArr.length; i++) {
                if (numberArr[i] % 2 == 0) {
                    if (numberArr[i] >= maxNumber) {
                        maxNumber = numberArr[i];
                        maxIndex = i;
                    }
                }
            }
        } else if (evenOrOdd.equals("odd")) {
            for (int i = 0; i < numberArr.length; i++) {
                if (numberArr[i] % 2 != 0) {
                    if (numberArr[i] >= maxNumber) {
                        maxNumber = numberArr[i];
                        maxIndex = i;
                    }
                }
            }
        }

        return maxIndex;
    }


    private static int[] exchangeArr(int[] numbers, int index) {
        //index = index % numbers.length;

        String[] arR = fromIntToStringArr(numbers);

        String[] a = Arrays.copyOf(arR, index + 1);

        int length = arR.length;
        String[] b = Arrays.copyOfRange(arR, index + 1, length);

        String[] both = Stream.concat(Arrays.stream(b), Arrays.stream(a)).toArray(String[]::new);

        return fromStringToInt(both);

    }

    private static int[] fromStringToInt(String[] both) {
        int[] temp = new int[both.length];
        for (int i = 0; i < both.length; i++) {
            temp[i] = Integer.parseInt(both[i]);
        }

        return temp;
    }

    private static String[] fromIntToStringArr(int[] numbers) {
        String[] temp = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            temp[i] = numbers[i] + "";
        }

        return temp;
    }
}
