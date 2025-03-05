package point2d;

public class Point {
    private double x, y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (x > 1000000) {
            throw new IllegalArgumentException();
        }
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        if (y > 1000000) {
            throw new IllegalArgumentException();
        }
        this.x = y;
    }

    public Point() {
        x = 0.0;
        y = 0.0;
    }

    public Point(double px, double py) {
        setX(px);
        setY(py);
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void mirror(double cx, double cy) {
        x = 2 * cx - x;
        y = 2 * cy - y;
    }

    public void mirrorP(Point p) {
        x = 2 * p.x - x;
        y = 2* p.y - y;
    }

    public double distance(Point p) {
        x = x - p.x;
        y = y - p.y;

        return Math.sqrt(x * x + y * y);
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}