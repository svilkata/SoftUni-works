package box;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setLength(double length) {
        Validator.validateNonNegativeSize(length, "Length");
        this.length = length;
    }

    private void setWidth(double width) {
        Validator.validateNonNegativeSize(width, "Width");
        this.width = width;
    }

    private void setHeight(double height) {
        Validator.validateNonNegativeSize(height, "Width");
        this.height = height;
    }

    public double calculateSurfaceArea() {
        return 2 *(this.length * this.width  + this.height * this.length + this.width * this.height );
    }

    public double calculateLateralSurfaceArea() {
        return 2 * this.length * this.height + 2 * this.width * this.height;
    }

    public double calculateVolume() {
        return this.length * this.width * this.height;
    }


}
