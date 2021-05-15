import java.util.Scanner;

public class AreaOfFigures {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String figureType = sc.nextLine();
        double area = 0.0;

        switch (figureType) {
            case "square": {
                Double a = Double.parseDouble(sc.nextLine());
                area = a * a;
                break;
            }
            case "rectangle": {
                Double a = Double.parseDouble(sc.nextLine());
                Double b = Double.parseDouble(sc.nextLine());
                area = a * b;
                break;
            }
            case "circle": {
                Double r = Double.parseDouble(sc.nextLine());
                area = Math.PI * r * r;
                break;
            }
            case "triangle": {
                Double a = Double.parseDouble(sc.nextLine());
                Double h = Double.parseDouble(sc.nextLine());
                area = a * h / 2;
                break;
            }
        }

        System.out.println(String.format("%.3f", area));
    }
}
