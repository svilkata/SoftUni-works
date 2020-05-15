import java.util.Random;
import java.util.Scanner;

public class Dice {

    private int sides;

    public Dice(int sides){
        this.sides = sides;
    }

    public int getSides() {
        return this.sides;
    }

    public void setSides(int newSides) {
        this.sides = newSides;
    }

//    public static Dice generateWithSides(int s) {
//        Dice d = new Dice();
//        d.setSides(s);
//
//        return d;
//    }

    public int roll() {
        int result = this.getRandomNumber();
        return result;
    }

    private int getRandomNumber() {
        Random rnd = new Random();

        return rnd.nextInt(this.sides) + 1;

    }
}
