import java.util.Scanner;

public class GreaterTwoValues {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String typeInput = sc.nextLine();

        switch (typeInput){
            case "int": {
                int a = Integer.parseInt(sc.nextLine());
                int b = Integer.parseInt(sc.nextLine());
                getMax(a, b);
                break;
            }

            case "char": {
                char a = sc.nextLine().charAt(0);
                char b = sc.nextLine().charAt(0);
                getMax(a, b);
                break;
            }

            case "string": {
                String a = sc.nextLine();
                String b = sc.nextLine();
                getMax(a, b);
                break;
            }
        }
    }

    private static void getMax(int a, int b) {
        if (a > b) {
            System.out.println(a);
        } else {
            System.out.println(b);
        }
    }

    private static void getMax(char a, char b) {
        if (a > b) {
            System.out.println(a);
        } else {
            System.out.println(b);
        }
    }

    private static void getMax(String a, String b) {
        if (a.compareTo(b) >0) {
            System.out.println(a);
        } else {
            System.out.println(b);
        }
    }
}
