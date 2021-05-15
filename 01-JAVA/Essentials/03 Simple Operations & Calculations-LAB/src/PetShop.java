import java.util.Scanner;

public class PetShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numDogs = Integer.parseInt(sc.nextLine()), numOtherAnimals = Integer.parseInt(sc.nextLine());

        System.out.printf("%.2f lv.", numDogs*2.50 + numOtherAnimals * 4.0);

    }
}
