package plane;

public class PublicCircle {
    public double x, y, radius;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public PublicCircle() {
        x = 0.0;
        y = 0.0;
        radius = 1.0;
    }

    public PublicCircle(double px, double py, double pradius) {
        setX(px);
        setY(py);
        setRadius(pradius);
    }

    public double getArea() {
        return 2 * radius * Math.PI;
    }
}