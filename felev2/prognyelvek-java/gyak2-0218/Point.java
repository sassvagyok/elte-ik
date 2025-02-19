public class Point {
    private double x, y;

    public Point(double px, double py) {
        x = px; // ugyanaz, mintha this.x lenne x helyett
        // this.x = x (ha x a paraméter neve is)
        y = py;
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