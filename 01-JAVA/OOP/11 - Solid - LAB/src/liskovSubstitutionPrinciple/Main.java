package liskovSubstitutionPrinciple;

public class Main {
    public static void main(String[] args) {
//        Rectangle rectangle = new Rectangle(10, 10);
        Square rectangle = new Square(10);
        rectangle.setHeight(20);
        rectangle.setWidth(3);

        System.out.println(rectangle.getArea());

    }

    public static class Rectangle {
        int width;
        int height;

        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return this.width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getArea() {
            return this.width * this.height;
        }
    }

    public static class Square extends Rectangle {
        public Square(int side) {
            super(side, side);
        }

        public void setSide(int side) {
            this.setWidth(side);
            this.setHeight(side);
        }

        @Override
        public int getArea() {
            return this.width * this.width;
        }
    }
}
