package shapes;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
    }

    public final Double getHeight() {
        return this.height;
    }

    public final Double getWidth() {
        return this.width;
    }

    @Override
    public Double calculatePerimeter() {
        Double perimeter = this.height * 2 + this.width * 2;
        super.setPerimeter(perimeter);

        return perimeter;
    }

    @Override
    public Double calculateArea() {
        Double area = this.width * this.height;
        super.setArea(area);

        return area;
    }
}
