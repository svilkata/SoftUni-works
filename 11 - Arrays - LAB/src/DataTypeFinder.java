import java.util.Scanner;

public class DataTypeFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        boolean isNumber = true;
        boolean isFloating = false;
        boolean isBoolean = false;
        boolean isChar = false;
        boolean isAllZeros = true;
        int brDots = 0, brHyphen = 0;

        if (input.length() != 0) {


            while (!"END".equals(input)) {

//            if (input.length() == 0) {
//                input = sc.nextLine();
//                continue;
//            }

                for (int i = 0; i < input.length(); i++) {

                    if (input.charAt(i) == '.') {
                        brDots++;
                        if (brDots > 1 || input.charAt(0) == '.') {
                            isNumber = false;
                            break;
                        }
                    }

                    if (input.charAt(i) == '-') {
                        brHyphen++;
                        if (brHyphen > 1 || (input.charAt(i) == '-' && i != 0)
                                || (input.charAt(i) == '-' && input.length() == 1)) {
                            isNumber = false;
                            break;
                        }
                    }

                    if (!(input.charAt(i) == '0' || input.charAt(i) == '1' || input.charAt(i) == '2' || input.charAt(i) == '3'
                            || input.charAt(i) == '4' || input.charAt(i) == '5' || input.charAt(i) == '6' || input.charAt(i) == '7'
                            || input.charAt(i) == '8' || input.charAt(i) == '9'
                            || input.charAt(i) == '.' || input.charAt(i) == '-')) {
                        isNumber = false;
                        break;
                    }

                } //end of the Cycle FOR

                //2nd For cycle
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) != '0' && input.charAt(i) != '.' && input.charAt(i) != '-') {
                        isAllZeros = false;
                        break;
                    }
                }

                if (isAllZeros && isNumber && input.length() > 1 && input.charAt(0) == '-') {
                    isNumber = false;
                }


                if (brDots == 1) {

                    isFloating = true;
                }

                if (input.length() == 1 && !isNumber) {
                    isChar = true;
                } else {
                    if ((input.contains("true") && input.length() == 4)
                            || (input.contains("false") && input.length() == 5)) {
                        isBoolean = true;
                    }
                }

                //Оттук надолу печатаме
                if (isNumber) {
                    if (isFloating) { // тука е FLoating
                        System.out.println(String.format("%s is floating point type", input));
                    } else { // тука е Integer
                        System.out.println(String.format("%s is integer type", input));
                    }
                } else if (isBoolean) {
                    System.out.println(String.format("%s is boolean type", input));
                } else if (isChar) {
                    System.out.println(String.format("%s is character type", input));
                } else {
                    System.out.println(String.format("%s is string type", input));
                }


                input = sc.nextLine();
                isNumber = true;
                isFloating = false;
                isBoolean = false;
                isChar = false;
                brDots = 0;
                brHyphen = 0;
                isAllZeros = true;


            }
        }


    }
}
