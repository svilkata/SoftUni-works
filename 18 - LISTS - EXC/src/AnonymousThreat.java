import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AnonymousThreat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> textToVirus = Arrays.stream(sc.nextLine().split("\\s+")).collect(Collectors.toList());

        for (int i = 0; i < textToVirus.size(); i++) {
            textToVirus.remove("");
        }

        String input = sc.nextLine();
        while (!"3:1".equals(input)) {
            String[] token = input.split(" ");
            switch (token[0]) {
                case "merge": {
                    int startIndex = Integer.parseInt(token[1]);
                    int endIndex = Integer.parseInt(token[2]);
                    concatText(textToVirus, startIndex, endIndex);
                    break;
                }
                case "divide": {
                    int indexElementToDivide = Integer.parseInt(token[1]);
                    int countSubelements = Integer.parseInt(token[2]);
                    String elementToDivide = textToVirus.get(indexElementToDivide);
                    int lengthOfSelectedElement = elementToDivide.length();

                    textToVirus.remove(indexElementToDivide);

                    if (lengthOfSelectedElement % countSubelements == 0) {
                        divideStringEqual(textToVirus, indexElementToDivide, countSubelements, elementToDivide, lengthOfSelectedElement);
                    } else {
                        divideStringUnequal(textToVirus, indexElementToDivide, countSubelements, elementToDivide, lengthOfSelectedElement);
                    }
                    break;
                }
            }

            input = sc.nextLine();
        }
        printVirusedtext(textToVirus);
    }

    private static void divideStringEqual(List<String> textToVirus, int indexElementToDivide, int countSubelements, String elementToDivide, int lengthOfSelectedElement) {
        int lengthOfSubelement = lengthOfSelectedElement / countSubelements;
        String[] temp = new String[countSubelements];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = "";
        }
        int br = 1;

        for (int i = 0; i <= lengthOfSelectedElement - 1; i = i + lengthOfSubelement) {
            for (int j = i; j < lengthOfSubelement * br; j++) {
                temp[br - 1] += elementToDivide.charAt(j) + "";
            }
            br++;

        }

        for (int i = 0; i < temp.length; i++) {
            textToVirus.add(indexElementToDivide, temp[i]);
            indexElementToDivide++;
        }

    }

    private static void divideStringUnequal(List<String> textToVirus, int indexElementToDivide, int countSubelements, String elementToDivide, int lengthOfSelectedElement) {
        int lengthOfSubelement = lengthOfSelectedElement / countSubelements;
        countSubelements -= 1;
        int lenghtResidualLastElement = lengthOfSelectedElement - (lengthOfSubelement * countSubelements);
        String[] temp = new String[countSubelements+1];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = "";
        }
        int br = 1;

        for (int i = 0; i < lengthOfSelectedElement - lenghtResidualLastElement; i = i + lengthOfSubelement) {
            for (int j = i; j < lengthOfSubelement * br; j++) {
                temp[br - 1] += elementToDivide.charAt(j) + "";
            }
            br++;
        }
        for (int k = lengthOfSelectedElement - lenghtResidualLastElement; k <=lengthOfSelectedElement-1  ; k++) {
            temp[br-1] += elementToDivide.charAt(k) + "";
        }

        for (int i = 0; i < temp.length; i++) {
            textToVirus.add(indexElementToDivide, temp[i]);
            indexElementToDivide++;
        }

    }


    private static void concatText(List<String> textToVirus, int startIndex, int endIndex) {
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (startIndex >= textToVirus.size()) {
            startIndex = textToVirus.size() - 1;
        }
        if (endIndex < 0) {
            endIndex = 0;
        }
        if (endIndex >= textToVirus.size()) {
            endIndex = textToVirus.size() - 1;
        }

        if (startIndex != endIndex) {
            String concatenatedElement = "";
            for (int i = startIndex; i <= endIndex; i++) {
                concatenatedElement = concatenatedElement + textToVirus.get(i);
            }
            textToVirus.set(endIndex, concatenatedElement);
            for (int i = startIndex; i <= endIndex - 1; i++) {
                textToVirus.remove(startIndex);
            }
        }
    }

    private static void printVirusedtext(List<String> textToVirus) {
        for (String el : textToVirus) {
            System.out.print(el + " ");
        }
    }
}
