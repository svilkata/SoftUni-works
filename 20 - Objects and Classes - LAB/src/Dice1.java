import java.util.Scanner;

public class Dice1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Dice d = new Dice(6);
        //Dice sides6 = Dice.generateWithSides(6);

        d.roll();
        System.out.println(d.roll());

//        System.out.println(d.getSides());
//        d.setSides(6);
//        System.out.println(d.getSides());

//        Dice d1 = new Dice();
//        System.out.println(d1.getSides());
    }
}
