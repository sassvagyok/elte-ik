public class Circle {
    private double x, y, radius;

    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void enlarge(int f) {
        radius = radius * f;
    }

    public double getArea() {
        return 2 * radius * Math.PI;
    }

    public String toString() {
        return "sugar: " + radius;
    }
}