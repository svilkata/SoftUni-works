import java.util.Scanner;

public class StringExplosion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        int power = 0;
        StringBuilder result = new StringBuilder();

        //добавяме само това, което не е гръмнало
        //това, което го махаме просто го пропускаме да го добавим
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            if (symbol == '>') {
                result.append('>');
                power += Integer.parseInt(String.valueOf(input.charAt(i+1)));
            } else if (power == 0) {
                result.append(symbol);
            } else {
                power--;
            }
        }

        System.out.println(result);
    }
}
