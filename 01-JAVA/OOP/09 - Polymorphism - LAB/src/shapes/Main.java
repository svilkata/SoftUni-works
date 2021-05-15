package shapes;

public class Main {
    public static void main(String[] args) {
        Shape rec = new Rectangle(5.0, 10.0);
        Shape circle = new Circle(5.0);

        System.out.println(rec.calculateArea());
        System.out.println(circle.calculateArea());
    }
}
