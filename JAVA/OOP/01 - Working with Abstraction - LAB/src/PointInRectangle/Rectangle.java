package PointInRectangle;

public class Rectangle {
    private Point bottomLeft;
    private Point topRight;

    public Rectangle(int bottomLeftX, int bottomLeftY, int topRightX, int topRightY) {
        this.bottomLeft = new Point(bottomLeftX, bottomLeftY);
        this.topRight = new Point(topRightX, topRightY);
    }

    public boolean contains(Point toCheck) {
        return  (toCheck.getX() >= this.bottomLeft.getX() && toCheck.getX() <= this.topRight.getX()
        && toCheck.getY() >= this.bottomLeft.getY() && toCheck.getY() <= this.topRight.getY());
    }


}
