package box;

public class Main {
    public static void main(String[] args) {
        Box box = new Box(2, 3, 4.0);

        System.out.println("Surface Area - " + box.calculateSurfaceArea());
        System.out.println("Lateral Surface Area - " + box.calculateLateralSurfaceArea());
        System.out.println("Volume – " + box.calculateVolume());
    }
}
