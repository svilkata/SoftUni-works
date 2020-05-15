import java.util.Arrays;
import java.util.Scanner;

public class LadyBugs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        short nSizeField = Short.parseShort(sc.nextLine());
        int[] indexesField = new int[nSizeField];

        for (int i = 0; i < indexesField.length; i++) {
            indexesField[i] = 0;
        }

        int[] indexesLadybugs = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(e -> Integer.parseInt(e)).toArray();

        for (int i = 0; i < indexesLadybugs.length; i++) {
            if (indexesLadybugs[i] >= 0 && indexesLadybugs[i] < indexesField.length) {
                indexesField[indexesLadybugs[i]] = 1;
            }
        }


        String input = sc.nextLine();
        while (!"end".equals(input)) { //ако дадем команда End няма да се изпълни циъкла while

            String[] inputArr = input.split(" ");

            int startLadybugIndex = Integer.parseInt(inputArr[0]);
            int lengthLadyBug = Integer.parseInt(inputArr[2]);

//            if (startLadybugIndex < 0 || startLadybugIndex >= indexesField.length || indexesField[startLadybugIndex] == 0) {
//                continue;
//            }

            if (startLadybugIndex >= 0 && startLadybugIndex < indexesField.length && indexesField[startLadybugIndex] == 1) { // валиден старт на калинка
                if (lengthLadyBug < 0) {
                    lengthLadyBug = Math.abs(lengthLadyBug);
                    if ("left".equals(inputArr[1])) {
                        inputArr[1] = "right";
                    } else {
                        inputArr[1] = "left";
                    }
                }

                indexesField[Integer.parseInt(inputArr[0])] = 0;

                if ("left".equals(inputArr[1])) {
                    while (true) {
                        if (startLadybugIndex - lengthLadyBug < 0) {// калинката излиза от полето

                            break;
                        } else {//калинката остава в полето
                            if (indexesField[startLadybugIndex - lengthLadyBug] == 0) { //калинката се мести успешно

                                indexesField[startLadybugIndex - lengthLadyBug] = 1;
                                break;
                            } else { //калинката попада на друга калинка и прелита един ход с дължина length наляво
                                startLadybugIndex = startLadybugIndex - lengthLadyBug;
                            }
                        }
                    }
                } else {
                    while (true) {
                        if (startLadybugIndex + lengthLadyBug > indexesField.length - 1) {// калинката излиза от полето

                            break;
                        } else {//калинката остава в полето
                            if (indexesField[startLadybugIndex + lengthLadyBug] == 0) { //калинката се мести успешно

                                indexesField[startLadybugIndex + lengthLadyBug] = 1;
                                break;
                            } else { //калинката попада на друга калинка и прелита един ход с дължина Lenght надясно
                                startLadybugIndex = startLadybugIndex + lengthLadyBug;
                            }
                        }
                    }
                }

            }


            input = sc.nextLine();
        }

        for (int element : indexesField) {
            System.out.print(element + " ");
        }


    }
}
